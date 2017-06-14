package xyz.fandrew.fullbatteryalarm.whatis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import xyz.fandrew.fullbatteryalarm.R;

public class HelpPowerSourceActivity extends AppCompatActivity
{
    TextView explanations;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_power_source);

        explanations = (TextView) findViewById(R.id.text_view_power_source_explanation);
        explanations.setText(R.string.what_is_power_source);
    }
}
