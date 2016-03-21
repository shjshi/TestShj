package com.shj.shjtest.animtor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.shj.shjtest.R;
import com.shj.shjtest.animtor.view.MyAnimationView1;

public class Anim1Activity extends AppCompatActivity {

    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim1);
        ll = ((LinearLayout) findViewById(R.id.ll));
        ll.addView(new MyAnimationView1(Anim1Activity.this));

    }
}
