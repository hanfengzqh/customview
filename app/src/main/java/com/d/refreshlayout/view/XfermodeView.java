package com.d.refreshlayout.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class XfermodeView extends View {
    private int width = 400;
    private int height = 400;
    private Bitmap dstBmp;//目标Bitmap
    private Bitmap srcBmp;//源Bitmap
    private Paint mPaint;

    public XfermodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        /**
         * view可以使用以下三个类型之一：
         * • LAYER_TYPE_NONE:view按一般方式绘制，不使用离屏缓冲．这是默认的行为．
         * • LAYER_TYPE_HARDWARE:如果应用被硬加速了，view会被绘制到一个硬件纹理中．如果应用没被硬加速，此类型的layer的行为同于LAYER_TYPE_SOFTWARE．
         * • LAYER_TYPE_SOFTWARE:view被绘制到一个bitmap中
         */
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        srcBmp = makeSrc(width, height);
        dstBmp = makeDst(width, height);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);//---最下面的一层
        /**
         * saveLayer()会创建一个透明的Bitmap，大小与指定保存的区域一致，其后面的绘图操作都放在这个Bitmap上进行
         * 在绘制结束之后，会直接盖在上一层的Bitmap上显示。
         */
        int layerID = canvas.saveLayer(0, 0, width * 2, height * 2, mPaint, Canvas.ALL_SAVE_FLAG);
        //绘制目标图像圆形
        canvas.drawBitmap(dstBmp, 0, 0, mPaint);//将蓝色圆形绘制到view上展示--中间一层
        canvas.rotate(30);
        /**
         *Mode.SRC_IN
         * 在两者相交的区域内绘制源图像，并且绘制的效果会受到目标图像对应地方透明度的影响
         *
         * 在处理源图像时，以显示源图像为主，在相交的区域利用目标图像的透明度来改变源图像的
         * 透明度和饱和度。当目标图像的透明度为0时，源图像完全不显示。
         */
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        //绘制源图像--矩形区域
        canvas.drawBitmap(srcBmp, width / 2, height / 2, mPaint);//将红色矩形绘制到view上展示--最上层
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerID);
    }

    // create a bitmap with a circle, used for the "dst" image--Destination
    static Bitmap makeDst(int w, int h) {//只是得到一个Bitmap对象
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(Color.BLUE);
//        c.drawRect(0, 0, w, h, p);
        c.drawOval(new RectF(0, 0, w, h), p);//在(0,0,400,400)的区域绘制一个蓝色的圆形
        return bm;
    }

    // create a bitmap with a rect, used for the "src" image--Source
    static Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(Color.RED);
        c.drawRect(0, 0, w, h, p);//在(0,0,400,400)的区域绘制一个红色的矩形
        return bm;
    }
}
