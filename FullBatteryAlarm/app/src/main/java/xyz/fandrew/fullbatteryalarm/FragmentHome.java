package xyz.fandrew.fullbatteryalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import xyz.fandrew.fullbatteryalarm.whatis.HelpBatteryTypeActivity;
import xyz.fandrew.fullbatteryalarm.whatis.HelpPowerSourceActivity;

public class FragmentHome extends Fragment
{
    private TextView batStatus, powerSource, batLevel, batHealth, batVolt, batTemp, batType;
    private ImageView iconStatus, iconHealth;
    private ImageButton buttonHelpPowerSource, buttonHelpBatteryType;
    private IntentFilter iFilter;

    public static FragmentHome newInstance(int instance)
    {
        Bundle args = new Bundle();
        args.putInt("homeInstance", instance);
        FragmentHome firstFragment = new FragmentHome();
        firstFragment.setArguments(args);
        return firstFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        iconStatus   = (ImageView) rootView.findViewById(R.id.battery_status_icon);
        batStatus    = (TextView) rootView.findViewById(R.id.text_view_battery_status_value);
        powerSource  = (TextView) rootView.findViewById(R.id.text_view_power_source_value);
        batLevel     = (TextView) rootView.findViewById(R.id.text_view_battery_level_value);
        iconHealth   = (ImageView) rootView.findViewById(R.id.battery_health_icon);
        batHealth    = (TextView) rootView.findViewById(R.id.text_view_health_status_value);
        batVolt      = (TextView) rootView.findViewById(R.id.text_view_battery_voltage_value);
        batTemp      = (TextView) rootView.findViewById(R.id.text_view_battery_temp_value);
        batType      = (TextView) rootView.findViewById(R.id.text_view_battery_type_value);

        buttonHelpPowerSource = (ImageButton) rootView.findViewById(R.id.button_what_is_power_source);
        buttonHelpBatteryType = (ImageButton) rootView.findViewById(R.id.button_what_is_battery_type);

        iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        final BroadcastReceiver receiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                // Battery Status (Charging state)
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                        status == BatteryManager.BATTERY_STATUS_FULL;

                // How are we charging?
                int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
                boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
                boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

                if(!(usbCharge) && !(acCharge)) powerSource.setText("Currently no power source" + "" + "");
                if(usbCharge) powerSource.setText("USB" + "" + "");
                if(acCharge) powerSource.setText("AC" + "" + "");

                // Monitor Significant Changes in Battery Level
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

                float batteryPct = level / (float)scale;
                if(isCharging)
                {
                    iconStatus.setImageResource(R.drawable.ic_battery_charging_full_black_24dp);
                    batStatus.setText("Charging" + "" + "");
                }
                else if(batteryPct == 100) batStatus.setText("Fully Charged" + "" + "");
                else
                {
                    iconStatus.setImageResource(R.drawable.ic_battery_full_black_24dp);
                    batStatus.setText("Unplugged" + "" + "");
                }

                if(batteryPct*100 >= 80.0) batLevel.setTextColor(Color.rgb(0, 220, 85));
                else if(batteryPct*100 >= 60.0 && batteryPct*100 < 80.0) batLevel.setTextColor(Color.rgb(120, 205, 35));
                else if(batteryPct*100 >= 40.0 && batteryPct*100 < 60.0) batLevel.setTextColor(Color.rgb(230, 187, 17));
                else if(batteryPct*100 >= 20.0 && batteryPct*100 < 40.0) batLevel.setTextColor(Color.rgb(228, 100, 15));
                else if(batteryPct*100 >=  0.0 && batteryPct*100 < 20.0) batLevel.setTextColor(Color.rgb(227, 15, 15));
                batLevel.setText(batteryPct*100 + "%" + "");

                // Monitor Battery Health
                int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
                switch(health)
                {
                    case BatteryManager.BATTERY_HEALTH_COLD:
                        batHealth.setTextColor(Color.CYAN);
                        batHealth.setText("Cold" + "" + "");
                        break;

                    case BatteryManager.BATTERY_HEALTH_DEAD:
                        batHealth.setTextColor(Color.rgb(227, 15, 15));
                        iconHealth.setImageResource(R.drawable.ic_battery_health_bad);
                        batHealth.setText("Dead" + "" + "");
                        break;

                    case BatteryManager.BATTERY_HEALTH_GOOD:
                        batHealth.setTextColor(Color.rgb(0, 220, 85));
                        iconHealth.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_battery_health_good));
                        iconHealth.setImageResource(R.drawable.ic_battery_health_good);
                        batHealth.setText("Good" + "" + "");
                        break;

                    case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                        batHealth.setTextColor(Color.rgb(227, 15, 15));
                        batHealth.setText("Overvoltage" + "" + "");
                        break;

                    case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                        batHealth.setTextColor(Color.rgb(227, 15, 15));
                        batHealth.setText("Overheat" + "" + "");
                        break;

                    case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                        batHealth.setTextColor(Color.DKGRAY);
                        batHealth.setText("Unknown" + "" + "");
                        break;

                    case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                        batHealth.setTextColor(Color.rgb(227, 15, 15));
                        iconHealth.setImageResource(R.drawable.ic_battery_alert_black_24dp);
                        batHealth.setText("Failure" + "" + "");
                        break;

                    default:
                        break;
                }

                // Monitor Battery Voltage
                int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
                batVolt.setText(voltage + " mV" + "");

                // Monitor Battery Temperature
                int temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
                batTemp.setText(temp/100 + " C" + "");

                // Seek Battery Technology
                String batteryType = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
                batType.setText(batteryType + "" + "");
            }
        };

        // Call the function
        getActivity().registerReceiver(receiver, iFilter);

        buttonHelpPowerSource.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                helpPowerSource(v);
            }
        });

        buttonHelpBatteryType.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                helpBatteryType(v);
            }
        });

        return rootView;
    }

    public void helpPowerSource (View view)
    {
        startActivity(new Intent(getActivity(), HelpPowerSourceActivity.class));
    }

    public void helpBatteryType (View view)
    {
        startActivity(new Intent(getActivity(), HelpBatteryTypeActivity.class));
    }
}
