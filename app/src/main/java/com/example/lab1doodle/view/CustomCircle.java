package com.example.lab1doodle.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomCircle extends View {

    private Paint paint = null;

    public CustomCircle(Context context) {
        super(context);
        init();
    }

    public CustomCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        int radius = 150;
        for(int i = 1; i <= 3; i++){
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius/i, paint );
//            canvas.restore();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
        // handle touch
    }
}
