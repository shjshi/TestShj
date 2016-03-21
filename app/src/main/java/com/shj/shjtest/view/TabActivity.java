package com.shj.shjtest.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shj.shjtest.R;

public class TabActivity extends AppCompatActivity {

    private AddSubView add_sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_sub_activity);
        add_sub = ((AddSubView) findViewById(R.id.as));
        add_sub.setValue(1);

    }
}
