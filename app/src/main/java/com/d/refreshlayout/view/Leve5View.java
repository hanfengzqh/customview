package com.d.refreshlayout.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * paint的相关设置--
 */
public class Leve5View extends View {
    private Paint mPaint;
    int diff;

    public Leve5View(Context context) {
        this(context, null);
    }

    public Leve5View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Leve5View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(80);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
//        mPaint.setStrokeCap(Paint.Cap.BUTT);//无任何线冒
//        canvas.drawLine(100, 200, 400, 200, mPaint);
//
//        mPaint.setStrokeCap(Paint.Cap.SQUARE);//方形线冒
//        canvas.drawLine(100, 400, 400, 400, mPaint);
//
//        mPaint.setStrokeCap(Paint.Cap.ROUND);//圆形线冒
//        canvas.drawLine(100, 600, 400, 600, mPaint);

        //垂直画出x=100的这条线
//        mPaint.reset();
//        mPaint.setColor(Color.RED);
//        mPaint.setStrokeWidth(2);
//        canvas.drawLine(100, 50, 100, 750, mPaint);

//        mPaint.reset();
//        mPaint.setStrokeJoin(Paint.Join.ROUND);//两条直线的相交处为圆弧
//        mPaint.setStrokeJoin(Paint.Join.BEVEL);//两条直线的相交处为直线
//        mPaint.setStrokeJoin(Paint.Join.MITER);//两条直线的相交处为锐角
//
        mPaint.reset();
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);//画笔的默认类型是FILL_AND_STROKE
        Path path = new Path();
        path.moveTo(100, 600);
        path.lineTo(400, 100);
        path.lineTo(700, 900);
        canvas.drawPath(path, mPaint);
        //设置圆形拐角效果
//        mPaint.setColor(Color.BLUE);
//        mPaint.setPathEffect(new CornerPathEffect(100));
//        canvas.drawPath(path, mPaint);
//
//        mPaint.setColor(Color.YELLOW);
//        mPaint.setPathEffect(new CornerPathEffect(200));
//        canvas.drawPath(path, mPaint);

        //设置虚线效果
        mPaint.setPathEffect(new DashPathEffect(new float[]{20, 10, 100, 100}, 0));
        canvas.translate(0, 100);
        mPaint.setColor(Color.GREEN);
        canvas.drawPath(path, mPaint);

        //偏移量是相对于原点缩进了多少
        mPaint.setPathEffect(new DashPathEffect(new float[]{20, 10, 50, 100}, diff));
        canvas.translate(0, 100);
        mPaint.setColor(Color.YELLOW);
        canvas.drawPath(path, mPaint);

    }


    public void startAnimator() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 230);
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                diff = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });

        animator.start();
    }

}
