package com.shj.shjtest.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.shj.shjtest.R;
import com.shj.shjtest.utils.UILibrary;

import java.util.List;

/**
 * Created by shj on 2016/3/15 0015.
 * 1 extends Dialog
 * 有时间好好研究
 */
public class ListDialog extends Dialog {
    private Context context;
    private ListView listView;
    private List<String> list;
    private ArrayAdapter<String> adapter;

    public ListDialog(Context context, int theme, List<String> list) {
        super(context, theme);
        this.context = context;
        this.list = list;
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_dialog_listview);
        initDialog();
        UILibrary.setDialogMaxHeight(context, listView, (int) context.getResources().getDimension(R.dimen.dialog_top_title_height));
    }

    private void initDialog() {
        TextView title = (TextView) this.findViewById(R.id.dialogTitle);
        //title.setText(context.getResources().getString(R.string.countySelect));
        this.listView = (ListView) this.findViewById(R.id.listView);

        if (this.adapter == null) {
            this.adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list);
            this.listView.setAdapter(this.adapter);
        } else {
            this.adapter.notifyDataSetChanged();
        }

    }
}
