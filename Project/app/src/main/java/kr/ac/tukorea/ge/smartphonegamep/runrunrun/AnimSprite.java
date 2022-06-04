package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import androidx.constraintlayout.motion.widget.CustomFloatAttributes;

public class AnimSprite extends Sprite{
    protected final int imageHeight;
    protected final int imageWidth;
    protected final int frameCount;
    protected Rect srcRect = new Rect();
    protected final float framesPerSecond;
    protected long createdOn;
    protected boolean isMoving = false;
    protected boolean isJumping = false;
    protected boolean isAttack = false;


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

    }
}
