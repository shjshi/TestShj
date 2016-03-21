package com.shj.shjtest.layout.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by shj on 2016/3/16 0016.
 * 1 重写构造方法
 * 2 画笔
 * 3 onDraw
 */
public class MyView1 extends View {
    private float mCurrentX = 70;
    private float mCurrentY = 70;
    private Paint paint = new Paint();//画笔在哪里初始化,就在这里？

    public MyView1(Context context) {
        super(context);
    }

    public MyView1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        canvas.drawCircle(mCurrentX, mCurrentY, 70, paint);//以(x,y)为原点坐标，70为半径画圆

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //为啥没有event.getAction();?
        mCurrentX = event.getX();
        mCurrentY = event.getY();
        invalidate();
        return true;
    }
}
