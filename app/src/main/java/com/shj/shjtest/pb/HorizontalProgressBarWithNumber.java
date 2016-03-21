package com.shj.shjtest.pb;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import com.shj.shjtest.R;


public class HorizontalProgressBarWithNumber extends ProgressBar
{
	/**
	 * 常量
	 * 成员变量
	 * 构造方法
	 * 自动回掉的成员变量
	 *
	 */
	//默认的text_size
	private static final int DEFAULT_TEXT_SIZE = 10;
	//默认的text_color
	private static final int DEFAULT_TEXT_COLOR = 0XFFFC00D1;
	//默认的未到达的颜色
	private static final int DEFAULT_COLOR_UNREACHED_COLOR = 0xFFd3d6da;
	//reached
	private static final int DEFAULT_HEIGHT_REACHED_PROGRESS_BAR = 2;
	//unreached
	private static final int DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR = 2;
	//text_offset
	private static final int DEFAULT_SIZE_TEXT_OFFSET = 10;

	/**
	 * painter of all drawing things
	 */
	protected Paint mPaint = new Paint();
	/**
	 * color of progress number
	 */
	protected int mTextColor = DEFAULT_TEXT_COLOR;
	/**
	 * size of text (sp)
	 */
	protected int mTextSize = sp2px(DEFAULT_TEXT_SIZE);

	/**
	 * offset of draw progress
	 */
	protected int mTextOffset = dp2px(DEFAULT_SIZE_TEXT_OFFSET);

	/**
	 * height of reached progress bar
	 */
	protected int mReachedProgressBarHeight = dp2px(DEFAULT_HEIGHT_REACHED_PROGRESS_BAR);

	/**
	 * color of reached bar
	 */
	protected int mReachedBarColor = DEFAULT_TEXT_COLOR;
	/**
	 * color of unreached bar
	 */
	protected int mUnReachedBarColor = DEFAULT_COLOR_UNREACHED_COLOR;
	/**
	 * height of unreached progress bar
	 */
	protected int mUnReachedProgressBarHeight = dp2px(DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR);
	/**
	 * view width except padding//不计算padding
	 */
	protected int mRealWidth;

	protected boolean mIfDrawText = true;

	protected static final int VISIBLE = 0;

	public HorizontalProgressBarWithNumber(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public HorizontalProgressBarWithNumber(Context context, AttributeSet attrs,
			int defStyle)
	{
		super(context, attrs, defStyle);
		//从xml中获取属性
		obtainStyledAttributes(attrs);
		//初始化画笔
		mPaint.setTextSize(mTextSize);
		mPaint.setColor(mTextColor);
	}
	//测量的模式，测量的值
	@Override
	protected synchronized void onMeasure(int widthMeasureSpec,
			int heightMeasureSpec)
	{

		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = measureHeight(heightMeasureSpec);
		setMeasuredDimension(width, height);
		//？
		mRealWidth = getMeasuredWidth() - getPaddingRight() - getPaddingLeft();
	}

	private int measureHeight(int measureSpec)
	{
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);//模式
		int specSize = MeasureSpec.getSize(measureSpec);//值
		if (specMode == MeasureSpec.EXACTLY)//固定
		{
			result = specSize;
		} else
		{
			//2.ascent：是baseline之上至字符最高处的距离
			//3.descent：是baseline之下至字符最低处的距离
			float textHeight = (mPaint.descent() - mPaint.ascent());//？
			result = (int) (getPaddingTop() + getPaddingBottom() + Math.max(
					Math.max(mReachedProgressBarHeight,
							//取绝对值
							mUnReachedProgressBarHeight), Math.abs(textHeight)));
			if (specMode == MeasureSpec.AT_MOST)
			{
				result = Math.min(result, specSize);
			}
		}
		return result;
	}

	/**
	 * get the styled attributes
	 * 从xml中获取属性
	 * @param attrs
	 */
	private void obtainStyledAttributes(AttributeSet attrs)
	{
		// init values from custom attributes
		final TypedArray attributes = getContext().obtainStyledAttributes(
				attrs, R.styleable.HorizontalProgressBarWithNumber);

		mTextColor = attributes
				.getColor(
						R.styleable.HorizontalProgressBarWithNumber_progress_text_color,
						DEFAULT_TEXT_COLOR);
		mTextSize = (int) attributes.getDimension(
				R.styleable.HorizontalProgressBarWithNumber_progress_text_size,
				mTextSize);

		mReachedBarColor = attributes
				.getColor(
						R.styleable.HorizontalProgressBarWithNumber_progress_reached_color,
						mTextColor);
		mUnReachedBarColor = attributes
				.getColor(
						R.styleable.HorizontalProgressBarWithNumber_progress_unreached_color,
						DEFAULT_COLOR_UNREACHED_COLOR);
		mReachedProgressBarHeight = (int) attributes
				.getDimension(
						R.styleable.HorizontalProgressBarWithNumber_progress_reached_bar_height,
						mReachedProgressBarHeight);
		mUnReachedProgressBarHeight = (int) attributes
				.getDimension(
						R.styleable.HorizontalProgressBarWithNumber_progress_unreached_bar_height,
						mUnReachedProgressBarHeight);
		mTextOffset = (int) attributes
				.getDimension(
						R.styleable.HorizontalProgressBarWithNumber_progress_text_offset,
						mTextOffset);

		int textVisible = attributes
				.getInt(R.styleable.HorizontalProgressBarWithNumber_progress_text_visibility,
						VISIBLE);
		if (textVisible != VISIBLE)
		{
			mIfDrawText = false;
		}
		//？TypedArray 为什么需要调用recycle()，一定要的
		attributes.recycle();
	}

	@Override
	protected synchronized void onDraw(Canvas canvas)
	{
//canvas.save();和canvas.restore();是两个相互匹配出现的，作用是用来保存画布的状态和取出保存的状态的。
		canvas.save();
		//http://blog.csdn.net/fengyee_zju/article/details/16994099
		canvas.translate(getPaddingLeft(), getHeight() / 2);
		//？
		boolean noNeedBg = false;
		//？
		float radio = getProgress() * 1.0f / getMax();
		//？
		float progressPosX = (int) (mRealWidth * radio);
		String text = getProgress() + "%";
		// mPaint.getTextBounds(text, 0, text.length(), mTextBound);
		//获取字符串显示的宽度值
		float textWidth = mPaint.measureText(text);
		float textHeight = (mPaint.descent() + mPaint.ascent()) / 2;

		if (progressPosX + textWidth > mRealWidth)
		{
			progressPosX = mRealWidth - textWidth;
			noNeedBg = true;
		}

		// draw reached bar
		float endX = progressPosX - mTextOffset / 2;
		if (endX > 0)
		{
			mPaint.setColor(mReachedBarColor);
			mPaint.setStrokeWidth(mReachedProgressBarHeight);
			canvas.drawLine(0, 0, endX, 0, mPaint);
		}
		// draw progress bar
		// measure text bound
		if (mIfDrawText)
		{
			mPaint.setColor(mTextColor);
			canvas.drawText(text, progressPosX, -textHeight, mPaint);
		}

		// draw unreached bar
		if (!noNeedBg)
		{
			float start = progressPosX + mTextOffset / 2 + textWidth;
			mPaint.setColor(mUnReachedBarColor);
			mPaint.setStrokeWidth(mUnReachedProgressBarHeight);
			canvas.drawLine(start, 0, mRealWidth, 0, mPaint);
		}

		canvas.restore();

	}

	/**
	 * dp 2 px
	 * 
	 * @param dpVal
	 */
	protected int dp2px(int dpVal)
	{
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dpVal, getResources().getDisplayMetrics());
	}

	/**
	 * sp 2 px
	 * 
	 * @param spVal
	 * @return
	 */
	protected int sp2px(int spVal)
	{
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
				spVal, getResources().getDisplayMetrics());

	}

}
