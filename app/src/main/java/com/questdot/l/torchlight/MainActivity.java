package com.questdot.l.torchlight;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    ImageButton btnSwitch,btnSwitch2;
  //  public static Context context;
    Boolean click=true;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // flash switch button
        btnSwitch = (ImageButton) findViewById(R.id.btnSwitch);
        btnSwitch2 = (ImageButton) findViewById(R.id.btnSwitch2);
        final Intent startIntent = new Intent(MainActivity.this, MyService.class);
        //context=this;
        btnSwitch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                   startService(startIntent);

            }
        });



        btnSwitch2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                stopService(startIntent);

            }
        });
    }

    /*
     * Get the camera
     */


    /*
     * Turning On flash
     */


    /*
     * Toggle switch button images
     * changing image states to on / off
     * */


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }


    @Override
    protected void onStart() {
        super.onStart();

        // on starting the app get the camera params

    }


}
