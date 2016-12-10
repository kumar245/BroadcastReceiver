package kumar.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by User on 10/16/2016.
 */

public class MyReceiver extends BroadcastReceiver {

    private String PREFS_NAME = "key";
    SharedPreferences settings;
    @Override
    public void onReceive(Context context, Intent intent) {
    settings = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
            Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
            long currentTime = System.currentTimeMillis();
            // Writing data to SharedPreferences
            SharedPreferences.Editor editor = settings.edit();
            editor.putLong("currentTime",currentTime);
            Log.d("currntT in connected", ""+currentTime);
            editor.commit();




        }
else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
            long currentTime = System.currentTimeMillis();
            Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
            long savedTime = settings.getLong("currentTime",0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putLong("record",settings.getLong("record",0)+currentTime-savedTime).commit();
            //editor.putLong("record",0).commit();
            long rest = currentTime-savedTime;
            showTime(rest);
            showTime(settings.getLong("record",0));

        }
    }
    public void showTime(long millis){
        long second = (millis / 1000) % 60;
        long minute = (millis / (1000 * 60)) % 60;
        long hour = (millis / (1000 * 60 * 60)) % 24;
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong("hours",hour);
        editor.putLong("minutes",minute);
        editor.putLong("seconds",second);
        editor.commit();

    }
}
