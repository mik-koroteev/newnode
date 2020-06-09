package com.clostra.newnode.vpn;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ProxyInfo;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import com.clostra.newnode.NewNode;

public class VpnService extends android.net.VpnService implements Handler.Callback {
    private static final String TAG = VpnService.class.getSimpleName();

    public static final String ACTION_CONNECT = "com.clostra.newnode.vpn.START";
    public static final String ACTION_DISCONNECT = "com.clostra.newnode.vpn.STOP";

    private Handler mHandler;
    private PendingIntent mConfigureIntent;

    @Override
    public void onCreate() {
        // The handler is only used to show messages.
        if (mHandler == null) {
            mHandler = new Handler(this);
        }

        NewNode.init();

        mConfigureIntent = PendingIntent.getActivity(this, 0, new Intent(this, VpnActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        TODO: protect sockets


        Builder builder = new Builder();
        builder.addAddress("10.7.0.3", 32);
        builder.addRoute("0.0.0.0", 0);
        builder.addDnsServer("1.0.0.1");
        builder.setSession("NewNode");
        String proxyHost = System.getProperty("proxyHost");
        int proxyPort = Integer.parseInt(System.getProperty("proxyPort"));
        builder.setHttpProxy(ProxyInfo.buildDirectProxy(proxyHost, proxyPort));
        Log.i(TAG, "builder:" + builder);
        ParcelFileDescriptor p = builder.establish();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && ACTION_DISCONNECT.equals(intent.getAction())) {
            disconnect();
            return START_NOT_STICKY;
        } else {
            connect();
            return START_STICKY;
        }
    }

    @Override
    public void onDestroy() {
        disconnect();
    }

    @Override
    public boolean handleMessage(Message message) {
        Toast.makeText(this, message.what, Toast.LENGTH_SHORT).show();
        if (message.what != R.string.disconnected) {
            updateForegroundNotification(message.what);
        }
        return true;
    }

    private void connect() {
        // Become a foreground service. Background services can be VPN services too, but they can
        // be killed by background check before getting a chance to receive onRevoke().
        updateForegroundNotification(R.string.connecting);
        mHandler.sendEmptyMessage(R.string.connecting);

    }

    private void disconnect() {
        mHandler.sendEmptyMessage(R.string.disconnected);
        stopForeground(true);
    }

    private void updateForegroundNotification(final int message) {
        final String NOTIFICATION_CHANNEL_ID = "NewNode VPN";
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        mNotificationManager.createNotificationChannel(new NotificationChannel(
                NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_ID,
                NotificationManager.IMPORTANCE_DEFAULT));
        startForeground(1, new Notification.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_vpn)
                .setContentText(getString(message))
                .setContentIntent(mConfigureIntent)
                .build());
    }
}