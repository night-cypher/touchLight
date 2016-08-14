package com.questdot.l.torchlight;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.hardware.Camera.Parameters;

public class MyService extends Service {

    private  Camera camera;
    boolean torch = false;

    private Parameters params;
    @Override
    public void onCreate() {
        super.onCreate();



    }



    private void turnOnFlash() {

        if(torch==false){
            camera = Camera.open();
            params = camera.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview();
            torch=true;
            foregroundNotification();
        }


    }


    public void turnOffFlash() {
        if(torch==true) {
            params = camera.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            camera.stopPreview();
            camera.release();
            torch=false;
        }


    }

    public void foregroundNotification(){
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        RemoteViews notificationView = new RemoteViews(this.getPackageName(),R.layout.notification);


        Intent buttonCloseIntent = new Intent(this, NotificationCloseButtonHandler.class);
        buttonCloseIntent.putExtra("action", "close");

        PendingIntent buttonClosePendingIntent = pendingIntent.getBroadcast(this, 0, buttonCloseIntent, 0);
        notificationView.setOnClickPendingIntent(R.id.notification_button_close, buttonClosePendingIntent);


        PendingIntent onClickPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);


        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Test")
                .setTicker("Test")
                .setContentText("Test")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(onClickPendingIntent)
                .setLargeIcon(
                        Bitmap.createScaledBitmap(icon, 128, 128, false))
                .setContent(notificationView)
                .setOngoing(true).build();



        startForeground(101,
                notification);



    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        turnOnFlash();
      //  Toast.makeText(getApplicationContext(),"sdsa",Toast.LENGTH_SHORT).show();

        return START_STICKY;
    }



    @Override
    public IBinder onBind(Intent intent) {
        // Used only in case of bound services.
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
        turnOffFlash();


    }
}