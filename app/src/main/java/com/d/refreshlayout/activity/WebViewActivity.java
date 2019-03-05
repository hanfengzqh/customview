package com.d.refreshlayout.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.d.refreshlayout.MainActivity;
import com.d.refreshlayout.R;

/**
 * ScrollViewActivity
 * Created by D on 2018/5/31.
 */
public class WebViewActivity extends AppCompatActivity {
    String URL = "http://www.zybang.com/composition/search/guidematerial?guideId=a47ded173f96a9c1e6b1896b6817e085";
    private static Context mContext;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, WebViewActivity.class);
        mContext = context;
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
//        init();
//        PorterDuffXfermodeView view = new PorterDuffXfermodeView(this);
//        FilletRecyclingImageView viewById = findViewById(R.id.llview);
//        viewById.setAlpha(0.2f);
//        viewById.getBackground().mutate().setAlpha(75);
        ImageView image = findViewById(R.id.imageview);

        Bitmap rawBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.skin_game_pvp_top_view_new_bg_english);
        Bitmap bitmap = toRoundCorner(rawBitmap, 20);
        image.setImageBitmap(bitmap);


        // 得到图片原始的高宽
        int rawHeight = rawBitmap.getHeight();
        int rawWidth = rawBitmap.getWidth();
        // 设定图片新的高宽
        int newHeight = 100;
        int newWidth = 100;
        // 计算缩放因子
        float heightScale = ((float) newHeight) / rawHeight;
        float widthScale = ((float) newWidth) / rawWidth;
        // 新建立矩阵
        Matrix matrix = new Matrix();
        matrix.postScale(heightScale, widthScale);
        // 设置图片的旋转角度
        matrix.postRotate(30);
        // 设置图片的倾斜
//        matrix.postSkew(0.1f, 0.1f);
        //将图片大小压缩
        //压缩后图片的宽和高以及kB大小均会变化
        Bitmap newBitmap = Bitmap.createBitmap(rawBitmap, 0, 0, rawWidth, rawHeight, matrix, true);
        // 将Bitmap转换为Drawable
//        Drawable newBitmapDrawable = new BitmapDrawable(newBitmap);
//        image.setImageDrawable(newBitmapDrawable);
        image.setImageBitmap(newBitmap);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext instanceof MainActivity) {
                    ((MainActivity) mContext).test();
                }
            }
        });

    }


    public Bitmap toRoundCorner(Bitmap bitMap, int pixels) {
        Bitmap roundCornerBitmap = Bitmap.createBitmap(bitMap.getWidth(), bitMap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(roundCornerBitmap);
        int color = 0xff424242;
        Paint paint = new Paint();
        paint.setColor(color);
        //防止锯齿
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, bitMap.getWidth(), bitMap.getHeight());
        RectF rectF = new RectF(rect);
        float roundPx = pixels;
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitMap, rect, rect, paint);
        return roundCornerBitmap;
    }

    public interface Test {
        void test();
    }

//    private void init() {
//        WebView webView = findViewById(R.id.webview);
//        webView.loadUrl(URL);
//    }
}
/***
 * https://blog.csdn.net/u010852160/article/details/78490451
 * https://www.cnblogs.com/dreamroute/p/8484457.html
 * https://www.jianshu.com/p/d11892bbe055
 * https://www.jianshu.com/search?q=saveLayer(RectF&page=1&type=note
 * https://www.jianshu.com/p/2fbd1a41a113
 * https://blog.csdn.net/chen930724/article/details/50176661
 * https://blog.csdn.net/u011433995/article/details/50475131?utm_source=blogxgwz6
 * https://blog.csdn.net/u010852160/article/details/78490451
 * https://blog.csdn.net/harryweasley/article/details/50132385/
 * https://blog.csdn.net/oyuanwa/article/details/51197546
 * https://www.cnblogs.com/dasusu/p/8047172.html
 *
 *
 * https://blog.csdn.net/EleganceKing/article/details/80989368
 * https://www.jianshu.com/p/49512ad8b43c
 * https://www.cnblogs.com/ldq2016/p/9036122.html
 * https://www.jianshu.com/p/f592f3715ae2?utm_campaign=haruki&utm_content=note&utm_medium=reader_share&utm_source=weixin&from=singlemessage&isappinstalled=1
 * https://blog.csdn.net/sdfdzx/article/details/79981073
 * https://www.jianshu.com/p/db92732bc40f
 * https://www.jianshu.com/p/5e20d5d7af6c
 * https://blog.csdn.net/MeloDev/article/details/76559427
 * https://blog.csdn.net/jieqiang3/article/details/68922821
 * https://www.jianshu.com/p/4f9591291365
 * https://developer.android.google.cn/reference/androidx/recyclerview/widget/RecyclerView
 */
