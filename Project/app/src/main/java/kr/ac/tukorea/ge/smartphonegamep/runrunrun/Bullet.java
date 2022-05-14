package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Bullet extends Sprite {
    protected final float dx;
    private float lifeTime;
    enum OBJ {PLAYER_BULLET, ENEMY_BULLET, OBJ_END};
    OBJ object;
    public Bullet(float x, float y) {
        super(x, y, R.dimen.bullet_radius, R.mipmap.ball);
        this.dx = Metrics.size(R.dimen.bullet_speed);
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;

        lifeTime += frameTime;
        if (lifeTime >= 1.f) {
            MainGame.getInstance().remove(this);
        }

        float dx = frameTime * Metrics.size(R.dimen.player_speed);
        dstRect.offset(dx, 0);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void setObject(OBJ obj) {object = obj;}
    public OBJ getObject() {return object;}
}
