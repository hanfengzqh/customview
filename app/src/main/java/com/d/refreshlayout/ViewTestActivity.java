package com.d.refreshlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.d.refreshlayout.view.Leve4View;
import com.d.refreshlayout.view.Leve5View;

public class ViewTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        Leve5View testImage = findViewById(R.id.view_wave);
        testImage.startAnimator();

    }
}
