package kumar.broadcastreceiver;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView hour,min,sec;
    SharedPreferences settings;
    private String PREFS_NAME="key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hour=(TextView)findViewById(R.id.hours);
        min=(TextView)findViewById(R.id.Minutes);
        sec=(TextView)findViewById(R.id.Seconds);
        settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
       long hours=settings.getLong("hours",0);
        long minutes=settings.getLong("minutes",0);
        long seconds=settings.getLong("seconds",0);
        hour.setText(String.valueOf(hours));
        min.setText(String.valueOf(minutes));
        sec.setText(String.valueOf(seconds));

    }
}
