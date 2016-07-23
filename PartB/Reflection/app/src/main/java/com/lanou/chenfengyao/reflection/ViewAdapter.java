package com.lanou.chenfengyao.reflection;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ChenFengYao on 16/7/19.
 */
public class ViewAdapter extends FragmentPagerAdapter {
    public ViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return TestFragment.instance(position);
    }

    @Override
    public int getCount() {
        return 10;
    }
}
