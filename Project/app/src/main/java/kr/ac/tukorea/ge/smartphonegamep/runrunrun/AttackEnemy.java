package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;

public class AttackEnemy extends AnimSprite implements BoxCollidable{
    protected RectF boundingRect = new RectF();
    private RectF playerRt = new RectF();
    private float dx = 0.f;
    private boolean isAttack = false;
    private float attackTime = 0.f;
    private boolean isLeft = false;

    public AttackEnemy(float x, float y) {
        //super(x, y, R.dimen.player_radius, R.mipmap.enemy2);
        super(x, y, R.dimen.attackEnemy_radius, R.mipmap.attack_enemy, 7, 4);
        reverseBitmap = BitmapPool.get(R.mipmap.attack_enemy_left);
        isMoving = false;
        boundingRect.set(dstRect);
    }

    public AttackEnemy(float x, float y, int radiusDimenID, int bitmpaResID) {
        //super(x, y, radiusDimenID, bitmpaResID);
        super(x, y, radiusDimenID, R.mipmap.attack_enemy, 60, 4);
        dstRect.set(x, y, x + Metrics.size(R.dimen.normalEnemy_radius), y + Metrics.size(R.dimen.normalEnemy_radius));
        isMoving = false;
    }

    public void update(float frameTime) {
        playerRt = MainScene.getInstance().getPlayerRect();
        float speed = 0.f, dx = 0.f;

        if (MainScene.getInstance().player.isMove()) {
            if (MainScene.getInstance().player.getIsLeftMove())
                speed = -MapLoader.get().speed;
            else
                speed = MapLoader.get().speed;
        }

        dx = frameTime * Metrics.size(R.dimen.enemy_speed);

        if (dstRect.left - playerRt.left < 1900.f) {
            if (playerRt.left - 80.f > dstRect.right) {
                dx = dx;
                isAttack = false;
                attackTime = 0.f;
                dstRect.offset(dx, 0);
                boundingRect.offset(dx, 0);
                isMoving = true;
                isLeft = false;
            } else if (playerRt.right + 80.f < dstRect.left) {
                dx = -dx;
                isAttack = false;
                isMoving = true;
                attackTime = 0.f;
                dstRect.offset(dx, 0);
                boundingRect.offset(dx, 0);
                isLeft = true;
            } else {
                dstRect.offset(frameTime * speed, 0);
                boundingRect.offset(frameTime * speed, 0);
                isAttack = true;
                isMoving = false;
                attack(frameTime);
            }
        }
        else {
            dstRect.offset(frameTime * speed, 0);
            boundingRect.offset(frameTime * speed, 0);
        }
    }

    private void attack(float frameTime) {
        attackTime += frameTime;
        if (attackTime > 2.f) {
            Bullet bullet = new Bullet(dstRect.centerX(), dstRect.centerY());
            bullet.setObject(Bullet.OBJ.ENEMY_BULLET);
            if (isLeft)
                bullet.setDirection(-1.f);
            else
                bullet.setDirection(1.f);
            MainScene.getInstance().add(MainScene.Layer.bullets.ordinal(), bullet);

            attackTime = 0.f;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        long now;
        int frameIndex = 0;

        if (isMoving) {
            now = System.currentTimeMillis();
            float time = (now - createdOn) / 1000.0f;
            frameIndex = Math.round(time * framesPerSecond) % frameCount;
        }

        srcRect.set(frameIndex * imageWidth, 0,
                (frameIndex + 1) * imageWidth, imageHeight);

        if (isLeft)
            canvas.drawBitmap(reverseBitmap, srcRect, dstRect, null);
        else
            canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }

    public RectF getDstRect() {return dstRect;}

    @Override
    public RectF getBoudingRect() {
        return boundingRect;
    }
}
