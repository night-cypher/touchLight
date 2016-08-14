package com.questdot.l.torchlight;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by HP on 9/6/2016.
 */
public class NotificationCloseButtonHandler extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Close Clicked", Toast.LENGTH_SHORT).show();
        context.stopService(new Intent(context, MyService.class));
    }
}
