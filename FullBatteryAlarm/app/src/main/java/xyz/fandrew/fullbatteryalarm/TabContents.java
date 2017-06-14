package xyz.fandrew.fullbatteryalarm;

/**
 * Created by iiro on 7.6.2016.
 */
public class TabContents {
    public static String get(int menuItemId, boolean isReselection)
    {
        switch (menuItemId)
        {
            case R.id.tab_home:
                new FragmentHome();
                break;
            case R.id.tab_settings:
                new FragmentSettings();
                break;
            case R.id.tab_about:
                new FragmentAbout();
                break;
        }

        //if (isReselection) {}

        return null;
    }
}
