package com.pradeep.broadcastexample;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyBroadcastReceiver myBroadcastReceiver;
    private Button btnStartBroadcast;
    LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartBroadcast = findViewById(R.id.btnStartBroadcast);
        myBroadcastReceiver = new MyBroadcastReceiver();
        localBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());

        btnStartBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setAction("com.custom.action.tag.sms.read");
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter("com.custom.action.tag.sms.read");
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(myBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myBroadcastReceiver);
    }
}
