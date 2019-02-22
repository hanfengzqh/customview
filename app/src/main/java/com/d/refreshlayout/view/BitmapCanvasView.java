package com.d.refreshlayout.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 验证若是使用Bitmap构建canvas，在这个canvas上绘制的图片会保存在这个Bitmap上，而不是
 * 画在view上，若想在view上就必须使用onDraw(Canvas canvas)函数传进来的canvas画一遍Bitmap
 * 才可以展示到View上
 */
public class BitmapCanvasView extends View {
    private Bitmap mBmp;
    private Paint mPaint;
    private Canvas mBmpCanvas;

    public BitmapCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();//创建画笔
        mPaint.setColor(Color.RED);//设置画笔颜色
        mBmp = Bitmap.createBitmap(800, 200, Bitmap.Config.ARGB_8888);//创建一个空的Bitmap位图
        mBmpCanvas = new Canvas(mBmp);//通过Bitmap位图构建一个Canvas画布
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setTextSize(100);
        mBmpCanvas.drawText("我是测试View", 0, 100, mPaint);//使用Bitmap构建的画布绘制

        /**************************************************/
        //以上代码只是滑到了Bitmap上
        //
        canvas.drawBitmap(mBmp,0,0,mPaint);//绘制到view上


    }
}
