package com.shj.shjtest.listview;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.shj.shjtest.R;
import com.shj.shjtest.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shj on 2016/3/17 0017.
 */
public class Test1 extends Activity {
    private ListView lv;
    private List<String> list;
    private int curPosition = 0;
    private int lastPosition;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview1);
        initData();
        lv = ((ListView) findViewById(R.id.lv));
         adapter = new MyAdapter();
        lv.setAdapter(adapter);
        initListener();
    }

    private void initListener() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                curPosition = position;
                if (curPosition+"" != SPUtils.get(Test1.this,"lastPosition",0+"")){
                    new AlertDialog.Builder(Test1.this)
                        .setTitle("标题")
                        .setMessage("简单消息框")
                        .setPositiveButton("确定", null)
                        .show();
                }else{
                    adapter.notifyDataSetChanged();
                }

            }
        });
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("shj" + i);
        }
    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = View.inflate(Test1.this, R.layout.lv_textview, null);
            }
            TextView tv = (TextView) convertView.findViewById(R.id.tv);
            tv.setText(list.get(position));
            if(curPosition == position){
                tv.setTextColor(Color.RED);
                SPUtils.put(Test1.this, "lastPosition", position+"");
            }else{
//
                tv.setTextColor(Color.BLACK);
            }
            return convertView;
        }
    }
}
