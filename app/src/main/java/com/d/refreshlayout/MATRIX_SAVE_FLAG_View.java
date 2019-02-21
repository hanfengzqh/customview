package com.d.refreshlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MATRIX_SAVE_FLAG_View extends View {
    private Paint mPaint;

    public MATRIX_SAVE_FLAG_View(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();

        mPaint.setColor(Color.GREEN);//绿色
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save(Canvas.MATRIX_SAVE_FLAG);//只保存矩阵
        canvas.rotate(40);//旋转
        canvas.drawRect(100, 0, 200, 100, mPaint);//绘制
        canvas.restore();//将画布还原

        mPaint.setColor(Color.YELLOW);//黄色
        canvas.drawRect(100, 0, 200, 100, mPaint);//绘制
    }
}
