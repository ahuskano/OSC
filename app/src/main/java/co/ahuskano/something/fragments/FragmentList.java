package co.ahuskano.something.fragments;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.ahuskano.something.R;
import co.ahuskano.something.activitys.SpaceDetailActivity;
import co.ahuskano.something.api.BaseResponse;
import co.ahuskano.something.api.SpacesResponse;
import co.ahuskano.something.controllers.BaseController;
import co.ahuskano.something.controllers.SpacesController;
import co.ahuskano.something.models.Space;
import co.ahuskano.something.util.Constants;
import co.ahuskano.something.util.recycleView.RecycleAdapter;
import co.ahuskano.something.util.recycleView.RecyclerItemClickListener;
import retrofit.RetrofitError;


public class FragmentList extends BaseFragment implements BaseController.OnDataReadListener, BaseController.OnDataErrorListener {

    private SpacesController spaces;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecycleAdapter<Space> adapterRecycle;
    private List<Space> data;
    public FragmentList() {
        super(FragmentFactory.FRAGMENT_LIST);
    }

    @Override
    public void initViews(View view) {
        data=new ArrayList<>();
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterRecycle = new RecycleAdapter<>(getActivity(),data);
        recyclerView.setAdapter(adapterRecycle);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (spaces != null)
                    spaces.getSpaces();
            }
        });

        spaces=new SpacesController(getActivity());
        spaces.setOnDataErrorListener(this);
        spaces.setOnDataReadListener(this);
        spaces.getSpaces();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity().getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent=new Intent(getActivity(), SpaceDetailActivity.class);
                        intent.putExtra(Constants.KEY_SPACE_ID, String.valueOf(data.get(position).getId()));
                        intent.putExtra(Constants.KEY_SPACE_NAME, data.get(position).getName());

                        startActivity(intent);
                    }
                })
        );
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_list;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void onDataErrorReceive(RetrofitError error) {

    }

    @Override
    public void onDataReceive(BaseResponse response) {
        SpacesResponse spaces=(SpacesResponse) response;
        data.addAll(Arrays.asList(spaces.getData()));
        adapterRecycle.setItems(data);
        adapterRecycle.notifyDataSetChanged();
        if(swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
    }
}
