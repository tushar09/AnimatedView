package com.captaindroid.customview.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.captaindroid.customview.R;

import java.util.ArrayList;

public class Circle extends View {

    //circle and text colors
    private int circleCol, labelCol;
    //label text
    private String circleText;
    //paint for drawing custom view
    private Paint circlePaint;

    private Paint red_paintbrush_fill;
    private Paint blue_paintbrush_fill;
    private Paint green_paintbrush_fill;

    private Paint red_paintbrush_stroke;
    private Paint blue_paintbrush_stroke;
    private Paint green_paintbrush_stroke;

    Path triangle, square;

    Bitmap man, man2;
    int manX, manY;
    int man2X, man2Y;
    int xDir, yDir;

    ArrayList<Coordinates> coordinates;

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);
//        circlePaint = new Paint();
//        //get the attributes specified in attrs.xml using the name we included
//        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
//                R.styleable.Circle, 0, 0);
//        try {
//            //get the text and colors specified using the names in attrs.xml
//            circleText = a.getString(R.styleable.Circle_circleLabel);
//            circleCol = a.getInteger(R.styleable.Circle_circleColor, 0);//0 is default
//            labelCol = a.getInteger(R.styleable.Circle_labelColor, 0);
//        } finally {
//            a.recycle();
//        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        //get half of the width and height as we are working with a circle
//        int viewWidthHalf = this.getMeasuredWidth() / 2;
//        int viewHeightHalf = this.getMeasuredHeight() / 2;
//
//
//        //get the radius as half of the width or height, whichever is smaller
//        //subtract ten so that it has some space around it
//        int radius = 0;
//        if (viewWidthHalf > viewHeightHalf)
//            radius = viewHeightHalf - 10;
//        else
//            radius = viewWidthHalf - 10;
//
//        circlePaint.setStyle(Paint.Style.STROKE);
//        circlePaint.setAntiAlias(true);
//        //set the paint color using the circle color specified
//        circlePaint.setColor(circleCol);
//
//        canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius, circlePaint);
//        //set the text color using the color specified
//        circlePaint.setColor(labelCol);
//        //set text properties
//        circlePaint.setTextAlign(Paint.Align.CENTER);
//        circlePaint.setTextSize(50);
//        //draw the text using the string attribute and chosen properties
//        canvas.drawText(circleText, viewWidthHalf, viewHeightHalf, circlePaint);
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
        green_paintbrush_stroke.setColor(Color.GRAY);
        green_paintbrush_stroke.setStyle(Paint.Style.STROKE);
        green_paintbrush_stroke.setStrokeWidth(7);



        man = BitmapFactory.decodeResource(getResources(), R.drawable.man, null);
        man2 = BitmapFactory.decodeResource(getResources(), R.drawable.man, null);

        drawSquare(130, 130, 650, 650, canvas);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(getResources(), R.drawable.man, options);

//        if(manX >= canvas.getWidth() - options.outWidth){
//            xDir = -1;
//        }
//
//        if(manX <= 0){
//            xDir = 1;
//        }
//
//        if(manY >= canvas.getHeight() - options.outHeight){
//            yDir = -1;
//        }
//
//        if(manY <= 0){
//            yDir = 1;
//        }
//
//        manX += xDir;
//        manY += yDir;
        motionMan(10);

        canvas.drawBitmap(man, manX - (man.getWidth() / 2), manY - (man.getHeight() / 2), null);
        canvas.drawBitmap(man2, man2X - (man2.getWidth() / 2), man2Y - (man2.getHeight() / 2), null);

        invalidate();

    }

    private void motionMan(int speed){
//        if(manY == 130 && manX < 650){
//            manX += speed;
//        }
//
//        if(manY < 650 && manX == 650){
//            manY += speed;
//        }
//
//        if(manY == 650 && manX > 130){
//            manX -= speed;
//        }
//
//        if(manY > 130 && manX == 130){
//            manY -= speed;
//        }
        if(coordinates != null){
            manX = coordinates.get(t).getX();
            manY = coordinates.get(t).getY();
            man2X = coordinates.get(t2).getX();
            man2Y = coordinates.get(t2).getY();

            t += 3;
            t2 += 3;
            if(t > coordinates.size() - 1){
                t = 0;
            }
            if(t2 > coordinates.size() - 1){
                t2 = 0;
            }
        }
    }

    private int t = 0;
    private int t2 = 0;
    private void drawSquare(int x1, int y1, int x2, int y2, Canvas canvas) {
        square = new Path();

        square.addCircle(getWidth() / 2, getWidth() / 2, getWidth() / 2 - man.getHeight(), Path.Direction.CW);

        //t++;

        canvas.drawPath(square, green_paintbrush_stroke);

        PathMeasure pm = new PathMeasure(square, false);
        float aCoordinates[] = {0f, 0f};
        if(coordinates == null){
            coordinates = new ArrayList<>();
            for(int x = 0; x < pm.getLength(); x++){
                pm.getPosTan(x, aCoordinates, null);
                Coordinates c = new Coordinates();
                c.setX((int)aCoordinates[0]);
                c.setY((int)aCoordinates[1]);
                coordinates.add(c);
            }
            t2 = coordinates.size() / 2;
        }
    }
}
