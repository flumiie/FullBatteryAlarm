package xyz.fandrew.fullbatteryalarm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentAbout extends Fragment
{
    public static FragmentAbout newInstance(int instance)
    {
        Bundle args = new Bundle();
        args.putInt("aboutInstance", instance);
        FragmentAbout thirdFragment = new FragmentAbout();
        thirdFragment.setArguments(args);
        return thirdFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);

        return rootView;
    }
}
