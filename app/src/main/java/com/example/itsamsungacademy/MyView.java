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
    float[] radius = new float[N];

    public MyView(Context context) {
        super(context);
        fillRandom(x, 0, 500);
        fillRandom(y, 0, 500);
        fillRandom(vx, -3, 3);
        fillRandom(vy, -3,3);
        fillRandom(radius,10,50);

    }

    float rand(float min , float max){
        return (float)(Math.random() * (max - min + 1)) + min;
    }

    void fillRandom(float[] array , float min, float max){
        for (int i = 0; i < array.length; i++){
            array[i] = rand (min, max);
        }
    }


    void add(float[] array , float[] values){
        for (int i = 0; i < array.length; i++){
            array[i] += values[i];
        }
    }

    private void drawBalls(Canvas canvas) {
        for (int i = 0; i < N; i++) {
            canvas.drawCircle(x[i], y[i], radius[i], paint);
        }
    }


    @Override
    protected void onDraw(Canvas canvas){
       drawBalls(canvas);
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
            add(x, vx);
            add(y, vy);
            if (x[i] < 0 || x[i] > this.getHeight()*2){
                vx[i] = - vx[i];
            }
            if (y[i] < 0 || y[i] > this.getHeight()){
                vy[i] = - vy[i];

            }
        }

        invalidate();
    }


}