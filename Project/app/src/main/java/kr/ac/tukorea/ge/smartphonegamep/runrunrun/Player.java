package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

public class Player extends AnimSprite implements BoxCollidable {
    private static final String TAG = Player.class.getSimpleName();
    private boolean isMove = false;
    private boolean isJump = false;
    protected RectF boundingRect = new RectF();

    private float jumpPower = 15.f;
    private float jumpTime = 0.f;
    private float originalY = 0.f;

    private float attackTime = 0.f;
    private boolean isLeft = false;

    public Player(float x, float y) {
        super(x, y, R.dimen.player_radius, R.mipmap.player_walk, 20, 4);
        originalY = y;
    }

    public void update(float frameTime) {
        float dx = 0.f, dy = 0.f;


        if (isMove == true) {
            isMoving = true;
            if (isLeft)
                dx = -frameTime * Metrics.size(R.dimen.player_speed);
            else
                dx = frameTime * Metrics.size(R.dimen.player_speed);

        }
        else
            isMoving = false;
        if (isJumping == true) {
            dy = jumping(frameTime);
            if (isJumping)
                dstRect.offset(dx, -dy);
        }
        else
            dstRect.offset(dx, 0.f);

        if (isAttack) {
            attackTime += frameTime;
            if (attackTime > 0.3f) {
                attackTime = 0.f;
                isAttack = false;
                isMoving = false;
            }
        }
        boundingRect.set(dstRect);
    }

    private float jumping(float frameTime) {
        float jumpY = originalY;

        jumpY = jumpPower * jumpTime - 9.8f * jumpTime * jumpTime * 0.5f;
        jumpTime += 10.f * frameTime;

        //dstRect.offset(0, -jumpY);

        if (dstRect.bottom > y + radius) {
            dstRect.bottom = y + radius;
            dstRect.top = y - radius;
            isJumping = false;
            jumpTime = 0.f;
        }
        return jumpY;
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
        else if (isJumping) {
            frameIndex = 3;
        }
        else if (isAttack) {
            now = System.currentTimeMillis();
            float time = (now - createdOn) / 1000.0f;
            frameIndex = Math.round(time * framesPerSecond) % 3;
        }


        srcRect.set(frameIndex * imageWidth, 0,
                (frameIndex + 1) * imageWidth, imageHeight);

        if (isLeft)
            canvas.drawBitmap(reverseBitmap, srcRect, dstRect, null);
        else
            canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }

    public void setIsMove(boolean isMove) {this.isMove = isMove;}
    public void setIsJump(boolean isJump) {this.isJumping = isJump;}
    public void setIsLeftMove(boolean isLeft) {this.isLeft = isLeft;}
    @Override
    public RectF getBoudingRect() {
        return boundingRect;
    }
    public RectF getDstRect() {return dstRect;}

    public void attack() {
        Bullet bullet = new Bullet(dstRect.centerX(), dstRect.centerY());
        bullet.setObject(Bullet.OBJ.PLAYER_BULLET);
        if (isLeft)
            bullet.setDirection(-1.f);
        else
            bullet.setDirection(1.f);
        MainGame.getInstance().add(MainGame.Layer.bullets.ordinal(), bullet);

        isAttack = true;
    }
}
