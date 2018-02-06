package com.sketchView.tools;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by keshav on 08/04/17.
 */

public abstract class SketchTool implements View.OnTouchListener {

    public static final int TYPE_PEN = 0;
    public static final int TYPE_ERASE = 1;

    View touchView;
    boolean useStylus;

    SketchTool(View touchView) {
        this.touchView = touchView;
        this.useStylus = true;
    }

    public abstract void render(Canvas canvas);

    public abstract void clear();

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        // *** RFD: added check for stylus
        // *** TODO: use getPressure to make color darker with more pressure.
        // http://tool.oschina.net/uploads/apidocs/android/resources/samples/ApiDemos/src/com/example/android/apis/graphics/TouchPaint.html
        if(!this.useStylus || (event.getToolType(0)==event.TOOL_TYPE_STYLUS)) {
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    onTouchDown(event);
                    break;
                case MotionEvent.ACTION_MOVE:
                    onTouchMove(event);
                    break;
                case MotionEvent.ACTION_UP:
                    onTouchUp(event);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    onTouchCancel(event);
                    break;
            }
        }
        return true;
    }

    abstract void onTouchDown(MotionEvent event);

    abstract void onTouchMove(MotionEvent event);

    abstract void onTouchUp(MotionEvent event);

    abstract void onTouchCancel(MotionEvent event);

}
