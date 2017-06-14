package xyz.fandrew.fullbatteryalarm;

/**
 * Created by Ferick Andrew on Jun 05, 2017.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class SectionPagesAdapter extends FragmentPagerAdapter
{

    private final List<Fragment> mFragmentList = new ArrayList<>();


    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }

    public SectionPagesAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}