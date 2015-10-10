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
    public static final int FRAGMENT_TABS=6;
    public static final int FRAGMENT_TAB_ONE=7;
    public static final int FRAGMENT_TAB_TWO=8;

    public static Fragment provideFragment(int tag) {

        switch (tag) {
            case FRAGMENT_DASHBOARD:
                return new FragmentDashboard();
            case FRAGMENT_SPACES:
                return new FragmentTabs();
            case FRAGMENT_TAGS:
                return new FragmentTags();
            case FRAGMENT_USERS:
                return new FragmentUsers();
            case FRAGMENT_TAB_ONE:
                return new FragmentList();
            case FRAGMENT_TAB_TWO:
                return new FragmentMapa();
            default:
                return new FragmentDashboard();
        }
    }
}
