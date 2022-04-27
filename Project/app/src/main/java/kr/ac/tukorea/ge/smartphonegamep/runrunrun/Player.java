package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

public class Player extends Sprite implements BoxCollidable {
    private static final String TAG = Player.class.getSimpleName();
    private boolean isMove = false;
    private boolean isJump = false;
    protected RectF boundingRect = new RectF();

    private float jumpPower = 15.f;
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
        jumpTime += 10.f * MainGame.getInstance().frameTime;

        dstRect.offset(0, -jumpY);
        if (dstRect.bottom > y + radius) {
            dstRect.bottom = y + radius;
            dstRect.top = y - radius;
            isJump = false;
            jumpTime = 0.f;
        }
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
    public RectF getDstRect() {return dstRect;}

    public void attack() {
        Bullet bullet = new Bullet(dstRect.right, dstRect.top + dstRect.bottom/2);
        MainGame.getInstance().add(bullet);
    }
}
