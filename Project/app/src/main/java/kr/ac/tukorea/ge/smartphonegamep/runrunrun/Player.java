package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;

public class Player extends Sprite{
    private static final String TAG = Player.class.getSimpleName();
    private boolean isMove = false;

    public Player(float x, float y) {
        super(x, y, R.dimen.player_radius, R.mipmap.player);
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;
        if (isMove == true) {
            float dx = frameTime * Metrics.size(R.dimen.player_speed);
            dstRect.offset(dx, 0);

        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void setIsMove(boolean isMove) {this.isMove = isMove;}
}
