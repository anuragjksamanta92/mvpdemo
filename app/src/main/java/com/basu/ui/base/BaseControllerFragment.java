package com.basu.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basu.R;

public abstract class BaseControllerFragment extends BaseFragment  implements ControllerFragment{


    // Fragment Controller
    @Override
    public void addFragment(BaseFragment baseFragment, String tag) {
        if (baseFragment != null) {
            getChildFragmentManager().beginTransaction().add(R.id.cl_root_view, baseFragment, tag).commitAllowingStateLoss();
        }
    }

    @Override
    public void addFragment(BaseFragment baseFragment, Bundle args, String tag) {
        if (baseFragment != null) {
            baseFragment.setArguments(args);
            FragmentTransaction fragmentManager = getFm().beginTransaction();
            fragmentManager.add(R.id.cl_root_view, baseFragment, tag);
            fragmentManager.commitAllowingStateLoss();
        }
    }

    @Override
    public void showFragment(BaseFragment baseFragment, Bundle args) {
        if (baseFragment != null) {
            baseFragment.setArguments(args);
            FragmentTransaction fragmentTransaction =  getFm().beginTransaction();
            fragmentTransaction.show(baseFragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("!!!!!  rootView");
        View v = inflater.inflate(getonCreateView(), null, false);
        return v;
    }

    public abstract int getonCreateView();


    @Override
    public void removeFragment(String tag) {
        BaseFragment baseFragment = findFragmentByTag(tag);
        if (baseFragment != null) {
            getChildFragmentManager().beginTransaction().remove(baseFragment).commitAllowingStateLoss();
        }
    }

    @Override
    public void removeFragment(BaseFragment baseFragment) {
        if (baseFragment != null) {
            getChildFragmentManager().beginTransaction().remove(baseFragment).commitAllowingStateLoss();
        }
    }

    @Override
    public void showFragment(BaseFragment baseFragment) {
        if (baseFragment != null) {
            getChildFragmentManager().beginTransaction().show(baseFragment).commitAllowingStateLoss();
        }
    }

    @Override
    public void showFragment(String tag) {
        BaseFragment baseFragment = findFragmentByTag(tag);
        if (baseFragment != null) {
            getChildFragmentManager().beginTransaction().show(baseFragment).commitAllowingStateLoss();
        }
    }

    @Override
    public void hideFragment(BaseFragment baseFragment) {
        if (baseFragment != null) {
            getChildFragmentManager().beginTransaction().hide(baseFragment).commitAllowingStateLoss();
        }
    }

    @Override
    public void hideFragment(String tag) {
        BaseFragment baseFragment = findFragmentByTag(tag);
        if (baseFragment != null) {
            getChildFragmentManager().beginTransaction().hide(baseFragment).commitAllowingStateLoss();
        }
    }

    @Override
    public void replaceFragment(BaseFragment baseFragment, boolean isAddToBackStack) {
        if (baseFragment != null) {
            FragmentManager fragmentManager = getFm();
            //  baseFragment  =    ((BaseFragment) fragmentManager.findFragmentById(R.id.fl_main_cointainer));
            baseFragment.setAddToBackStack(isAddToBackStack);
            FragmentTransaction fragmentTransaction = getFm().beginTransaction();
            fragmentTransaction.replace(R.id.cl_root_view, baseFragment);
            fragmentTransaction.addToBackStack(baseFragment.getClass().getName());
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    @Override
    public void replaceFragment(BaseFragment baseFragment, Bundle args, boolean isAddToBackStack) {
        if (baseFragment != null) {
            FragmentManager fragmentManager = getFm();
            //    baseFragment  =    ((BaseFragment) fragmentManager.findFragmentById(R.id.fl_main_cointainer));
            baseFragment.setArguments(args);
            baseFragment.setAddToBackStack(isAddToBackStack);
            FragmentTransaction fragmentTransaction = getFm().beginTransaction();
            fragmentTransaction.replace(R.id.cl_root_view, baseFragment);
            fragmentTransaction.addToBackStack(baseFragment.getClass().getName());
            fragmentTransaction.commitAllowingStateLoss();
        }
    }


    @Override
    public void replaceFragment(BaseFragment baseFragment, Bundle args, int id) {
        if (baseFragment != null) {
            FragmentManager fragmentManager = getFm();
            baseFragment.setArguments(args);
            baseFragment.setAddToBackStack(isAddToBackStack);
            FragmentTransaction fragmentTransaction = getFm().beginTransaction();
            fragmentTransaction.replace(id, baseFragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    @Override
    public BaseFragment getCurrentlyDisplayedFragment() {
        return (BaseFragment) getFm().findFragmentById(R.id.cl_root_view);
    }
    @Override
    public BaseFragment getCurrentlyDisplayedFragment(int framelayout) {
        return (BaseFragment) getFm().findFragmentById(framelayout);
    }
    @Override
    public BaseFragment findFragmentByTag(String tag) {
        return (BaseFragment) getFm().findFragmentByTag(tag);
    }


    public void goBack() {
        System.out.println("goback");
        try {
            if (getFm().getBackStackEntryCount() > 0) {
                getFm().popBackStackImmediate();
            } else {
                getNavigationController().goBack();
            }
        } catch (Exception e) {
            e.printStackTrace();
            getActivity().onBackPressed();
        }
    }

    @Override
    public void replaceFragment(BaseFragment baseFragment) {
        FragmentManager fragmentManager = getFm();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.cl_root_view, baseFragment);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void replaceFragment(BaseFragment baseFragment, int id) {
        FragmentManager fragmentManager = getFm();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(id, baseFragment);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void replaceFragment(BaseFragment baseFragment, Bundle args) {
        baseFragment.setArguments(args);
        FragmentManager fragmentManager = getFm();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.cl_root_view, baseFragment);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void removeAllFragment() {

    }

    private FragmentManager getFm() {
        return getFragmentManager();
    }


}
