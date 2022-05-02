package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import androidx.constraintlayout.motion.widget.CustomFloatAttributes;

public class AnimSprite extends Sprite{
    protected final int imageHeight;
    protected final int imageWidth;
    protected final int frameCount;
    private Rect srcRect = new Rect();
    private final float framesPerSecond;
    private long createdOn;
    protected boolean isMoving = false;

    public AnimSprite(float x, float y, int radiusDimenID, int bitmpaResID, float framePerSecond, int frameCount) {
        super(x, y, radiusDimenID, bitmpaResID);

        imageHeight = bitmap.getHeight();
        if (frameCount == 0) {
            imageWidth = imageHeight;
            frameCount = bitmap.getWidth() / imageHeight;
        } else {
            imageWidth = bitmap.getWidth() / frameCount;
        }

        this.framesPerSecond = framePerSecond;
        this.frameCount = frameCount;
        srcRect.set(0, 0, imageWidth, imageHeight);

        createdOn = System.currentTimeMillis();
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

        srcRect.set(frameIndex * imageWidth, 0,
                (frameIndex + 1) * imageWidth, imageHeight);
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }
}
