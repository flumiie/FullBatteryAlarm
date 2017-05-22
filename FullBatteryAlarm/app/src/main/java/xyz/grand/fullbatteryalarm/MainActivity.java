package xyz.grand.fullbatteryalarm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private TextView mTextMessage;
    private BottomNavigationView mBottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        mTextMessage = (TextView) findViewById(R.id.mTextMessage);
        mBottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        mBottomNavigation.getMaxItemCount();
        mBottomNavigation.inflateMenu(R.menu.navigation);
        mBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.nav_home);
                    return true;
                case R.id.navigation_settings:
                    mTextMessage.setText(R.string.nav_settings);
                    return true;
                case R.id.navigation_about:
                    mTextMessage.setText(R.string.nav_about);
                    return true;
            }
            return false;
        }

    };
}