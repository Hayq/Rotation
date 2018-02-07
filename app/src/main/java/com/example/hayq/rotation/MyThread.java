package com.example.hayq.rotation;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by hayq on 2/7/2018.
 */

public class MyThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private Object object;
    private boolean running;
    private Canvas canvas;

    MyThread(SurfaceHolder surfaceHolder, Object object)
    {
        super();
        this.surfaceHolder = surfaceHolder;
        this.object = object;
    }

    @Override
    public void run()
    {
        while (running) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized(surfaceHolder) {
                    this.object.draw(canvas);
                }
            } catch (Exception e) {       }
            finally {
                if (canvas != null)            {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void setRunning(boolean b)
    {
        running=b;
    }
}