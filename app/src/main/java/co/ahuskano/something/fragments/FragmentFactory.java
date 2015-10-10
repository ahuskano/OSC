package co.ahuskano.something.fragments;

import android.support.v4.app.Fragment;


public class FragmentFactory {

    public static final int FRAGMENT_OVERVIEW=1;
    public static final int FRAGMENT_LIST=2;
    public static final int FRAGMENT_SETTINGS=3;

    public static Fragment provideFragment(int tag) {

        switch (tag) {
            case FRAGMENT_OVERVIEW:
                return new FragmentOverview();
            case FRAGMENT_LIST:
                return new FragmentList();
            default:
                return new FragmentOverview();
        }
    }
}
