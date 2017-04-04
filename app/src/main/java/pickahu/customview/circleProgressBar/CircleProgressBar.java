package pickahu.customview.circleProgressBar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import pickahu.customview.utils.DisplayUtil;
import pickahu.customview.utils.LogUtil;

/**
 * Created by Administrator on 2017/3/14.
 */
public class CircleProgressBar extends View {
    private Context context;
    private int mRadius;
    private int mCirlceWidth;
    private int mFirstColor;
    private int mSecondColor;
    private Paint mPaint;

    private float mProgress;

    public CircleProgressBar(Context context) {
        super(context);
        init(context);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        this.context = context;
        LogUtil.e("getHeight:" + getHeight());
        LogUtil.e("getMeasuredHeight:" + getMeasuredHeight());

        mRadius = DisplayUtil.dip2px(context, 30);
        mCirlceWidth = DisplayUtil.dip2px(context, 5);
        mFirstColor = Color.RED;
        mSecondColor = Color.BLUE;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

    }


    /**
     * 设置进度条进度
     *
     * @param progress 0-100.0的浮点值
     */
    public void setProgress(float progress) {

        float angle = 360 * (progress / 100);
        LogUtil.e("angle:"+progress);
        mProgress = angle;
        postInvalidate();
    }

    public CircleProgressBar setFirstColor(int firstColor) {
        mFirstColor = firstColor;
        invalidate();
        return this;
    }

    public CircleProgressBar setSecondColor(int secondColor) {
        mSecondColor = secondColor;
        invalidate();
        return this;
    }

    /**
     * 设置进度条宽度 ，注意是单位是DP
     *
     * @param dp
     * @return
     */
    public CircleProgressBar setCircleWidth(int dp) {
        mCirlceWidth = DisplayUtil.dip2px(context, dp);
        invalidate();
        return this;
    }

    /**
     * 设置进度条半径大小，注意单位为DP
     * @param dp
     * @return
     */
    public CircleProgressBar setCircleRadius(int dp) {

        mRadius = DisplayUtil.dip2px(context, dp);
        invalidate();
        return this;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST) {
            width = getPaddingLeft() + getPaddingRight() + mRadius * 2 + mCirlceWidth;
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            height = getPaddingTop() + getPaddingBottom() + mRadius * 2 + mCirlceWidth;
        }


        LogUtil.e("getHeight2:" + getHeight());
        LogUtil.e("getMeasuredHeight2:" + getMeasuredHeight());

        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtil.e("getHeight3:" + getHeight());
        LogUtil.e("getMeasuredHeight3:" + getMeasuredHeight());


        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;
        RectF ArcRect = new RectF(centerX - mRadius, centerY - mRadius, centerX + mRadius, centerY + mRadius);

        mPaint.setColor(mFirstColor);
        mPaint.setStrokeWidth(mCirlceWidth);
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心

        //画底圆
        canvas.drawCircle(centerX, centerY, mRadius, mPaint);

        mPaint.setColor(mSecondColor);
        canvas.drawArc(ArcRect, -90, mProgress, false, mPaint);

    }
}
