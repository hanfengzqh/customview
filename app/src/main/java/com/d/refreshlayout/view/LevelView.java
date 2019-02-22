package com.d.refreshlayout.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class LevelView extends View {
    private Paint mPaint;

    public LevelView(Context context) {
        this(context, null);
    }

    public LevelView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LevelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(25);
//        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mPaint.setStrokeWidth(5);//设置画笔宽度
        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setShadowLayer(10, 15, 15, Color.YELLOW);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制文字
//        canvas.drawText("https://blog.csdn.net/", 0, 20, mPaint);
        RectF rect = new RectF(100, 10, 300, 100);
        canvas.drawRect(rect, mPaint);//画矩形

        mPaint.setColor(Color.BLUE);//更改画笔颜色
        canvas.drawOval(rect, mPaint);//绘制椭圆形

        //绘制弧形
        mPaint.setColor(Color.YELLOW);
        mPaint.setStyle(Paint.Style.FILL);
        RectF rectF = new RectF(100, 120, 300, 220);
        /**
         * RectF oval:生成椭圆的矩形
         * float startAngle：弧开始的角度，以X轴正方向为0度
         * float sweepAngle：弧持续的角度
         * boolean useCenter:是否有弧的两边，True，还两边，False，只有一条弧
         */
        canvas.drawArc(rectF, 0, 240, true, mPaint);

        //绘制直线路径
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(100, 230);//设定起始点
        path.lineTo(100, 400);//第一条直线的终点，也是第二条直线的起点
        path.lineTo(300, 450);//第二条直线
        path.lineTo(400, 500);//第三条直线
        path.close();
        canvas.drawPath(path, mPaint);

        //绘制矩形路径
        Path ccw = new Path();//逆时针
        RectF rectF1 = new RectF(320,10,470,160);
        ccw.addRect(rectF1, Path.Direction.CCW);
        canvas.drawPath(ccw,mPaint);//绘制路径


        Path cw = new Path();//顺时针
        RectF rectF2 = new RectF(540,10,690,160);
        cw.addRect(rectF2, Path.Direction.CW);
        canvas.drawPath(cw,mPaint);//绘制路径

        //根据路径绘制文字
        String testText = "大风起兮云飞扬，威加海内兮归故乡。";
        mPaint.setColor(Color.GREEN);
        canvas.drawTextOnPath(testText,ccw,0,18,mPaint);
        /**
         * float hOffset  : 与路径起始点的水平偏移距离
         * float vOffset  : 与路径中心的垂直偏移量
         */
        canvas.drawTextOnPath(testText,cw,20,18,mPaint);

        //绘制指定字体文字
        String familyName = "Times New Roman";
//        String familyName = "宋体";
        Typeface typeface = Typeface.create(familyName, Typeface.NORMAL);
        mPaint.setTypeface(typeface);
        mPaint.setTextSize(60);//设置文字大小
        canvas.drawText("欢迎光临Harvic的博客",100,700,mPaint);


        mPaint.setStrokeWidth (5);//设置画笔宽度
        mPaint.setAntiAlias(true); //指定是否使用抗锯齿功能，如果使用，会使绘图速度变慢
        mPaint.setTextSize(60);//设置文字大小
        mPaint.setStyle(Paint.Style.STROKE);//绘图样式，设置为填充

//        String familyName = "宋体";
        Typeface font = Typeface.create(familyName,Typeface.NORMAL);
        mPaint.setTypeface(font);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.LEFT);//对齐方式
        canvas.drawText("欢迎光临Harvic的博客",10,600, mPaint);
    }
}
