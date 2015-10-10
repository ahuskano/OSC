package co.ahuskano.something.fragments;

import android.view.View;

import co.ahuskano.something.R;

/**
 * Created by ahuskano on 10.10.2015..
 */
public class FragmentUsers extends BaseFragment {

    public FragmentUsers() {
        super(FragmentFactory.FRAGMENT_USERS);
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_users;
    }

    @Override
    public String getName() {
        return null;
    }
}
