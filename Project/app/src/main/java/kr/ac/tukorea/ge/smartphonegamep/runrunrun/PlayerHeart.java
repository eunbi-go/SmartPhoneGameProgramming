package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class PlayerHeart implements GameObject{
    private final Bitmap bitmap;
    private RectF srcRect = new RectF();
    private RectF dstRect = new RectF();
    private final int imageWidth, imageHeight;
    private final float radius;
    private boolean isDead = false;
    private int index = -1;

    public PlayerHeart(int mipmapResId, int radius, float x, float y, int index) {
        this.bitmap = BitmapPool.get(mipmapResId);

        this.index = index;
        this.imageWidth = bitmap.getWidth();
        this.imageHeight = bitmap.getHeight();
        this.radius = Metrics.size(radius);

        srcRect.set(0, 0, imageWidth, imageHeight);
        dstRect.set(x - this.radius, y - this.radius, x + this.radius, y + this.radius);
    }

    @Override
    public void update(float frameTime) {

    }

    @Override
    public void draw(Canvas canvas) {
        if (!isDead)
            canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    void setDead(boolean isDead) {this.isDead = isDead;}
    boolean getIsDead() {return isDead;}
}
