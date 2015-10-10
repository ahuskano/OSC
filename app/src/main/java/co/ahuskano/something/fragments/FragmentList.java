package co.ahuskano.something.fragments;

import android.view.View;

import co.ahuskano.something.R;
import co.ahuskano.something.fragments.BaseFragment;
import co.ahuskano.something.fragments.FragmentFactory;


public class FragmentList extends BaseFragment {

    public FragmentList() {
        super(FragmentFactory.FRAGMENT_LIST);
    }

    @Override
    public void initViews(View view) {

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
