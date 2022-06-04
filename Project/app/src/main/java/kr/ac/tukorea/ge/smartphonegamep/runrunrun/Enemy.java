package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.Map;

public class Enemy extends AnimSprite implements BoxCollidable{
    protected RectF boundingRect = new RectF();
    private float frameTime = 0.f;
    private float dx = 0.f;
    boolean left = false;
    private float limitLeftX, limitRightX;
    float speed;

    public Enemy(float x, float y) {
        super(x, y, R.dimen.normalEnemy_radius, R.mipmap.normal_enemy, 7, 2);
        dstRect.set(x, y, x + Metrics.size(R.dimen.normalEnemy_radius), y + Metrics.size(R.dimen.normalEnemy_radius));
        isMoving = true;
        limitLeftX = x - 100.f;
        limitRightX = x + 100.f;
        speed = Metrics.size(R.dimen.enemy_speed);
        boundingRect.set(dstRect);
    }

    public Enemy(float x, float y, int radiusDimenID, int bitmpaResID) {
        super(x, y, radiusDimenID, R.mipmap.normal_enemy, 7, 2);
        isMoving = true;
    }

    public void update(float frameTime) {
        this.frameTime += frameTime;
        dx = 1;
        float fTime = frameTime;
        if (left)
            fTime = fTime * -2.f;
        dx = fTime * Metrics.size(R.dimen.enemy_speed);

        if (this.frameTime >= 1.f) {
            this.frameTime = 0.f;
            left = !left;
        }



        dstRect.offset(dx, 0);
        boundingRect.offset(dx, 0);
    }

    @Override
    public void draw(Canvas canvas) {
        long now;
        int frameIndex = 0;

        now = System.currentTimeMillis();
        float time = (now - createdOn) / 1000.0f;
        frameIndex = Math.round(time * framesPerSecond) % frameCount;

        srcRect.set(frameIndex * imageWidth, 0,
                (frameIndex + 1) * imageWidth, imageHeight);
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }

    public void changeDirection() {left = !left;}

    @Override
    public RectF getBoudingRect() {
        return boundingRect;
    }
    public RectF getDstRect() {return dstRect;}
}
