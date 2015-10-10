package co.ahuskano.something.fragments;

import android.view.View;

import co.ahuskano.something.R;

/**
 * Created by ahuskano on 10.10.2015..
 */
public class FragmentSpaces extends BaseFragment {

    public FragmentSpaces() {
        super(FragmentFactory.FRAGMENT_SPACES);
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_spaces;
    }

    @Override
    public String getName() {
        return null;
    }
}
