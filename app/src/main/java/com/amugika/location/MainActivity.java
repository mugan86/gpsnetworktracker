package com.amugika.location;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean comeback = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        find();
    }

    public void find()
    {
        GPSNetworkTracker gps = new GPSNetworkTracker(MainActivity.this);
        if(gps.canGetLocation()){

            Toast.makeText(getApplicationContext(), String.valueOf(gps.getLocation().getLatitude()), Toast.LENGTH_LONG).show();
        }else{

            String message_title = "Kokapen konfigurazioa";
            String message = "Enable";
            String config_btn = "Konfiguratu";
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert(message_title, message, config_btn);
        }
    }
    @Override
    public void onResume()
    {
        super.onResume();
        Log.i("onResume","onResume call");

        if (comeback) find();
    }

    public void onPause()
    {
        super.onPause();
        Log.i("onPause", "onPause call");
        comeback = true;
    }

    @Override
    public void onRequestPermissionsResult(
            int rCode,
            @NonNull String [] permissions,
            @NonNull int[] res
    )
    {
        if(rCode == 198) //Permission GRANTED
        {

            if (res[0] == PackageManager.PERMISSION_GRANTED)
            {
               find();
            }
            else
            {

            }

        }
    }



}
