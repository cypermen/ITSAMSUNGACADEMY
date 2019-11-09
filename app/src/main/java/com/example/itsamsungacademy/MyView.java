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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        Paint paint1 = new Paint();
        paint.setColor(Color.RED);
        paint1.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        paint1.setStrokeWidth(10);

        //red rhombuses
        canvas.drawLine(canvas.getWidth()*1.0f / 4,0,0,canvas.getHeight()*1.0f / 4,paint);
        canvas.drawLine(0,canvas.getHeight()*1.0f / 4,canvas.getWidth()*1.0f*(3*1.0f/4),canvas.getHeight(),paint);
        canvas.drawLine(canvas.getWidth()*1.0f / 4,0,canvas.getWidth(),canvas.getHeight()*1.0f*(3*1.0f/4),paint);
        canvas.drawLine(canvas.getWidth(),canvas.getHeight()*1.0f*(3*1.0f / 4),canvas.getWidth()*1.0f*(3*1.0f / 4),canvas.getHeight(),paint);
        canvas.drawLine(canvas.getWidth()*1.0f * (3*1.0f / 4),0,0,canvas.getHeight()*1.0f * (3*1.0f / 4),paint);
        canvas.drawLine(canvas.getWidth()*1.0f * (3*1.0f / 4),0,canvas.getWidth(),canvas.getHeight()*1.0f / 4,paint);
        canvas.drawLine(canvas.getWidth(),canvas.getHeight()*1.0f / 4 ,canvas.getWidth()*1.0f / 4 ,canvas.getHeight(),paint);
        canvas.drawLine(0,canvas.getHeight()*1.0f * (3*1.0f / 4),canvas.getWidth()*1.0f / 4,canvas.getHeight(),paint);


        //blue rhombuses
        canvas.drawLine(canvas.getWidth()*1.0f / 2 ,0,canvas.getWidth(),canvas.getHeight()*1.0f / 2,paint1);
        canvas.drawLine(canvas.getWidth()*1.f / 2 ,0,0,canvas.getHeight()*1.0f / 2,paint1);
        canvas.drawLine(canvas.getWidth()*1.0f / 2,canvas.getHeight(),0,canvas.getHeight()*1.0f / 2,paint1);
        canvas.drawLine(canvas.getWidth()*1.0f / 2,canvas.getHeight(),canvas.getWidth(),canvas.getHeight()*1.0f / 2,paint1);
        canvas.drawLine(canvas.getWidth()*1.0f / 4,canvas.getHeight()*1.0f / 4,canvas.getWidth()*1.0f * (3*1.0f / 4),canvas.getHeight()*1.0f * (3*1.0f / 4),paint1);
        canvas.drawLine(getWidth()*0.75f,getHeight()*0.25f,getWidth()*0.25f,getHeight()*0.75f,paint1);





    }
}
