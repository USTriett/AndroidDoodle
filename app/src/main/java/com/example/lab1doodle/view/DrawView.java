package com.example.lab1doodle.view;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lab1doodle.R;
import com.example.lab1doodle.utilities.Utilities;

import java.util.ArrayList;
import java.util.Random;

public class DrawView extends androidx.appcompat.widget.AppCompatImageView {
    public static final int NUMBERS_PHOTO = 3;
    private Bitmap center;
    private Paint myPaint;
    //    private Canvas curCanvas;
    private ArrayList<Point> heartPoint  = new ArrayList<>();
    private final int sizeHeart = 10;
    private ArrayList<Bitmap> listBitmap = new ArrayList<>();
    private ArrayList<Integer> listImgId = Utilities.getListImgId();

    public DrawView(@NonNull Context context) {
        super(context);
        init(null);
    }

    public DrawView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DrawView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){
        myPaint = new Paint();
//        curCanvas = new Canvas();
        Resources res = getContext().getResources();
        for(int i = 0; i < listImgId.size(); i++){
            Bitmap bitmap1 = BitmapFactory.decodeResource(res, listImgId.get(i));
            if(bitmap1 != null)
                listBitmap.add(bitmap1);
        }
        center = BitmapFactory.decodeResource(res, R.drawable.phat);
//        center = new Point(getWidth() / 2, getHeight()/2);

//        createHeartPoints();
    }



    protected Pair<Integer, Integer> getParentViewSize(View parentView) {
        int width = parentView.getWidth();
        int height = parentView.getHeight();
        return new Pair<>(width, height);
    }
    private boolean updateView = false;
    public void setAnimation(boolean anim){
        this.updateView = true;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth ();
        int height = canvas.getHeight ();

        // Calculate the center point of the canvas
        int centerX = width / 2;
        int centerY = height / 2;

        // Draw the thumbnails in a heart shape pattern
        Random r = new Random();

        for (int i = 0; i < 180; i++) {
            // Calculate the angle and radius of each thumbnail based on its index
            double angle = Math.PI * i / 180;
//            double radius = Math.sin (4 * angle) * centerX / 2;

            // Calculate the x and y coordinates of each thumbnail based on its angle and radius
            double v = 25 * (16 * Math.pow(Math.sin(angle),3));
            double t = 25 * (13 * Math.cos(angle) - 5 * Math.cos(2 * angle) - 2 * Math.cos(3 * angle) - Math.cos(4 * angle));

            int xRight = centerX + (int) v;
            int xLeft = centerX - (int) v;
            int y = centerY - (int) t;

            // Draw each thumbnail on the canvas with some offset and rotation
            float half_thumbSize = 100 / 2;
            // Right
            int rand = Math.abs(r.nextInt(3)%listBitmap.size());
            Bitmap bm = Bitmap.createScaledBitmap(listBitmap.get(rand), 100, 100, false);
            canvas.save ();
            canvas.translate (xRight - half_thumbSize, y - half_thumbSize);
            canvas.drawBitmap (bm, 0, 0, myPaint);
            canvas.restore ();

            // Left
            canvas.save ();
            canvas.translate (xLeft - half_thumbSize, y - half_thumbSize);
            canvas.drawBitmap (bm, 0, 0, myPaint);
            canvas.restore ();
        }
        canvas.drawBitmap (Bitmap.createScaledBitmap(center, 200, 200, false), centerX - 200/2, centerY - 200/2, myPaint);
        if(updateView)
            invalidate();

    }




}
