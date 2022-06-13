package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;

import androidx.constraintlayout.motion.widget.CustomFloatAttributes;

public class ShieldBlock extends AnimSprite implements BoxCollidable{
    private final RectF boundingRect = new RectF();

    public ShieldBlock(float x, float y) {
        super(x, y, R.dimen.block_radius, R.mipmap.shield, 7, 3);

        float left = x;
        float top = y;
        dstRect.set(x, y, x + Metrics.size(R.dimen.block_radius), y + Metrics.size(R.dimen.block_radius));
        boundingRect.set(dstRect);
    }

    public void update(float frameTime) {
        float speed = 0.f;
        if (MainScene.getInstance().player.isMove()) {
            if (MainScene.getInstance().player.getIsLeftMove())
                speed = -MapLoader.get().speed;
            else
                speed = MapLoader.get().speed;
            float dx = speed * frameTime;
            dstRect.offset(dx, 0);
            boundingRect.offset(dx, 0);
            //if (dstRect.right < 0)
            //BaseGame.getInstance().remove(this);
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
    @Override
    public RectF getBoudingRect() {
        return boundingRect;
    }
}
