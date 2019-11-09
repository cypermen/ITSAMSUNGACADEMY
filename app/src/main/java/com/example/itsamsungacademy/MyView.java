package com.example.itsamsungacademy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {
    Paint paint = new Paint();
    int N = 10;
    float[] x  = new float[N];
    float[] y  = new float[N];
    float[] vx = new float[N];
    float[] vy = new float[N];

    public MyView(Context context) {
        super(context);
        for (int i = 0; i < N; i++){
            x[i] = (float)(Math.random() * 500);
            y[i] = (float)(Math.random() * 500);
            vx[i] = (float)(Math.random() * 6 - 3);
            vy[i] = (float)(Math.random() * 6 - 3);
        }
    }



    boolean started = false;
    @Override
    protected void onDraw(Canvas canvas){
        if (!started){
            for (int i = 0; i < N; i++){
                x[i] = (float)(Math.random() * getWidth());
                y[i] = (float)(Math.random() * getHeight());
                vx[i] = (float)(Math.random() * 6 - 3);
                vy[i] = (float)(Math.random() * 6 - 3);
            }
            started = true;
        }
        for (int i = 0; i < N; i++) {
            canvas.drawCircle(x[i], y[i], 20, paint);
        }

        for (int i = 0; i < N; i++) {
            x[i] += vx[i];
            y[i] += vy[i];
        }

        invalidate();
    }
}
