package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

public class Player extends Sprite implements BoxCollidable {
    private static final String TAG = Player.class.getSimpleName();
    private boolean isMove = false;
    private boolean isJump = false;
    protected RectF boundingRect = new RectF();

    private float jumpPower = 20.f;
    private float jumpTime = 0.f;
    private float originalY = 0.f;

    public Player(float x, float y) {
        super(x, y, R.dimen.player_radius, R.mipmap.player);
        originalY = y;
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;
        if (isJump == true) {
            jumping();
        }
        if (isMove == true) {
            float dx = frameTime * Metrics.size(R.dimen.player_speed);
            dstRect.offset(dx, 0);
        }
        boundingRect.set(dstRect);
    }

    private void jumping() {
        float jumpY = originalY;

        jumpY = jumpPower * jumpTime - 9.8f * jumpTime * jumpTime * 0.5f;
        jumpTime += 3.f * MainGame.getInstance().frameTime;
        if (dstRect.bottom > 500.f + radius) {
            isJump = false;
            jumpTime = 0.f;

        }
        dstRect.offset(0, -jumpY);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void setIsMove(boolean isMove) {this.isMove = isMove;}
    public void setIsJump(boolean isJump) {this.isJump = isJump;}

    @Override
    public RectF getBoudingRect() {
        return boundingRect;
    }

    public void attack() {
        Bullet bullet = new Bullet(dstRect.right, dstRect.top + dstRect.bottom/2);
        MainGame.getInstance().add(bullet);
    }
}
