package com.basu.ui.base;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.basu.R;

/**
 * Created by Suhrit on 10/17/2018
 **/
public class BaseControllerActivity extends BaseActivity implements ControllerFragment{
    @Override
    protected void setUp() {

    }
    /**
     * Add Fragment
     *
     * @param baseFragment
     * @param tag
     */
    @Override
    public void addFragment(BaseFragment baseFragment, String tag) {
        if (baseFragment != null) {
            FragmentTransaction fragmentTransaction = getFragmentTransaction();
            fragmentTransaction.
                    add(R.id.cl_root_view,
                            baseFragment, tag).commitAllowingStateLoss();
        }
    }

    /**
     * Add new_fragment_invitebymail and pass data using Bundle
     *
     * @param baseFragment
     * @param args
     * @param tag
     */
    @Override
    public void addFragment(BaseFragment baseFragment, Bundle args, String tag) {
        if (baseFragment != null) {
            baseFragment.setArguments(args);
            FragmentTransaction fragmentTransaction = getFragmentTransaction();
            fragmentTransaction.add(R.id.cl_root_view, baseFragment, tag);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    /**
     * Remove Fragment USing TAG
     *
     * @param tag
     */
    @Override
    public void removeFragment(String tag) {
        BaseFragment baseFragment = findFragmentByTag(tag);
        if (baseFragment != null) {
            getFragmentTransaction().remove(baseFragment).commitAllowingStateLoss();
        }
    }

    /**
     * Remove Fragment
     *
     * @param baseFragment
     */

    @Override
    public void removeFragment(BaseFragment baseFragment) {
        if (baseFragment != null) {
            getFragmentTransaction().remove(baseFragment).commitAllowingStateLoss();
        }else{
            Log.e("baseFragment "," ++ NULL");
        }
    }

    /**
     * Show Fragment and send data using Bundle
     *
     * @param baseFragment
     * @param args
     */
    @Override
    public void showFragment(BaseFragment baseFragment, Bundle args) {
        if (baseFragment != null) {
            baseFragment.setArguments(args);
            FragmentTransaction fragmentTransaction = getFragmentTransaction();
            fragmentTransaction.show(baseFragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    /**
     * Show Fragment
     *
     * @param baseFragment
     */
    @Override
    public void showFragment(BaseFragment baseFragment) {
        if (baseFragment != null) {
            FragmentTransaction fragmentTransaction = getFragmentTransaction();
            fragmentTransaction.show(baseFragment).commitAllowingStateLoss();
        }
    }

    /**
     * Show Fragment Using TAG
     *
     * @param tag
     */
    @Override
    public void showFragment(String tag) {
        BaseFragment baseFragment = findFragmentByTag(tag);
        if (baseFragment != null) {
            FragmentTransaction fragmentTransaction = getFragmentTransaction();
            fragmentTransaction.show(baseFragment).commitAllowingStateLoss();
        }
    }

    /**
     * Hide Fragment
     *
     * @param baseFragment
     */
    @Override
    public void hideFragment(BaseFragment baseFragment) {
        if (baseFragment != null) {
            getFragmentTransaction().hide(baseFragment).commitAllowingStateLoss();
        }
    }

    /**
     * Hide Fragmnet using TAG
     *
     * @param tag
     */
    @Override
    public void hideFragment(String tag) {
        BaseFragment baseFragment = findFragmentByTag(tag);
        if (baseFragment != null) {
            getFragmentTransaction().hide(baseFragment).commitAllowingStateLoss();
        }
    }

    /**
     * Replace Fragment and true if added to backstack and false if not
     *
     * @param baseFragment
     * @param isAddToBackStack
     */
    @Override
    public void replaceFragment(BaseFragment baseFragment, boolean isAddToBackStack) {
        if (baseFragment != null) {
            //baseFragment.setAddToBackStack(isAddToBackStack);
            FragmentTransaction fragmentTransaction = getFm().beginTransaction();
            fragmentTransaction.replace(R.id.cl_root_view, baseFragment);
            fragmentTransaction.addToBackStack(baseFragment.getClass().getName());
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    /**
     * Replace Fragment and true if added to backstack and false if not and send Data using Bundle
     *
     * @param baseFragment
     * @param args
     * @param isAddToBackStack
     */
    @Override
    public void replaceFragment(BaseFragment baseFragment, Bundle args, boolean isAddToBackStack) {
        if (baseFragment != null) {
            baseFragment.setArguments(args);
            //baseFragment.setAddToBackStack(isAddToBackStack);
            FragmentTransaction fragmentTransaction = getFm().beginTransaction();
            fragmentTransaction.replace(R.id.cl_root_view, baseFragment);
            fragmentTransaction.addToBackStack(baseFragment.getClass().getName());
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    @Override
    public void replaceFragment(BaseFragment baseFragment, Bundle args, int id) {

    }

    /**
     * Replace Fragment
     *
     * @param baseFragment
     */
    @Override
    public void replaceFragment(BaseFragment baseFragment) {
        if (baseFragment != null) {
            FragmentTransaction fragmentTransaction = getFm().beginTransaction();
            fragmentTransaction.replace(R.id.cl_root_view, baseFragment);
            fragmentTransaction.addToBackStack(baseFragment.getClass().getName());
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    /**
     * Replace Fragment using View ID
     *
     * @param baseFragment
     * @param cointainerviewid
     */
    @Override
    public void replaceFragment(BaseFragment baseFragment, int cointainerviewid) {
        if (baseFragment != null) {
            FragmentTransaction fragmentTransaction = getFm().beginTransaction();
            fragmentTransaction.replace(cointainerviewid, baseFragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    /**
     * Replace Fragment and send data using Bundle
     *
     * @param baseFragment
     * @param args
     */
    @Override
    public void replaceFragment(BaseFragment baseFragment, Bundle args) {
        baseFragment.setArguments(args);
        FragmentTransaction fragmentTransaction = getFm().beginTransaction();
        fragmentTransaction.replace(R.id.cl_root_view, baseFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * get Currently displayed Fragment
     *
     * @return
     */
    @Override
    public BaseFragment getCurrentlyDisplayedFragment() {
        return (BaseFragment) getFm().findFragmentById(R.id.cl_root_view);
    }

    /**
     * get Currently displayed Fragment using layout id
     *
     * @return
     */
    @Override
    public BaseFragment getCurrentlyDisplayedFragment(int framelayout) {
        return (BaseFragment) getFm().findFragmentById(framelayout);
    }

    /**
     * get new_fragment_invitebymail using TAG
     *
     * @param tag
     * @return
     */
    @Override
    public BaseFragment findFragmentByTag(String tag) {
        return (BaseFragment) getFm().findFragmentByTag(tag);
    }

    /**
     * Remove all Fragments
     */
    @Override
    public void removeAllFragment() {
        FragmentManager fm = getFm();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

    /**
     * Go back i.e pop fragments
     */
    @Override
    public void goBack() {
        System.out.println("");
        if (getFm().getBackStackEntryCount() > 0) {
            getFm().popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Get Fragment Transaction
     *
     * @return
     */
    private FragmentTransaction getFragmentTransaction() {
        return getFm().beginTransaction();
    }

    /**
     * Get Fragment Managaer
     *
     * @return
     */
    public FragmentManager getFm() {
        return getSupportFragmentManager();
    }
}
