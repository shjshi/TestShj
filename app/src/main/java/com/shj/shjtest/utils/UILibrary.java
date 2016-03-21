package com.shj.shjtest.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.view.Display;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by shj on 2016/3/7 0007.
 */
public class UILibrary {
    /**
     * 设置内容为列表的对话框的最大高度(如果对话框列表项太多，通过此方法设置其最大高度，竖屏距离顶部和底部50dip，横屏距离顶部和底部20dip)
     */
    public static void setDialogMaxHeight(final Context context,final ListView listView,final int edgeHeight){
        ViewTreeObserver vto2 = listView.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                listView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int height = listView.getHeight();
                WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) listView.getLayoutParams();
                int maxHeight;
                if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    maxHeight = display.getHeight() - getStatusBarHeight(context) - dip2px(context, 40) - edgeHeight;
                }else {
                    maxHeight = display.getHeight() - getStatusBarHeight(context) - dip2px(context, 100) - edgeHeight;
                }
                if (height > maxHeight) {
                    params.height = maxHeight;
                    listView.setLayoutParams(params);
                }
            }
        });
    }
    /**
     * 得到状态栏高度的方法
     * @param context
     * @return 画页状态栏高度
     */
    public static int getStatusBarHeight(Context context){
        int statusBarHeight = 0;
        Class<?> localClass;
        try {
            localClass = Class.forName("com.android.internal.R$dimen");
            Object localObject = localClass.newInstance();
            int x = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }
    /**
     * dip转换成px
     *
     * @param context
     * @param dipValue
     * @return px
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density; // 设备的密度
        return (int) (dipValue * scale + 0.5f);
    }
}
