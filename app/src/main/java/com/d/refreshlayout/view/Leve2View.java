package com.d.refreshlayout.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Leve2View extends View {
    private Paint mPaint;

    public Leve2View(Context context) {
        this(context, null);
    }

    public Leve2View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Leve2View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);//设置支持抗锯齿效果
        mPaint.setTextSize(25);//设置文字大小
        mPaint.setStrokeWidth(5);//设置画笔宽度
        mPaint.setStyle(Paint.Style.STROKE);//设置画笔填充样式

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect0 = new Rect(100, 50, 300, 150);
        Rect rect1 = new Rect(150, 10, 250, 210);
//        canvas.translate(100,0);
        canvas.drawRect(rect0, mPaint);
        canvas.drawRect(rect1, mPaint);

        Region region0 = new Region(rect0);
        Region region1 = new Region(rect1);
//        region0.op(region1, Region.Op.INTERSECT);//获取相交区域
//        region0.op(region1, Region.Op.DIFFERENCE);//补集
        region0.op(region1, Region.Op.REVERSE_DIFFERENCE);//反转补集
//        region0.op(region1, Region.Op.UNION);//并集
//        region0.op(region1, Region.Op.XOR);//异并集
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);


        drawRegion(region0, canvas);


        //构造两个画笔，一个红色，一个绿色
        Paint paint_green = generatePaint(Color.GREEN, Paint.Style.STROKE, 5);
        Paint paint_red = generatePaint(Color.RED, Paint.Style.STROKE, 5);

        Rect rect3 = new Rect(0, 220, 300, 320);

        canvas.drawRect(rect3, paint_green);
        canvas.translate(100, 50);
        canvas.drawRect(rect3, paint_red);

        Paint paint_green1 = generatePaint(Color.GREEN, Paint.Style.FILL, 5);
        Paint paint_red1 = generatePaint(Color.RED, Paint.Style.STROKE, 5);
        Rect rect4 = new Rect(300, 430, 500, 500);

        canvas.drawRect(rect4, paint_green1);
        canvas.rotate(30);
        canvas.drawRect(rect4, paint_red1);


    }

    //使用微积分形式处理显示区域
    private void drawRegion(Region region, Canvas canvas) {
        RegionIterator regionIterator = new RegionIterator(region);
        Rect rect = new Rect();
        while (regionIterator.next(rect)) {
            canvas.drawRect(rect, mPaint);
        }
    }

    private Paint generatePaint(int color, Paint.Style style, int width) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(style);
        paint.setStrokeWidth(width);
        return paint;
    }
}
