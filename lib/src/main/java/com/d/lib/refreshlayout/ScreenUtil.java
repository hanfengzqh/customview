//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.d.lib.refreshlayout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import java.util.concurrent.atomic.AtomicInteger;

public class ScreenUtil {
    private static int DPI_LEVEL = -1;
    public static final int LEVEL_MDPI = 1;
    public static final int LEVEL_HDPI = 2;
    public static final int LEVEL_XHDPI = 3;
    public static final int LEVEL_XXHDPI = 4;
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public ScreenUtil() {
    }

    public static int dp2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dp * scale + 0.5F);
    }

    public static Rect getViewRect(View view, Rect rect) {
        if (rect == null) {
            rect = new Rect();
        }

        ((Activity)view.getContext()).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int decorViewTop = rect.top;
        view.getGlobalVisibleRect(rect);
        rect.top -= decorViewTop;
        rect.bottom -= decorViewTop;
        return rect;
    }

    public static Rect getViewRect(View view) {
        return getViewRect(view, (Rect)null);
    }


    public static DisplayMetrics getMetrics(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    public static int getScreenWidth(Context context) {
        return getMetrics(context).widthPixels;
    }

    public static int getGenerateViewId() {
        int i;
        if (VERSION.SDK_INT < 17) {
            i = generateViewId();
            return i;
        } else {
            i = View.generateViewId();
            return i;
        }
    }

    public static int generateViewId() {
        int result;
        int newValue;
        do {
            result = sNextGeneratedId.get();
            newValue = result + 1;
            if (newValue > 16777215) {
                newValue = 1;
            }
        } while(!sNextGeneratedId.compareAndSet(result, newValue));

        return result;
    }
}
