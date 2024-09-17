// TODO: change the package name accordingly
package edu.sjsu.android.project2jasminelao;

public class Particle {
    private static final float COR = 0.7f;
    private float mVelX;
    private float mVelY;

    // mPosX and mPosY are used to position the ball
    public float mPosX;
    public float mPosY;

    /**
     * Calculate displacement of the particle along the X and Y axis.
     *
     * @param x acceleration of x-axis
     * @param y acceleration of x-axis
     * @param timestamp timestamp of the sensor event
     */
    public void updatePosition(float x, float y, long timestamp) {
        // change the divisor to change the speed of the ball
        float dt = (System.nanoTime() - timestamp) / 150000000.0f;
        mVelX += -x * dt;
        mVelY += -y * dt;
        mPosX += mVelX * dt;
        mPosY += mVelY * dt;
    }

    /**
     * Adds a bounce effect when it collides with the boundary.
     *
     * @param horizontalBound horizontal bound of the screen
     * @param verticalBound vertical bound of the screen
     */
    public void resolveCollisionWithBounds(float horizontalBound, float verticalBound) {
        if (mPosX > horizontalBound) {
            mPosX = horizontalBound;
            mVelX = -mVelX * COR;
        } else if (mPosX < -horizontalBound) {
            mPosX = -horizontalBound;
            mVelX = -mVelX * COR;
        }
        if (mPosY > verticalBound) {
            mPosY = verticalBound;
            mVelY = -mVelY * COR;
        } else if (mPosY < -verticalBound) {
            mPosY = -verticalBound;
            mVelY = -mVelY * COR;
        }
    }
}
