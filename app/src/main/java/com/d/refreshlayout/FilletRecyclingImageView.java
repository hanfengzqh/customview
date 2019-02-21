package com.d.refreshlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.d.lib.refreshlayout.ScreenUtil;

public class FilletRecyclingImageView extends ImageView {

    private Paint paint;
    private RectF mRoundRect;
    private RectF mSrcRect;
    private Xfermode mXfermode;
    private Bitmap maskBitmap;
    private float radius = 20;
    private float imageWidth;
    private float imageHeight;
    private float defaultRatio = 0.33f;

    public FilletRecyclingImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
//        imageWidth = ScreenUtil.getScreenWidth(context) - ScreenUtil.dp2px(context,20);
//        imageHeight = imageWidth * defaultRatio;

        mRoundRect = new RectF();
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.OVERLAY);
        maskBitmap = null;
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
//        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.RoundImageView);
//        radius = typedArray.getDimension(R.styleable.RoundImageView_borderRadius, radius);
//        typedArray.recycle();
    }

    public void setImgWith(int imageWidth, float ratio) {
        this.imageWidth = imageWidth;
        if (ratio >0){
            imageHeight = imageWidth*ratio;
        }
    }

    public FilletRecyclingImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilletRecyclingImageView(Context context) {
        this(context, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (null != drawable && radius > 0.0F) {
            Drawable mDrawable = getDrawable();
            if (mDrawable == null) {
                return;
            }

            if (mDrawable.getIntrinsicWidth() == 0 || mDrawable.getIntrinsicHeight() == 0) {
                return;
            }
            imageHeight = mDrawable.getIntrinsicHeight();
            imageWidth = mDrawable.getIntrinsicWidth();
            Log.e("zqh","imageHeight = "+imageHeight+" : imageWidth = "+imageWidth);
            int saveCount = canvas.saveLayer(mSrcRect, null, Canvas.ALL_SAVE_FLAG);
            Log.e("zqh","saveCount = "+saveCount);
            canvas.save();
            Matrix mDrawMatrix = getImageMatrix();
            if (mDrawMatrix != null) {
                canvas.concat(mDrawMatrix);
            }

            mDrawable.draw(canvas);
            canvas.restore();
            paint.setXfermode(mXfermode);

            canvas.drawBitmap(maskBitmap, null, mRoundRect, paint);
            paint.setXfermode(null);
            canvas.restoreToCount(saveCount);
        } else {
            super.onDraw(canvas);
        }

    }

    private void getRoundBitmap(float w, float h) {
        if (null != maskBitmap && !maskBitmap.isRecycled()) {
            maskBitmap.recycle();
            maskBitmap = null;
        }

        maskBitmap = Bitmap.createBitmap((int) w, (int) h, Bitmap.Config.ALPHA_8);
        Canvas mCanvas = new Canvas(maskBitmap);
        radius = Math.min(radius, Math.min(w, h) / 2);
        mCanvas.drawRoundRect(new RectF(0.0F, 0.0F, w, h), radius, radius, paint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("zqh","right = "+right+" : bottom = "+bottom);
        mRoundRect.set(10.0F, 20.0F, right-50, bottom-50);
        mSrcRect = new RectF(0.0F,0.0F,getWidth(),getHeight());
        if (changed && radius > 0.0F && bottom > 0) {
            getRoundBitmap(right, bottom);
        }
    }
}
