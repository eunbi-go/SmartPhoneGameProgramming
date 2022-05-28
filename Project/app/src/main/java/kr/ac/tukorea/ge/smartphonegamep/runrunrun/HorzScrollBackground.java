package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;

public class HorzScrollBackground extends Sprite{
    private final float speed;
    private final int width;
    private boolean isMove = false;
    private boolean isLeft = false;

    public HorzScrollBackground(int bitmapResId, float speed) {
        super(Metrics.width / 2, Metrics.height / 2,
                Metrics.width, Metrics.height, bitmapResId);
        this.width = bitmap.getWidth() * Metrics.height / bitmap.getHeight();
        setDstRect(width, Metrics.height);
        this.speed = speed;
    }

    @Override
    public void update(float frameTime) {
        if (isMove) {
            if (isLeft) this.x += speed * frameTime;
            else    this.x -= speed * frameTime;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        int curr = (int)x % width;
        if (curr > 0) curr -= width;
        while (curr < Metrics.width) {
            dstRect.set(curr, 0, curr + width, Metrics.height);
            canvas.drawBitmap(bitmap, null, dstRect, null);
            curr += width;
        }
    }

    public void setIsMove(boolean isMove) {this.isMove = isMove;}
    public void setIsLeft(boolean isLeft) {this.isLeft = isLeft;}
}
