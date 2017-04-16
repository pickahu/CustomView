package pickahu.customview.volControl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import pickahu.customview.utils.DisplayUtil;

/**
 * Created by Administrator on 2017/4/4.
 */
public class VolView extends View {

    private Context mContext;
    private int circleColor;
    private int mCircleColor;
    private int mCircleRadius;
    private int mCircleWidth;
    private Paint mPaint;
    private Rect mRect;
    private int mPointNum;
    private int mInterval;
    private int mProgress;

    public VolView(Context context) {
        super(context);
        init(context);
    }

    public VolView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public VolView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;

        mCircleColor = Color.WHITE;
        mCircleRadius = DisplayUtil.dip2px(context, 80);
        mCircleWidth = DisplayUtil.dip2px(context, 5);

        mPointNum = 16;
        mInterval = DisplayUtil.dip2px(context, 5);
        mProgress = mPointNum;

        mPaint = new Paint();
        mRect = new Rect();
    }

    public void setProgress(int progress) {
        if (progress > mPointNum) {
            mProgress = mPointNum;
        } else if (progress < 0) {
            mProgress = 0;
        } else {
            mProgress = progress;
            postInvalidate();
        }
    }

    public void up() {
        setProgress(mProgress + 1);
    }

    public void down() {
        setProgress(mProgress - 1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (modeWidth == MeasureSpec.AT_MOST) {

        }

        if (modeHeight == MeasureSpec.AT_MOST) {

        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);


        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;


        //画点点
        mPaint.setColor(mCircleColor);

        RectF arcRect = new RectF(centerX - mCircleRadius, centerY - mCircleRadius,
                centerX + mCircleRadius, centerY + mCircleRadius);
        float sweepAngle = (float) ((360 - mPointNum * mInterval) * 1.0 / mPointNum);


        for (int i = 0; i < mProgress; i++) {
            canvas.drawArc(arcRect, i * (sweepAngle + mInterval), sweepAngle, false, mPaint);
        }
    }


}
