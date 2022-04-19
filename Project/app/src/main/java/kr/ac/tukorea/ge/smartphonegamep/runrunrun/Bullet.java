package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Bullet extends Sprite {
    protected final float dx;

    public Bullet(float x, float y) {
        super(x, y, R.dimen.bullet_radius, R.mipmap.ball);
        this.dx = Metrics.size(R.dimen.bullet_speed);
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;
        float dx = frameTime * Metrics.size(R.dimen.player_speed);
        dstRect.offset(dx, 0);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }
}
