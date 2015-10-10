package co.ahuskano.something.fragments;

import android.support.v4.app.Fragment;


public class FragmentFactory {

    public static final int FRAGMENT_OVERVIEW=1;
    public static final int FRAGMENT_LIST=2;
    public static final int FRAGMENT_MAP=3;

    public static final int FRAGMENT_DASHBOARD=1;
    public static final int FRAGMENT_USERS=2;
    public static final int FRAGMENT_SPACES=3;
    public static final int FRAGMENT_TAGS=4;
    public static final int FRAGMENT_SETTINGS=5;

    public static Fragment provideFragment(int tag) {

        switch (tag) {
            case FRAGMENT_DASHBOARD:
                return new FragmentDashboard();
            case FRAGMENT_SPACES:
                return new FragmentSpaces();
            case FRAGMENT_TAGS:
                return new FragmentTags();
            case FRAGMENT_USERS:
                return new FragmentUsers();
            default:
                return new FragmentDashboard();
        }
    }
}
