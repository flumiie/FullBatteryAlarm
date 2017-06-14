package xyz.fandrew.fullbatteryalarm;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ncapdevi.fragnav.FragNavController;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity //implements FragNavController.RootFragmentListener
{
    private FragNavController mFragNavController;
    private FrameLayout container;
    private FragmentTransaction fragTransact;
    private FragmentManager fragmentManager;
    private int i = 0, j = 0, k = 0;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FragNavController.Builder builder =
//                FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.main_container);
//
//        final List<Fragment> fragments = new ArrayList<>(3);
//
//        fragments.add(FragmentHome.newInstance(0));
//        fragments.add(FragmentSettings.newInstance(0));
//        fragments.add(FragmentAbout.newInstance(0));
//
//        // Link fragments to container
//        builder.rootFragments(fragments);
//        mFragNavController = builder.build();

        BottomBar mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener()
        {
            @Override
            public void onTabSelected(@IdRes int tabId)
            {
                fragTransact = getSupportFragmentManager().beginTransaction();
                if (tabId == R.id.tab_home)
                {
                    fragTransact.replace(R.id.fragment_container, new FragmentHome());
                    fragTransact.commit();
                }
                if(tabId == R.id.tab_settings)
                {
                    fragTransact.replace(R.id.fragment_container, new FragmentSettings());
                    fragTransact.commit();
                }
                if(tabId == R.id.tab_about)
                {
                    fragTransact.replace(R.id.fragment_container, new FragmentAbout());
                    fragTransact.commit();
                }
            }
        });

        mBottomBar.setOnTabReselectListener(new OnTabReselectListener()
        {
            @Override
            public void onTabReSelected(@IdRes int tabId)
            {
                Toast.makeText(getApplicationContext(), TabContents.get(tabId, true), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        if (mFragNavController.getCurrentStack().size() > 1) mFragNavController.popFragment();
        else super.onBackPressed();
    }

//    @Override
//    public Fragment getRootFragment(int index)
//    {
//        switch (index)
//        {
//            case R.id.tab_home:
//                return FragmentHome.newInstance(0);
//            case R.id.tab_settings:
//                return FragmentSettings.newInstance(0);
//            case R.id.tab_about:
//                return FragmentAbout.newInstance(0);
//        }
//        throw new IllegalStateException("Need to send an index that we know");
//    }
}