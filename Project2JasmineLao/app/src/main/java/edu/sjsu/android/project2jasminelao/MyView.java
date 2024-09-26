package edu.sjsu.android.project2jasminelao;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

public class MyView extends View {
    private MyListener listener;
    private final int BALL_SIZE = 300;
    private Bitmap field, ball;
    private float originX, originY, horizontalBound, verticalBound;
    private Particle particle;

    // TODO: have a Paint object to configure how to draw your name
    private Paint paint;

    public MyView(Context context) {
        super(context);
        listener = new MyListener(context);
        field = BitmapFactory.decodeResource(context.getResources(), R.drawable.field);
        ball = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball);
        ball = Bitmap.createScaledBitmap(ball, BALL_SIZE, BALL_SIZE, false);
        particle = new Particle();

        // TODO: initialize the Paint object
        paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.WHITE);
    }

    public MyListener getListener() {
        return listener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
        // Origin is center of screen
        originX = w / 2f; // dividing by a float
        originY = h / 2f;
        // Bounds is the distance between the edge of the ball and screen
        horizontalBound = (w - BALL_SIZE) / 2f;
        verticalBound = (h - BALL_SIZE) / 2f;
        field = Bitmap.createScaledBitmap(field, w, h, false);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas){
        super.onDraw(canvas);
        // Draw the field Bitmap object so that it occupies the whole screen
        canvas.drawBitmap(field, 0, 0, null);
        canvas.drawBitmap(ball, originX + particle.mPosX - (BALL_SIZE / 2f), originY - particle.mPosY - (BALL_SIZE / 2f), null);
        canvas.drawText("Jasmine Lao", 150, 200, paint);

        // Update particle object using sensor data
        particle.updatePosition(listener.getX(), listener.getY(), listener.getTimestamp());
        particle.resolveCollisionWithBounds(horizontalBound, verticalBound);
        invalidate();
    }
}
