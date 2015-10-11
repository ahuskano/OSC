package co.ahuskano.something.fragments;

import android.content.Intent;
import android.location.Location;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.ahuskano.something.R;
import co.ahuskano.something.activitys.SpaceDetailActivity;
import co.ahuskano.something.api.BaseResponse;
import co.ahuskano.something.api.SpacesResponse;
import co.ahuskano.something.controllers.BaseController;
import co.ahuskano.something.controllers.SpacesController;
import co.ahuskano.something.controllers.TopSpacesController;
import co.ahuskano.something.models.Space;
import co.ahuskano.something.util.Constants;
import co.ahuskano.something.util.recycleView.RecycleAdapter;
import co.ahuskano.something.util.recycleView.RecyclerItemClickListener;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import retrofit.RetrofitError;

/**
 * Created by ahuskano on 10.10.2015..
 */
public class FragmentNearMe extends BaseFragment implements BaseController.OnDataReadListener,BaseController.OnDataErrorListener{

    private TopSpacesController spaces;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecycleAdapter<Space> adapterRecycle;
    private List<Space> data;
    private Location loc;
    public FragmentNearMe() {
        super(FragmentFactory.FRAGMENT_TOP);
    }

    @Override
    public void initViews(View view) {
        showDialog();
        data=new ArrayList<>();
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterRecycle = new RecycleAdapter<>(getActivity(),data);
        recyclerView.setAdapter(adapterRecycle);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (spaces != null && loc!=null)
                    spaces.getSpaces(String.valueOf(loc.getLatitude()),String.valueOf(loc.getLongitude()));
            }
        });

        spaces=new TopSpacesController(getActivity());
        spaces.setOnDataErrorListener(this);
        spaces.setOnDataReadListener(this);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity().getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), SpaceDetailActivity.class);
                        intent.putExtra(Constants.KEY_SPACE_ID, String.valueOf(data.get(position).getId()));
                        intent.putExtra(Constants.KEY_SPACE_NAME, data.get(position).getName());

                        startActivity(intent);
                    }
                })
        );


        SmartLocation.with(getActivity()).location()
                .oneFix()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        if (location != null) {
                            loc=location;
                            spaces.getSpaces(String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude()));
                        }

                    }
                });
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_top;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void onDataErrorReceive(RetrofitError error) {
        dismissDialog();
    }

    @Override
    public void onDataReceive(BaseResponse response) {
        SpacesResponse spaces=(SpacesResponse) response;
        data.clear();
        data.addAll(Arrays.asList(spaces.getData()));
        adapterRecycle.setItems(data);
        adapterRecycle.notifyDataSetChanged();
        if(swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
        dismissDialog();
    }
}
