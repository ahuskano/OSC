package co.ahuskano.something.fragments;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.ahuskano.something.R;
import co.ahuskano.something.api.BaseResponse;
import co.ahuskano.something.api.SpacesResponse;
import co.ahuskano.something.controllers.BaseController;
import co.ahuskano.something.controllers.SpaceController;
import co.ahuskano.something.models.Space;
import co.ahuskano.something.util.recycleView.RecycleAdapter;
import retrofit.RetrofitError;


public class FragmentList extends BaseFragment implements BaseController.OnDataReadListener, BaseController.OnDataErrorListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecycleAdapter<Space> adapterRecycle;
    private FloatingActionButton fab;
    private List<Space> data;
    public FragmentList() {
        super(FragmentFactory.FRAGMENT_LIST);
    }

    @Override
    public void initViews(View view) {
        fab=(FloatingActionButton) view.findViewById(R.id.fabNew);
        data=new ArrayList<>();
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterRecycle = new RecycleAdapter<>(getActivity(),data);
        recyclerView.setAdapter(adapterRecycle);
        fab.show(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });

        SpaceController spaces=new SpaceController(getActivity());
        spaces.setOnDataErrorListener(this);
        spaces.setOnDataReadListener(this);
        spaces.getSpaces();
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
    }
}
