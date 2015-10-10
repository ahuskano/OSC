package co.ahuskano.something.fragments;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.View;

import co.ahuskano.something.R;
import co.ahuskano.something.util.SlidingTabLayout;
import co.ahuskano.something.util.ViewPagerAdapter;

/**
 * Created by ahuskano on 10.10.2015..
 */
public class FragmentTabs extends BaseFragment  implements ActionBar.TabListener{

    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;
    private CharSequence titles[] = {"List", "Map"};
    private int Numboftabs = 2;

    public FragmentTabs() {
        super(FragmentFactory.FRAGMENT_TABS);
    }
    @Override
    public void initViews(View view) {
        adapter =  new ViewPagerAdapter(getFragmentManager(),titles,Numboftabs);
        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) view.findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.white);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_tabs;
    }

    @Override
    public String getName() {
        return null;
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
