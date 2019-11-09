package com.example.itsamsungacademy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.View;


public class MyView extends View {

    int N = 15;
    int[] l = new int [N];
    double x0, y0;
    double[] x = new double [N];
    double[] y = new double [N];
    double g = 9.832f, pi = Math.PI;
    double[] w = new double[N];
    double fi0;
    double[] fi = new double[N];
    int t = 0, deltaT = 1;

    void makePendulum()
    {
        fi0 = pi/4;

        int l_min = 100;
        for (int i = 0; i<N; i++)
        {
            l[i] = l_min;
            l_min += 50;

            w[i] = Math.sqrt(g/l[i]);
        }
    }
    void movePendulum()
    {
        t += deltaT;

        for (int i = 0; i<N; i++)
        {
            fi[i] = fi0 * Math.cos(w[i] * t);
            x[i] = l[i]*Math.sin(fi[i]);
            y[i] = l[i]*Math.cos(fi[i]);
        }
    }

    MyView(Context context) {
        super(context);
        makePendulum();
        MyTimer timer = new MyTimer();
        timer.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        x0 = getWidth()/2;
        y0 = getHeight()/4;
        Paint paint = new Paint();
        canvas.drawCircle((float) x0, (float) y0, 10, paint);
        for (int i = 0; i<N; i++)
        {
            paint.setColor(Color.BLUE);
            canvas.drawLine((float)x0, (float)y0, (float)(x[i] + x0), (float)(y[i]+ y0), paint);
            paint.setColor(Color.RED);
            canvas.drawCircle((float)(x[i] + x0), (float)(y[i] + y0), 20, paint);
        }
    }

    void nextFrame()
    {
        movePendulum();
        invalidate();
    }

    class MyTimer extends CountDownTimer
    {
        MyTimer()
        {
            super(100000, 100);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            nextFrame();
        }
        @Override
        public void onFinish() {
        }
    }
}