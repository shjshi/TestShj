package com.shj.shjtest.brocatreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shj.shjtest.R;

/**
 * Created by shj on 2016/3/15 0015.
 *
 */
public class MyReceiver1Activity extends Activity {
    private Button bt;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receiver1);

        initComponent();
        initListener();
    }

    private void initListener() {
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("bt_textView");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyReceiver1 receiver1 = new MyReceiver1();
        IntentFilter filter = new IntentFilter();
        filter.addAction("shj");
        registerReceiver(receiver1, filter);
        Intent intent = new Intent("shj");
        sendBroadcast(intent);
    }

    private void initComponent() {
        bt = ((Button) findViewById(R.id.bt));
        tv = ((TextView) findViewById(R.id.tv));
    }

    class MyReceiver1 extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
           intent.setAction("shj");
            sendBroadcast(intent);
            tv.setText("receiver_textview");
        }
    }
}
