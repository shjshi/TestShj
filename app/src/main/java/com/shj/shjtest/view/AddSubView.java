package com.shj.shjtest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shj.shjtest.R;

/**
 * Created by shj on 2016/3/18 0018.
 */
public class AddSubView extends LinearLayout {
    private Context context;
    private Button sub;
    private EditText et;
    private Button add;
    private int i ;

    public AddSubView(Context context) {
        super(context);
        this.context = context;
        initView();
        add();
        sub();
    }

    private void initView() {
        View v = View.inflate(context, R.layout.add_sub, this);
        add = ((Button) v.findViewById(R.id.add));
        sub = (Button) v.findViewById(R.id.sub);
        et = (EditText) v.findViewById(R.id.et);
        String s = i+"";
        et.setText(s);
    }

    public AddSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
        add();
        sub();
    }

    public void add() {
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                String s = i+"";
                et.setText(s);
            }
        });
    }

    public void sub() {
        sub.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>0){
                    i--;
                }else{
                    i=0;
                }
                String s = i+"";
                et.setText(s);
            }
        });
    }
    public void setValue(int i){
        this.i = i;
    }

}
