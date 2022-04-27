package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;

public class Enemy extends Sprite implements BoxCollidable{
    protected RectF boundingRect = new RectF();
    private float frameTime = 0.f;
    private float dx = 0.f;
    boolean left = false;

    public Enemy(float x, float y) {
        super(x, y, R.dimen.player_radius, R.mipmap.enemy1);
    }

    public Enemy(float x, float y, int radiusDimenID, int bitmpaResID) {
        super(x, y, radiusDimenID, bitmpaResID);
    }

    public void update() {
        this.frameTime += MainGame.getInstance().frameTime;

        dx = MainGame.getInstance().frameTime * Metrics.size(R.dimen.enemy_speed);
        if (this.frameTime >= 2.f) {
            this.frameTime = 0.f;
            left = !left;
        }

        if (left)
            dx = -dx;

        dstRect.offset(dx, 0);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    @Override
    public RectF getBoudingRect() {
        return boundingRect;
    }
}
