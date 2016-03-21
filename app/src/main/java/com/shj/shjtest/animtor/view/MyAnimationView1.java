
package com.shj.shjtest.animtor.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import java.util.ArrayList;

/**
 * Created by shj on 2016/3/12 0012.
 * ALT+inster 生成getset等方法的快捷键
 */
public class MyAnimationView1 extends View implements ValueAnimator.AnimatorUpdateListener {
    //定义椭圆的宽高
    private static final float BALL_SIZE = 50F;
    //时间
    private static final float FULL_TIME = 1000;
    //定义一个集合来装小球，之后从这个集合中拿出小球，或remove都好
    public final ArrayList<ShapeHolder> balls = new ArrayList<>();

    //
    public MyAnimationView1(Context context) {
        super(context);
        //改变了整个画布的颜色,我自定义的view不是小球，而是一个矩形的view，只是可以产生小球
        setBackgroundColor(Color.WHITE);
    }

    public MyAnimationView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.WHITE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN && event.getAction() != MotionEvent.ACTION_MOVE) {
            return false;
        }
        //每次触碰都创建一个球
        ShapeHolder newBall = addBall(event.getX(), event.getY());
        //计算小球开始下落动画开始的y坐标
        float startY = newBall.getY();
        //计算小球下落到底端的坐标
        float endY = getHeight() - BALL_SIZE;
        //获取屏幕的高度,准确来说是父亲布局的高度
        float h = getHeight();
        float eventY = event.getY();
        //计算动画持续的时间
        int duration = (int) (2000 * ((h - eventY) / h));  //这个不是0吗？
        //定义小球落下的动画
        ValueAnimator fallAnim = ObjectAnimator.ofFloat(newBall, "y", startY, endY);
        //时间
        fallAnim.setDuration(duration);
        //插值器
        fallAnim.setInterpolator(new AccelerateInterpolator());
        //监听器
        fallAnim.addUpdateListener(this);
        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(newBall, "alpha", 1f, 0f);
        //持续时间
        fadeAnim.setDuration(150);
        //监听器
        fadeAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                balls.remove(((ObjectAnimator) animation).getTarget());
            }
        });
        fadeAnim.addUpdateListener(this);
        AnimatorSet set = new AnimatorSet();
        set.play(fallAnim).before(fadeAnim);
        set.start();
        return true;
    }

    //定义一个方法
    private ShapeHolder addBall(float x, float y) {
        //创建一个椭圆
        OvalShape circle = new OvalShape();
        //设置椭圆的宽高
        circle.resize(BALL_SIZE, BALL_SIZE);
        //将椭圆包装成drawable对象
        ShapeDrawable drawable = new ShapeDrawable(circle);
        //创建一个ShapeHolder对象
        ShapeHolder holder = new ShapeHolder(drawable);
        //每一个控件的坐标都是从左上角算的
        //在android中每一个图像都是方形的，所以，如果是圆的话，手点击的坐标是小球所在矩形的左上角的坐标
        //要想让小球的中心是手点击的坐标，那么x，y坐标都要再向上，左移动小球半径的距离
        holder.setX(x - BALL_SIZE / 2);
        holder.setY(y - BALL_SIZE / 2);
        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        //三个随机数组成argb颜色     ?
        int color = 0xff000000 + red << 16 | green << 8 | blue;
        //获取drawable上关联的画笔
        Paint paint = drawable.getPaint();
        //将rgb三个随机数除以四得到的商值组合成argb颜色
        int darkColor = 0xff000000 | red / 4 << 16 | green / 4 << 8 | blue / 4;
        //创建圆形渐变，参数？
        RadialGradient gradient = new RadialGradient(37.5f, 12.5f, BALL_SIZE, color, darkColor, Shader.TileMode.CLAMP);
        paint.setShader(gradient);
        //为shapeHolder设置画笔
        holder.setPaint(paint);
        //每创建一个就放到集合中
        balls.add(holder);
        return holder;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        this.invalidate();
    }

    //定义一个ShapeHolder类
    public class ShapeHolder {
        private float x = 0;
        private float y = 0;
        //?
        private ShapeDrawable shapeDrawable;
        private int color;
        //?
        private RadialGradient gradient;
        //?
        private float alpha = 1f;
        private Paint paint;

        public ShapeHolder(ShapeDrawable s) {
            this.shapeDrawable = s;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public ShapeDrawable getShapeDrawable() {
            return shapeDrawable;
        }

        public void setShapeDrawable(ShapeDrawable shapeDrawable) {
            this.shapeDrawable = shapeDrawable;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public RadialGradient getGradient() {
            return gradient;
        }

        public void setGradient(RadialGradient gradient) {
            this.gradient = gradient;
        }

        public float getAlpha() {
            return alpha;
        }

        public void setAlpha(float alpha) {
            this.alpha = alpha;
        }

        public Paint getPaint() {
            return paint;
        }

        public void setPaint(Paint paint) {
            this.paint = paint;
        }

        public float getX() {

            return x;
        }

        public void setX(float x) {
            this.x = x;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画圆
        for (ShapeHolder shapeHolder : balls) {
            canvas.save();
            canvas.translate(shapeHolder.getX(), shapeHolder.getY());
            shapeHolder.getShapeDrawable().draw(canvas);
            canvas.restore();
        }


    }
}
