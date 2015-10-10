package co.ahuskano.something.fragments;

import android.view.View;

import co.ahuskano.something.R;

/**
 * Created by ahuskano on 10.10.2015..
 */
public class FragmentTags extends BaseFragment {

    public FragmentTags() {
        super(FragmentFactory.FRAGMENT_TAGS);
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_tags;
    }

    @Override
    public String getName() {
        return null;
    }
}
