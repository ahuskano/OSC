package co.ahuskano.something.fragments;

import android.view.View;

import co.ahuskano.something.R;
import co.ahuskano.something.fragments.BaseFragment;
import co.ahuskano.something.fragments.FragmentFactory;


public class FragmentOverview extends BaseFragment {

    public FragmentOverview() {
        super(FragmentFactory.FRAGMENT_OVERVIEW);
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_overview;
    }

    @Override
    public String getName() {
        return null;
    }
}
