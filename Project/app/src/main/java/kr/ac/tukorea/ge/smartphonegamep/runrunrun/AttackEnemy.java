package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;

public class AttackEnemy extends AnimSprite {
    private RectF playerRt = new RectF();
    private float dx = 0.f;
    private boolean isAttack = false;
    private float attackTime = 0.f;
    private boolean isLeft = false;

    public AttackEnemy(float x, float y) {
        //super(x, y, R.dimen.player_radius, R.mipmap.enemy2);
        super(x, y, R.dimen.normalEnemy_radius, R.mipmap.attack_enemy, 20, 4);
        isMoving = false;
    }

    public AttackEnemy(float x, float y, int radiusDimenID, int bitmpaResID) {
        //super(x, y, radiusDimenID, bitmpaResID);
        super(x, y, radiusDimenID, R.mipmap.attack_enemy, 60, 4);
        isMoving = false;
    }

    public void update() {
        playerRt = MainGame.getInstance().getPlayerRect();
        dx = MainGame.getInstance().frameTime * Metrics.size(R.dimen.enemy_speed);

        if (playerRt.left - 300.f > dstRect.right) {
            dx = dx;
            isAttack = false;
            attackTime = 0.f;
            dstRect.offset(dx, 0);
            isMoving = true;
            isLeft = false;
        }
        else if (playerRt.right + 300.f < dstRect.left ) {
            dx = -dx;
            isAttack = false;
            isMoving = true;
            attackTime = 0.f;
            dstRect.offset(dx, 0);
            isLeft = true;
        }
        else {
            isAttack = true;
            isMoving = false;
            attack();
        }

    }

    private void attack() {
        attackTime += MainGame.getInstance().frameTime;
        if (attackTime > 3.f) {
            Bullet bullet = new Bullet(dstRect.right, dstRect.centerY());
            bullet.setObject(Bullet.OBJ.ENEMY_BULLET);
            //MainGame.getInstance().add(bullet);
            MainGame.getInstance().add(MainGame.Layer.bullets.ordinal(), bullet);

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
}
