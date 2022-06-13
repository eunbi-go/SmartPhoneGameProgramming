package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;

public class Bullet extends Sprite implements BoxCollidable{
    protected final float dx;
    private float lifeTime;
    private float direction = 1.f;
    protected RectF boundingRect = new RectF();

    @Override
    public RectF getBoudingRect() {
        return boundingRect;
    }

    enum OBJ {PLAYER_BULLET, ENEMY_BULLET, OBJ_END};
    OBJ object;

    public Bullet(float x, float y) {
        super(x, y, R.dimen.bullet_radius, R.mipmap.ball);
        this.dx = Metrics.size(R.dimen.bullet_speed);

        boundingRect.set(x - Metrics.size(R.dimen.bullet_coll_radius), y - Metrics.size(R.dimen.bullet_coll_radius), x + Metrics.size(R.dimen.bullet_coll_radius), y + Metrics.size(R.dimen.bullet_coll_radius));
    }

    public void update(float frameTime) {
        lifeTime += frameTime;
        if (lifeTime >= 2.f) {
            MainScene.getInstance().remove(this);
        }

        float dx = frameTime * Metrics.size(R.dimen.bullet_speed) * direction;
        dstRect.offset(dx, 0);
        boundingRect.offset(dx, 0);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void setObject(OBJ obj) {object = obj;}
    public OBJ getObject() {return object;}

    public void setDirection(float direction) {this.direction = direction;}
}
