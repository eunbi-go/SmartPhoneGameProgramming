package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.Map;

public class Enemy extends AnimSprite implements BoxCollidable{
    protected RectF boundingRect = new RectF();
    private float frameTime = 0.f;
    private float dx = 0.f;
    boolean left = false;
    private float limitLeftX, limitRightX;
    float speed;
    private RectF playerRt = new RectF();
    private boolean isStart = false;

    public Enemy(float x, float y) {
        super(x, y, R.dimen.normalEnemy_radius, R.mipmap.normal_enemy, 7, 2);
        dstRect.set(x, y, x + Metrics.size(R.dimen.normalEnemy_radius), y + Metrics.size(R.dimen.normalEnemy_radius));
        isMoving = true;
        limitLeftX = x - 100.f;
        limitRightX = x + 100.f;
        speed = Metrics.size(R.dimen.enemy_speed);
        boundingRect.set(dstRect);
    }

    public Enemy(float x, float y, int radiusDimenID, int bitmpaResID) {
        super(x, y, radiusDimenID, R.mipmap.normal_enemy, 7, 2);
        isMoving = true;
    }

    public void update(float frameTime) {
        playerRt = MainScene.getInstance().getPlayerRect();
        if (MainScene.getInstance().player.isMove()) {
            if (MainScene.getInstance().player.getIsLeftMove())
                speed = -MapLoader.get().speed;
            else
                speed = MapLoader.get().speed;
        }

        this.frameTime += frameTime;
        if (this.frameTime >= 1.f) {
            this.frameTime = 0.f;
            left = !left;
        }

        if (!isStart) {
        if (dstRect.left - playerRt.left < 500.f) {
            isStart = true;
        }
        else isStart = false;
        }



        if (isStart) {
            // 움직이기 시작
            dx = frameTime * Metrics.size(R.dimen.enemy_speed);
            if (left) {
                dx = -dx;
            }
            dstRect.offset(dx, 0);
            boundingRect.offset(dx, 0);
        }
        else {

            dstRect.offset(frameTime * speed, 0);
            boundingRect.offset(frameTime * speed, 0);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        long now;
        int frameIndex = 0;

        now = System.currentTimeMillis();
        float time = (now - createdOn) / 1000.0f;
        frameIndex = Math.round(time * framesPerSecond) % frameCount;

        srcRect.set(frameIndex * imageWidth, 0,
                (frameIndex + 1) * imageWidth, imageHeight);
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }

    public void changeDirection() {left = !left;}

    @Override
    public RectF getBoudingRect() {
        return boundingRect;
    }
    public RectF getDstRect() {return dstRect;}
}
