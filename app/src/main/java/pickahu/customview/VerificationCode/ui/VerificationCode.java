package pickahu.customview.VerificationCode.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/11.
 */
public class VerificationCode extends View{

    private int mTextColor;
    private int mBgColor;
    private String mCodeText;
    private Paint mPaint;
    private Rect mTextRect;
    private int mTextSize;
    private OnDataChangeListener mListener;

    public VerificationCode(Context context) {
        super(context);
        init();
    }

    public VerificationCode(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VerificationCode(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        //初始化属性
        mTextColor = Color.RED;
        mBgColor = Color.YELLOW;
        mCodeText = "1234";
        mTextSize = 16;


        //初始化画笔
        mPaint = new Paint();
        mTextRect = new Rect();

        mPaint.setTextSize(mTextSize);
        mPaint.getTextBounds(mCodeText, 0, mCodeText.length(), mTextRect);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = randomText();
                setText(str);
                mListener.onDataChanged(str);
            }
        });

    }


    public VerificationCode setText(String text) {

        mCodeText = text;
        invalidate();
        return this;
    }

    public VerificationCode setTextColor(int textColor) {

        mTextColor = textColor;
        invalidate();
        return this;
    }

    public VerificationCode setTextSize(int textSize) {

        mTextSize = textSize;
        mPaint.setTextSize(mTextSize);
        mPaint.getTextBounds(mCodeText, 0, mCodeText.length(), mTextRect);
        invalidate();
        return this;
    }

    public VerificationCode setBgColor(int bgColor) {

        mBgColor = bgColor;
        invalidate();
        return this;
    }

    public void setOnDataChangedListener(OnDataChangeListener listener){

        mListener = listener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST) {
            width = getPaddingLeft() + getPaddingRight() + mTextRect.width();
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            height = getPaddingTop() + getPaddingBottom() + mTextRect.height();
        }

        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(mBgColor);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mTextColor);
        canvas.drawText(mCodeText, getWidth() / 2 - mTextRect.width() / 2, getHeight() / 2 + mTextRect.height() / 2, mPaint);

    }



    private String randomText()
    {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4)
        {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set)
        {
            sb.append("" + i);
        }

        return sb.toString();
    }
}
