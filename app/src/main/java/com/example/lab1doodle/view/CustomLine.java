package com.example.lab1doodle.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomLine extends View {
    private Paint paint;
    public CustomLine(Context context) {
        super(context);
        init();
    }

    public CustomLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle( Paint.Style.STROKE );
        paint.setAntiAlias(true);
        paint.setStrokeWidth(3);
        paint.setColor( Color.BLACK );
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawLine(0, getHeight()/2 , getWidth(), getHeight()/2, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
        // handle touch
    }
}
