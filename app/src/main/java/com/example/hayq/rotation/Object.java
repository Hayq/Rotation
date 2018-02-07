package com.example.hayq.rotation;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Object extends SurfaceView implements SurfaceHolder.Callback {
    private int w = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int h = Resources.getSystem().getDisplayMetrics().heightPixels;

    private Paint paint, textPaint;

    private MyThread thread;
    private float deltaX = 0;
    private float startX = 0;
    private float angle = 0;
    private float cAngle = 0;
    private int click = 0;

    public Object(Context context)
    {
        super(context);
        getHolder().addCallback(this);
        thread = new MyThread(getHolder(), this);
        setFocusable(true);

        paint = new Paint();
        paint.setColor(Color.WHITE);

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(40);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if( this.click == 1 ){
            angle = startX - deltaX;
        }

        canvas.drawText( "Exponent: " + String.valueOf((int)cAngle/100), 100, 100, textPaint);

        cAngle = cAngle + angle * this.click;
        canvas.rotate( cAngle/100,w/2, h/2);
        canvas.drawRect(w/2-100, h/2-100, w/2 + 100, h/2 + 100, paint);
    }

    public void setStartX(float start) {
        this.startX = start;
    }

    public void setDeltaX(float deltaX) {
        this.deltaX = deltaX;
    }

    public void click(int ck){
        this.click = ck;
    }
}
