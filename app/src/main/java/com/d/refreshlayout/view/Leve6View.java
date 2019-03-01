package com.d.refreshlayout.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.d.refreshlayout.R;

/**
 * Android 色彩矩阵
 */
public class Leve6View extends View {
    private Paint mPaint;
    int diff;
    private Bitmap bitmap;

    public Leve6View(Context context) {
        this(context, null);
    }

    public Leve6View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Leve6View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.composition_mate_01);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制原始位图
        canvas.drawBitmap(bitmap, null, new Rect(10, 0, 500, 500 * bitmap.getHeight() / bitmap.getWidth()), mPaint);
        canvas.translate(0, 510);

        //生成色彩矩阵(生成一个只包含蓝色通道的色彩矩阵)
//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//                0, 0, 0, 0, 0,//red
//                0, 0, 0, 0, 0,//green
//                0, 0, 1, 0, 0,//blue
//                0, 0, 0, 0, 0,//alpha
//
//        });

//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//                1, 0, 0, 0, 0,//red
//                0, 1, 0, 0, 100,//green 色彩平移除了增加指定颜色饱和度以外，另一个应用就是色彩反转
//                0, 0, 1, 0, 0,//blue
//                0, 0, 0, 1, 0,//alpha
//
//        });

        /*ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                -1, 0, 0, 0, 255,//red
                0, -1, 0, 0, 255,//green 色彩平移除了增加指定颜色饱和度以外，另一个应用就是色彩反转
                0, 0, -1, 0, 255,//blue
                0, 0, 0, 1, 0,//alpha

        });*/

        //色彩反转--两个颜色反转
//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//                1, 0, 0, 0, 0,//red
//                0, 0, 1, 0, 0,//green 色彩平移除了增加指定颜色饱和度以外，另一个应用就是色彩反转
//                0, 1, 0, 0, 0,//blue
//                0, 0, 0, 1, 0,//alpha
//
//        });

        //变旧照片
//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//                1 / 2f, 1 / 2f, 1 / 2f, 0, 0,//red
//                1 / 3f, 1 / 3f, 1 / 3f, 0, 0,//green 色彩平移除了增加指定颜色饱和度以外，另一个应用就是色彩反转
//                1 / 4f, 1 / 4f, 1 / 4f, 0, 0,//blue
//                0, 0, 0, 1, 0,//alpha
//
//        });
        //LightingColorFilter--过滤颜色和增强颜色的函数
        /**
         * 当对应的颜色值取0时，就不会将对应的颜色显示出来
         * 将要显示出来的颜色 mul 设置为ff,即255时,对原始颜色不会产生任何影响
         */
        mPaint.setColorFilter(new LightingColorFilter(0xff0000, 0x000000));

        //PorterDuffColorFilter图像混合滤镜
//        mPaint.setColorFilter(new PorterDuffColorFilter(Color.BLUE, PorterDuff.Mode.ADD));

//        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, null, new Rect(10, 0, 500, 500 * bitmap.getHeight() / bitmap.getWidth()), mPaint);

    }
}
