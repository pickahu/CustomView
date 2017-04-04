package pickahu.customview.ImageAndDes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import pickahu.customview.R;
import pickahu.customview.utils.DisplayUtil;

/**
 * Created by Administrator on 2017/3/12.
 */
public class ImageDes extends View {


    public final static int IMAGE_SCALE_FITXY = 0;
    public final static int IMAGE_SCALE_CENTER = 1;

    private Context context;
    private String mText;
    private int mTextColor;
    private int mTextSize;
    private Bitmap mBitmap;
    private Paint mPaint;
    private Rect mTextRect;
    private int mImageScaleType;

    public ImageDes(Context context) {
        super(context);
        init(context);
    }

    public ImageDes(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ImageDes(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;

        mText = "图片描述";
        mTextColor = Color.BLACK;
        mTextSize = DisplayUtil.sp2px(context, 20);
        mImageScaleType = IMAGE_SCALE_FITXY;

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.default_image);

        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
        mTextRect = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), mTextRect);


    }

    public ImageDes setImage(int id)
    {
        mBitmap = BitmapFactory.decodeResource(getResources(),id);
        invalidate();
        return this;
    }

    public ImageDes setText(String text)
    {
        mText = text;
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

            int widthImage = mBitmap.getWidth();
            int widthText = mTextRect.width();

            int max = Math.max(widthImage, widthText);

            width = getPaddingLeft() + max + getPaddingRight();
        }

        if(heightMode == MeasureSpec.AT_MOST)
        {
            height = getPaddingTop() + getPaddingBottom() + mTextRect.height() +mBitmap.getHeight();
        }

        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        mPaint.setColor(mTextColor);
        if (mTextRect.width() > getWidth())
        {
            TextPaint paint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(mText, paint, (float) getWidth() - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg, getPaddingLeft(), getHeight() - getPaddingBottom(), mPaint);

        } else
        {
            //正常情况，将字体居中
            canvas.drawText(mText, getWidth() / 2 - mTextRect.width() * 1.0f / 2, getHeight() - getPaddingBottom(), mPaint);
        }

        Rect imageRect =  new Rect();
        imageRect.left = getPaddingLeft();
        imageRect.top  =getPaddingTop();
        imageRect.right = getWidth() - getPaddingRight();
        imageRect.bottom = getHeight() - getPaddingBottom() - mTextRect.height();


        if(mImageScaleType == IMAGE_SCALE_FITXY)
        {
            canvas.drawBitmap(mBitmap, null, imageRect, mPaint);
        }else{
            //计算居中的矩形范围
            imageRect.left = getWidth() / 2 - mBitmap.getWidth() / 2;
            imageRect.right = getWidth()  / 2 + mBitmap.getWidth() / 2;
            imageRect.top = (getHeight() - mTextRect.height()) / 2 - mBitmap.getHeight() / 2;
            imageRect.bottom = (getHeight() - mTextRect.height()) / 2 + mBitmap.getHeight() / 2;

            canvas.drawBitmap(mBitmap, null, imageRect, mPaint);
        }

        /**
         * 边框
         */
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);
    }
}
