package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class PlayerBullet implements GameObject{

    private final Bitmap bitmap;
    private final int index;
    private final int imageWidth;
    private final int imageHeight;
    private final float radius;
    private final RectF srcRect = new RectF();
    private final RectF dstRect = new RectF();
    private boolean isDead = false;

    public PlayerBullet(int mipmapResId, int radius, float x, float y, int index) {
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

    @Override
    public RectF getDstRect() {
        return null;
    }

    public void setDead(boolean isDead) {this.isDead = isDead;}
    boolean getIsDead() {return isDead;}
    int getIndex() {return index;}
}
