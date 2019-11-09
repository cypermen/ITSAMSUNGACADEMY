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

        paint.setColor(Color.BLUE);


        int n = 40;

        for(int i = 0; i < n;i++){//diagonal lines
            canvas.drawLine(canvas.getWidth()*((i*1.0f)/n),0,canvas.getWidth(),canvas.getHeight()*(1 - (i*1.0f/n)),paint);
            canvas.drawLine(0,canvas.getHeight()*(i*1.0f/n),canvas.getWidth()*(1-(i*1.0f/n)),canvas.getHeight(),paint);
        }

    }
}
