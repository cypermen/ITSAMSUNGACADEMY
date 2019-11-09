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
    int[] red = new int[N];
    int[] green = new int[N];
    int[] blue = new int[N];
    float[] radius = new float[N];

    public MyView(Context context) {
        super(context);
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
                red[i] = (int) (Math.random()*255);
                green[i] = (int) (Math.random()*255);
                blue[i] = (int) (Math.random()*255);
                radius[i]=(float)(Math.random()*50);

            }
            started = true;
        }
        for (int i = 0; i < N; i++) {
            if (x[i] < 0 || x[i] > this.getHeight()){
                paint.setColor(Color.rgb(red[i],green[i],blue[i]));
            }
            if (y[i] < 0 || y[i] > this.getHeight()){
                paint.setColor(Color.rgb(red[i],green[i],blue[i]));

            }
            canvas.drawCircle(x[i], y[i], radius[i], paint);
        }
        for(int i = 0;i < N;i++){
            for(int j = i + 1;j < N;j++){
                if(Math.sqrt((x[i]-x[j])*(x[i]-x[j])+(y[i]-y[j])*(y[i]-y[j]))<=2*radius[i]){
                    if( radius[i] > radius[j] ) {
                        vx[j] = vx[i] * (radius[i] * 1.0f / radius[j]);
                        vy[j] = vy[i] * (radius[i] * 1.0f / radius[j]);
                    }else if(radius[i] == radius[j]){
                        vx[i]=-vx[i];
                        vy[i]=-vx[i];
                        vx[j]=-vx[j];
                        vy[j]=-vx[j];
                    }else{
                        vx[i] = vx[j] * (radius[j] * 1.0f / radius[i]);
                        vy[i] = vy[j] * (radius[j] * 1.0f / radius[i]);
                    }

                }
            }
        }
        for (int i = 0; i < N; i++) {
            x[i] += vx[i];
            y[i] += vy[i];
            if (x[i] < 0 || x[i] > this.getHeight()){
                vx[i] = - vx[i];
            }
            if (y[i] < 0 || y[i] > this.getHeight()){
                vy[i] = - vy[i];

            }
        }

        invalidate();
    }
}