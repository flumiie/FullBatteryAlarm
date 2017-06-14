package xyz.fandrew.fullbatteryalarm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

public class FragmentSettings extends Fragment
{
    private Switch darkTheme;

    public static FragmentSettings newInstance(int instance)
    {
        Bundle args = new Bundle();
        args.putInt("settingsInstance", instance);
        FragmentSettings secondFragment = new FragmentSettings();
        secondFragment.setArguments(args);
        return secondFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        darkTheme = (Switch) rootView.findViewById(R.id.toggle_dark_theme);

        AppCompatDelegate.getDefaultNightMode();
        darkTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    getActivity().setTheme(R.style.AppTheme_Dark);
                }
                else
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    getActivity().setTheme(R.style.AppTheme);
                }
            }
        });



        return rootView;
    }
}
