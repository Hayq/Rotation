package com.example.hayq.rotation;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    private Object object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        object = new Object(this);

        object.setOnTouchListener(this);

        setContentView(object);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                click(0);
                setStartX(motionEvent.getRawX());
                break;
            case MotionEvent.ACTION_MOVE:
                setAngle(motionEvent.getRawX());
                click(1);
                break;
            case MotionEvent.ACTION_UP:
                click(0);
                break;
        }

        return true;
    }

    private void click(int i) {
        object.click(i);
    }

    public void setStartX(float startX) {
        if( object != null )
            object.setStartX(startX);
    }

    public void setAngle(float deltaX) {
        if( object != null )
            object.setDeltaX(deltaX);
    }
}
