package com.d.refreshlayout.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * 水波纹效果
 */
public class Leve4View extends View {
    private Paint mPaint;
    private Path mPath;
    private int mItemWaveLength = 800;
    int dx;

    public Leve4View(Context context) {
        this(context, null);
    }

    public Leve4View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Leve4View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        int originY = 300;
        int halfWaveLen = mItemWaveLength / 2;
        mPath.moveTo(-mItemWaveLength + dx, originY);
        for (int i = -mItemWaveLength; i <= getWidth() + mItemWaveLength; i += mItemWaveLength) {
            mPath.rQuadTo(halfWaveLen / 2, -100, halfWaveLen, 0);
            mPath.rQuadTo(halfWaveLen / 2, 100, halfWaveLen, 0);
        }

        mPath.lineTo(getWidth(), getHeight());
        mPath.lineTo(0, getHeight());

        //绘制手指轨迹
        canvas.drawPath(mPath, mPaint);
    }

    public void startAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, mItemWaveLength);
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.start();
    }
}
