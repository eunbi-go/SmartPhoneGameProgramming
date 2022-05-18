package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;

public class Enemy extends AnimSprite implements BoxCollidable{
    protected RectF boundingRect = new RectF();
    private float frameTime = 0.f;
    private float dx = 0.f;
    boolean left = false;

    public Enemy(float x, float y) {
        super(x, y, R.dimen.normalEnemy_radius, R.mipmap.normal_enemy, 7, 2);
        isMoving = true;
    }

    public Enemy(float x, float y, int radiusDimenID, int bitmpaResID) {
        super(x, y, radiusDimenID, R.mipmap.normal_enemy, 7, 2);
        isMoving = true;
    }

    public void update(float frameTime) {
        this.frameTime += frameTime;

        dx = frameTime * Metrics.size(R.dimen.enemy_speed);
        if (this.frameTime >= 2.f) {
            this.frameTime = 0.f;
            left = !left;
        }

        if (left)
            dx = -dx;

        dstRect.offset(dx, 0);
    }

    //public void draw(Canvas canvas) {
      //  canvas.drawBitmap(bitmap, null, dstRect, null);
    //}

    @Override
    public RectF getBoudingRect() {
        return boundingRect;
    }
    public RectF getDstRect() {return dstRect;}
}
