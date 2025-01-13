package com.example.smartparktn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;

public class CircularProgressView extends View {
    private Paint paint;
    private RectF rectF;
    private float progress = 1f;
    private static final float STROKE_WIDTH = 10f;
    private static final float START_ANGLE = -90f;
    private static final float MAX_ANGLE = 360f;

    public CircularProgressView(Context context) {
        super(context);
        init();
    }

    public CircularProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(STROKE_WIDTH);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.white));
        rectF = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float padding = STROKE_WIDTH / 2;
        rectF.set(padding, padding, w - padding, h - padding);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float sweepAngle = MAX_ANGLE * progress;
        canvas.drawArc(rectF, START_ANGLE, sweepAngle, false, paint);
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }
}

