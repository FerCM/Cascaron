package com.fiscaliageneralags.fiscalia.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ERodriguezF on 01/12/2017.
 * @author ERodriguezF
 * @version 1.18
 */

public class MainMenuPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList =  new ArrayList<>();
    private List<String> mFragmentTitleList =  new ArrayList<>();

    public MainMenuPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment( String title, Fragment fragment) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
