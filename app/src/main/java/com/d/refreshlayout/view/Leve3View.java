package com.d.refreshlayout.view;

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

public class Leve3View extends View {
    private Paint mPaint;
    private Path mPath = new Path();
    private float mPreX, mPreY;

    public Leve3View(Context context) {
        this(context, null);
    }

    public Leve3View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Leve3View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);//设置支持抗锯齿效果
        mPaint.setStrokeWidth(5);//设置画笔宽度
        mPaint.setStyle(Paint.Style.STROKE);//设置画笔填充样式

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制二阶贝塞尔曲线 start
//        Path path = new Path();
//        path.moveTo(100, 300);//指向起始点
//        path.quadTo(200, 200, 300, 300);
//        path.quadTo(400, 400, 500, 300);
//        canvas.drawPath(path, mPaint);
        //二阶贝塞尔曲线绘制 end

        //绘制手指轨迹
        canvas.drawPath(mPath, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
//                mPath.lineTo(event.getX(), event.getY());
                float endX = (mPreX + event.getX()) / 2;
                float endY = (mPreY + event.getY()) / 2;
                mPath.quadTo(mPreX, mPreY, endX, endY);
                mPreX = event.getX();
                mPreY = event.getY();
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
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

    //重置还原
    public void reset() {
        mPath.reset();
        invalidate();
    }
}
