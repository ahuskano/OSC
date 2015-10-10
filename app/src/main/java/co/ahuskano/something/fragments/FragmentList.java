package co.ahuskano.something.fragments;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import co.ahuskano.something.R;
import co.ahuskano.something.fragments.BaseFragment;
import co.ahuskano.something.fragments.FragmentFactory;
import co.ahuskano.something.util.recycleView.DemoModel;
import co.ahuskano.something.util.recycleView.RecycleAdapter;


public class FragmentList extends BaseFragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecycleAdapter<DemoModel> adapterRecycle;
    private FloatingActionButton fab;
    private List<DemoModel> data;
    public FragmentList() {
        super(FragmentFactory.FRAGMENT_LIST);
    }

    @Override
    public void initViews(View view) {
        fab=(FloatingActionButton) view.findViewById(R.id.fabNew);
        data=new ArrayList<>();
        data.add(new DemoModel());
        data.add(new DemoModel());
        data.add(new DemoModel());
        data.add(new DemoModel());
        data.add(new DemoModel());
        data.add(new DemoModel());
        data.add(new DemoModel());
        data.add(new DemoModel());
        data.add(new DemoModel());
        data.add(new DemoModel());
        data.add(new DemoModel());
        data.add(new DemoModel());
        data.add(new DemoModel());
        data.add(new DemoModel());
        data.add(new DemoModel());

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterRecycle = new RecycleAdapter<>(getActivity(),data);
        recyclerView.setAdapter(adapterRecycle);
        fab.show(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);

                    }
                }, 1500);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_list;
    }

    @Override
    public String getName() {
        return null;
    }
}
