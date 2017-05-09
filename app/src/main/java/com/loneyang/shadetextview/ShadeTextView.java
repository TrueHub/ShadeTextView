package com.loneyang.shadetextview;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by user on 2017/5/9.
 */

public class ShadeTextView extends View {

    public ShadeTextView(Context context) {
        this(context, null);
    }

    public ShadeTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private int mDirection ; //渐变的方向

    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_TOP = 2;
    public static final int DIRECTION_BOTTOM = 3;

    public void setDirection(int direction) {
        mDirection = direction;
        postInvalidate();
    }

    private Paint mPaint;

    private String mText;//显示的文字

    private int mTextSize;//文字大小

    private float mProgress;//渐变位置

    private int mFirstColor;//base文字颜色

    private int mSecondColor;//变化的文字颜色

    public void setmProgress(float mProgress) {
        this.mProgress = mProgress;
        postInvalidate();
    }

    public ShadeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ShadeTextView, 0, defStyleAttr);

        for (int i = 0; i < typedArray.length(); i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.ShadeTextView_text:
                    mText = typedArray.getString(attr);
                    break;
                case R.styleable.ShadeTextView_firstColor:
                    mFirstColor = typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.ShadeTextView_secondColor:
                    mSecondColor = typedArray.getColor(attr, Color.BLUE);
                    break;
                case R.styleable.ShadeTextView_textSize:
                    mTextSize = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                /*case R.styleable.ShadeTextView_progress:
                    mProgress = typedArray.getInteger(attr, 30);
                    break;*/
            }
        }
        typedArray.recycle();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        int width = 0, height = 0;

        switch (specMode) {
            case MeasureSpec.EXACTLY:
                width = specSize + getPaddingRight() + getPaddingLeft();
                break;

            case MeasureSpec.AT_MOST:
                width = (int) (mPaint.measureText(mText) + getPaddingLeft() + getPaddingRight());//先确定mPaint是否已经设置textsize
                break;
        }

        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (specMode) {
            case MeasureSpec.EXACTLY:
                height = specSize + getPaddingTop() + getPaddingBottom();
                break;
            case MeasureSpec.AT_MOST:
                height = (int) (Math.abs(mPaint.getFontMetrics().bottom - mPaint.getFontMetrics().top) + getPaddingTop() + getPaddingBottom());
                break;
        }

        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        float textWidth = mPaint.measureText(mText);
        float mLeft = (getMeasuredWidth() - textWidth) / 2;
        float textHeight = Math.abs(mPaint.getFontMetrics().bottom - mPaint.getFontMetrics().top);
        float mTop = (getMeasuredHeight() - textHeight) /2 ;

        mPaint.setColor(mFirstColor);
        canvas.drawText(mText, mLeft, getY(), mPaint);

        mPaint.setColor(mSecondColor);


        if (mDirection == DIRECTION_LEFT) {
            canvas.save(Canvas.CLIP_SAVE_FLAG);
            canvas.clipRect(mLeft, 0, textWidth * mProgress + mLeft, getMeasuredHeight());
            canvas.drawText(mText, mLeft, getY(), mPaint);
            canvas.restore();
        } else if (mDirection == DIRECTION_RIGHT){
            canvas.save(Canvas.CLIP_SAVE_FLAG);
            canvas.clipRect(textWidth - textWidth * mProgress + mLeft , 0 , textWidth + mLeft, getMeasuredHeight());
            canvas.drawText(mText, + mLeft, getY(), mPaint);
            canvas.restore();
        } else if (mDirection == DIRECTION_TOP){
            canvas.save(Canvas.CLIP_SAVE_FLAG);
            canvas.clipRect(0, mTop , getWidth(), textHeight * mProgress + mTop);
            canvas.drawText(mText, + mLeft, getY(), mPaint);
            canvas.restore();
        }else if (mDirection == DIRECTION_BOTTOM){
            canvas.save(Canvas.CLIP_SAVE_FLAG);
            canvas.clipRect(0, textHeight - textHeight * mProgress + mTop, getWidth(), textHeight + mTop );
            canvas.drawText(mText, + mLeft, getY(), mPaint);
            canvas.restore();
        }
    }

    public float getY() {
        Paint.FontMetricsInt fm = mPaint.getFontMetricsInt();
        return (getHeight() + fm.descent - fm.ascent) / 2 - fm.descent;
    }
}
