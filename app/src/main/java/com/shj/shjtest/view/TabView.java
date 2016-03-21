package com.shj.shjtest.view;

import android.app.Service;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.shj.shjtest.R;

/**
 * Created by shj on 2016/3/18 0018.
 */
public class TabView extends LinearLayout {
    public Context context;
    public TabView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    private void initView() {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);

        View v = layoutInflater.inflate(R.layout.tab, this, true);
    }

}
