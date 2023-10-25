package com.example.lab1doodle.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomText extends View {
    private TextPaint paint;
    StaticLayout mStaticLayout;
    public CustomText(Context context) {
        super(context);
        init();
    }

    public CustomText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        paint = new TextPaint();
        paint.setAntiAlias(true);
        paint.setTextSize(24 * getResources().getDisplayMetrics().density);
        paint.setColor(0xFF000000);

        // default to a single line of text
        int width = (int) paint.measureText("UW");

        // New API alternate

         StaticLayout.Builder builder = StaticLayout.Builder.obtain("UW", 0, "UW".length(), paint, width)
                .setAlignment(Layout.Alignment.ALIGN_NORMAL)
                .setLineSpacing(1, 0) // multiplier, add
                .setIncludePad(false);
         mStaticLayout = builder.build();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Tell the parent layout how big this view would like to be
        // but still respect any requirements (measure specs) that are passed down.

        // determine the width
        int width;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthRequirement = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthRequirement;
        } else {
            width = mStaticLayout.getWidth() + getPaddingLeft() + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST) {
                if (width > widthRequirement) {
                    width = widthRequirement;
                    // too long for a single line so relayout as multiline

                }
            }
        }

        // determine the height
        int height;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightRequirement = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightRequirement;
        } else {
            height = mStaticLayout.getHeight() + getPaddingTop() + getPaddingBottom();
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(height, heightRequirement);
            }
        }

        // Required call: set width and height
        setMeasuredDimension(width, height);
    }
    private float targetX = getPaddingLeft();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // do as little as possible inside onDraw to improve performance
        ;

        // calculate how much you need to translate the canvas in the x direction


        // draw the text on the canvas after adjusting for padding and translating to the target x position
        canvas.save();
        canvas.translate(targetX - getPaddingLeft(), getPaddingTop());

        mStaticLayout.draw(canvas);

        canvas.restore();
        if(targetX < getWidth() - paint.measureText("UW") - 10){
            targetX+=5;

            invalidate();
        }
        // draw the text on the canvas after adjusting for padding
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
        // handle touch
    }
}
