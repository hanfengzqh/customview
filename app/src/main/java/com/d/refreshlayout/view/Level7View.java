package com.d.refreshlayout.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.d.refreshlayout.R;

/**
 * CPU主导绘图--让View层次结构失效；绘制View层次结构
 * 基于硬件加速GPU主导绘图--让View层次结构失效；记录、更新显示列表；绘制显示列表
 * setXfermode(Xfermode xfermode)--图形混合模式
 */
public class Level7View extends View {
    private Paint mPaint;
    int diff;
    private Bitmap bitmapSRC;//源图像
    private Bitmap bitmapDST;//目标图像
    private Bitmap bitmapText;//底部显示文字

    private PorterDuffXfermode xfermode;
    private Path mPath;
    private float mPreX, mPreY;

    public Level7View(Context context) {
        this(context, null);
    }

    public Level7View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Level7View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(50);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);

        bitmapSRC = BitmapFactory.decodeResource(getResources(), R.mipmap.skin_game_pvp_top_view_new_bg_calculate);
        bitmapDST = Bitmap.createBitmap(bitmapSRC.getWidth(), bitmapSRC.getHeight(), Bitmap.Config.ARGB_8888);
        bitmapText = makeText(bitmapSRC.getWidth(), bitmapSRC.getHeight());
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmapText, 0, 0, mPaint);
        //创建一个隔离图层
        int saveLayer = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint, Canvas.ALL_SAVE_FLAG);

        //根据目标图像构建一空白的画布,并先把手指轨迹滑到目标Bitmap上
        Canvas c = new Canvas(bitmapDST);
        c.drawPath(mPath, mPaint);

        //然后把目标图像画到画布上
        canvas.drawBitmap(bitmapDST, 0, 0, mPaint);

        //计算源图像显示区域
        /**
         * 以目标图像透明度的补值来调节源图像的透明度和色彩饱和度。
         * 即当目标图像为空白像素时，就完全显示源图像，当目标图像的透明度为100%时，交合区域为空像素。
         */
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(bitmapSRC, 0, 0, mPaint);

        mPaint.setXfermode(null);
        canvas.restoreToCount(saveLayer);
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
                float endX = (mPreX + event.getX()) / 2;
                float endY = (mPreY + event.getY()) / 2;
                mPath.quadTo(mPreX, mPreY, endX, endY);
                mPreX = event.getX();
                mPreY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }

    static Bitmap makeText(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint();
        p.setColor(Color.RED);
        p.setStrokeWidth(10);
        p.setTextSize(40);
        p.setAntiAlias(true);
        c.drawText("谢谢关注，你没机会了！", 100, 100, p);
        return bm;
    }
}
