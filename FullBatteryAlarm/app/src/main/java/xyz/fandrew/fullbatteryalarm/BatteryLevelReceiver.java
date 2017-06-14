package xyz.fandrew.fullbatteryalarm;

/**
 * Created by Ferick Andrew on Jun 01, 2017.
 */

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

/**
 * receives broadcasts about the battery level
 */
public class BatteryLevelReceiver extends BroadcastReceiver
{
    private final boolean V_LOG = false;
    private final String TAG = "BatteryLevelReceiver";

    @Override
    public void onReceive(Context context, Intent intent)
    {

        // check to see what level we're being informed about
        if(V_LOG) { Log.v(TAG, "onReceive method called"); }

        // check on the action associated with the intent
        if(intent.getAction().equals(Intent.ACTION_BATTERY_LOW))
        {
            // notification that the battery is low
            if(V_LOG) { Log.v(TAG, "received notification that battery is low"); }

            // stop any of your services here

            // inform the user
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
            mBuilder.setMessage("Low battery")
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            dialog.cancel();
                        }
                    });
            AlertDialog mAlert = mBuilder.create();
            mAlert.show();

        } else if(intent.getAction().equals(Intent.ACTION_BATTERY_OKAY)) {
            // notification that the battery is ok after being low
            // restart the GPS
            if(V_LOG) {
                Log.v(TAG, "received notification that battery is ok");
            }

            //start any of your services here

            // inform the user
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
            mBuilder.setMessage("Battery level is OK")
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            dialog.cancel();
                        }
                    });
            AlertDialog mAlert = mBuilder.create();
            mAlert.show();
        }
    }
}