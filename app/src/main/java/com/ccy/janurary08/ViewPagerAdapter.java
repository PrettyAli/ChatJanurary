package com.ccy.janurary08;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by chanchaoyue on 2018/1/8.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter{

    private Context mContext;
    private List<SimpleFragment> mFragments;

    public ViewPagerAdapter(FragmentManager fm,Context context,List<SimpleFragment> simpleFragments)
    {
        super(fm);
        mContext=context;
        mFragments=simpleFragments;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}
