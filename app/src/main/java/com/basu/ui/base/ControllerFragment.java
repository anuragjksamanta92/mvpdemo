package com.basu.ui.base;

import android.os.Bundle;

/**
 * Created by Suhrit on 21/7/18.
 */

public interface ControllerFragment {
    void addFragment(BaseFragment baseFragment, String tag);
    void addFragment(BaseFragment baseFragment, Bundle args, String tag);

    void removeFragment(String tag);

    void removeFragment(BaseFragment baseFragment);

    void showFragment(BaseFragment baseFragment);
    void showFragment(BaseFragment baseFragment, Bundle args);

    void showFragment(String tag);

    void hideFragment(BaseFragment baseFragment);

    void hideFragment(String tag);

    void replaceFragment(BaseFragment baseFragment, boolean isAddToBackStack);
    void replaceFragment(BaseFragment baseFragment, Bundle args, boolean isAddToBackStack);
     void replaceFragment(BaseFragment baseFragment, Bundle args, int id);
    BaseFragment getCurrentlyDisplayedFragment();
    BaseFragment getCurrentlyDisplayedFragment(int framelayout);

    BaseFragment findFragmentByTag(String tag);

    void goBack();

    void replaceFragment(BaseFragment baseFragment);
    void replaceFragment(BaseFragment baseFragment, int id);
    void replaceFragment(BaseFragment baseFragment, Bundle args);

    void removeAllFragment();
}
