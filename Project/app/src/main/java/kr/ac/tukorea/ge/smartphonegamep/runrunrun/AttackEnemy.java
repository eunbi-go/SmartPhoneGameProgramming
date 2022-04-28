package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;

public class AttackEnemy extends Sprite{
    private RectF playerRt = new RectF();
    private float dx = 0.f;
    private boolean isAttack = false;
    private float attackTime = 0.f;

    public AttackEnemy(float x, float y) {
        super(x, y, R.dimen.player_radius, R.mipmap.enemy2);
    }

    public AttackEnemy(float x, float y, int radiusDimenID, int bitmpaResID) {
        super(x, y, radiusDimenID, bitmpaResID);
    }

    public void update() {
        playerRt = MainGame.getInstance().getPlayerRect();
        dx = MainGame.getInstance().frameTime * Metrics.size(R.dimen.enemy_speed);

        if (playerRt.left - 300.f > dstRect.right) {
            dx = dx;
            isAttack = false;
            attackTime = 0.f;
            dstRect.offset(dx, 0);
        }
        else {
            isAttack = true;
        }
        if (playerRt.right + 300.f < dstRect.left ) {
            dx = -dx;
            isAttack = false;
            attackTime = 0.f;
            dstRect.offset(dx, 0);
        }
        else {
            isAttack = true;
        }

        if (isAttack)
            attack();
    }

    private void attack() {
        attackTime += MainGame.getInstance().frameTime;
        if (attackTime > 3.f) {
            Bullet bullet = new Bullet(dstRect.right, dstRect.top + bitmap.getHeight() / 2);
            MainGame.getInstance().add(bullet);

            attackTime = 0.f;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }
}
