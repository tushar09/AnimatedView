package com.captaindroid.customview.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.captaindroid.customview.R;

public class CircleCanvas extends SurfaceView implements Runnable {

    boolean canDraw = false;
    Thread thread;

    Bitmap backGround;
    Canvas canvas;
    SurfaceHolder surfaceHolder;

    private Paint red_paintbrush_fill;
    private Paint blue_paintbrush_fill;
    private Paint green_paintbrush_fill;

    private Paint red_paintbrush_stroke;
    private Paint blue_paintbrush_stroke;
    private Paint green_paintbrush_stroke;

    Path square;

    Bitmap man;
    int manX, manY;
    int xDir, yDir;

    public CircleCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        surfaceHolder = getHolder();
        canDraw = true;
        thread = new Thread(this);
        thread.start();
        man = BitmapFactory.decodeResource(getResources(), R.drawable.man, null);
        backGround = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        manX = 650;
        manY = 130;

    }

    @Override
    public void run() {

        prepBurshes();

        while(canDraw){
            if(!surfaceHolder.getSurface().isValid()){
                continue;
            }
            canvas = surfaceHolder.lockCanvas();
            motionMan(10);
            drawSquare(130, 130, 650, 650);
            canvas.drawBitmap(backGround, 0, 0, null);
            canvas.drawBitmap(man, manX - (man.getWidth() / 2), manY - (man.getHeight() / 2), null);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }

    }

    private void motionMan(int speed){
        if(manY == 130 && manX < 650){
            manX += speed;
        }

        if(manY < 650 && manX == 650){
            manY += speed;
        }

        if(manY == 650 && manX > 130){
            manX -= speed;
        }

        if(manY > 130 && manX == 130){
            manY -= speed;
        }
    }

    private void drawSquare(int x1, int y1, int x2, int y2) {
        square = new Path();
        square.moveTo(x1, y1);
        square.lineTo(x2, y1);
        square.moveTo(x2, y1);
        square.lineTo(x2, y2);
        square.moveTo(x2, y2);
        square.lineTo(x1, y2);
        square.moveTo(x1, y2);
        square.lineTo(x1, y1);

        this.canvas.drawPath(square, green_paintbrush_stroke);
    }

    private void prepBurshes(){
        red_paintbrush_fill = new Paint();
        red_paintbrush_fill.setColor(Color.RED);
        red_paintbrush_fill.setStyle(Paint.Style.FILL);

        blue_paintbrush_fill = new Paint();
        blue_paintbrush_fill.setColor(Color.BLUE);
        blue_paintbrush_fill.setStyle(Paint.Style.FILL);

        green_paintbrush_fill = new Paint();
        green_paintbrush_fill.setColor(Color.GREEN);
        green_paintbrush_fill.setStyle(Paint.Style.FILL);


        red_paintbrush_stroke = new Paint();
        red_paintbrush_stroke.setColor(Color.RED);
        red_paintbrush_stroke.setStyle(Paint.Style.STROKE);

        blue_paintbrush_stroke = new Paint();
        blue_paintbrush_stroke.setColor(Color.BLUE);
        blue_paintbrush_stroke.setStyle(Paint.Style.STROKE);

        green_paintbrush_stroke = new Paint();
        green_paintbrush_stroke.setColor(Color.GREEN);
        green_paintbrush_stroke.setStyle(Paint.Style.STROKE);
        green_paintbrush_stroke.setStrokeWidth(10);
    }


}
