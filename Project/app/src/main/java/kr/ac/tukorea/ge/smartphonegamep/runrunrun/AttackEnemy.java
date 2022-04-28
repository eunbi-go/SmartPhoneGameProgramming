package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;

public class AttackEnemy extends Sprite{
    private RectF playerRt = new RectF();
    private float dx = 0.f;

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
            dstRect.offset(dx, 0);
        }
        else if (playerRt.right + 300.f < dstRect.left ) {
            dx = -dx;
            dstRect.offset(dx, 0);
        }

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }
}
