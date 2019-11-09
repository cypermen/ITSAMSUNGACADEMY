package com.example.itsamsungacademy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    Paint paint = new Paint();
    float x = 0;

    long lastTime = System.currentTimeMillis();
    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawCircle(x, 300, 20, paint);

        long nowTime = System.currentTimeMillis();
        x += 0.01f * (nowTime - lastTime);

        lastTime = nowTime;
        invalidate();
    }
}
