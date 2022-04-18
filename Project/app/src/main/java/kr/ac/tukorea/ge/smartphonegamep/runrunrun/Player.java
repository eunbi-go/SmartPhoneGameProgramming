package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;

public class Player extends Sprite implements BoxCollidable {
    private static final String TAG = Player.class.getSimpleName();
    private boolean isMove = false;
    protected RectF boundingRect = new RectF();

    public Player(float x, float y) {
        super(x, y, R.dimen.player_radius, R.mipmap.player);
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;
        if (isMove == true) {
            float dx = frameTime * Metrics.size(R.dimen.player_speed);
            dstRect.offset(dx, 0);
        }
        boundingRect.set(dstRect);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void setIsMove(boolean isMove) {this.isMove = isMove;}

    @Override
    public RectF getBoudingRect() {
        return boundingRect;
    }
}
