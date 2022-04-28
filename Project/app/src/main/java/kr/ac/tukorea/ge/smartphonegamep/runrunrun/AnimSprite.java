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
    private int frameIndex;
    private float time;
    private final float framePerSecond;

    public AnimSprite(float x, float y, int radiusDimenID, int bitmpaResID, float framePerSecond, int frameCount) {
        super(x, y, radiusDimenID, bitmpaResID);
        imageHeight = bitmap.getHeight();
        if (frameCount == 0) {
            imageWidth = imageHeight;
            frameCount = bitmap.getWidth() / imageHeight;
        } else {
            imageWidth = bitmap.getWidth() / frameCount;
        }

        this.framePerSecond = framePerSecond;
        this.frameCount = frameCount;
        srcRect.set(0, 0, imageWidth, imageHeight);
    }

    @Override
    public void update() {
        time += MainGame.getInstance().frameTime;
        int frameIndex = Math.round(time * framePerSecond) % frameCount;
        srcRect.set(frameIndex * imageWidth, 0,
                (frameIndex + 1) * imageWidth, imageHeight);
        super.update();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }
}
