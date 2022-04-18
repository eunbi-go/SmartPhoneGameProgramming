package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Button implements GameObject, BoxCollidable{
    protected Bitmap bitmap;
    protected RectF dstRect = new RectF();
    protected float x, y, radius;
    protected RectF boundingRect = new RectF();

    public Button(float x, float y, int radiusDimenResId, int bitmapResId) {
        this.x = x;
        this.y = y;
        this.radius = Metrics.size(radiusDimenResId);
        dstRect.set(x - radius, y - radius, x + radius, y + radius);
        bitmap = BitmapPool.get(bitmapResId);
    }

    @Override
    public void update() {
        boundingRect.set(dstRect);
        boundingRect.inset(radius / 16, radius / 16);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    @Override
    public RectF getBoudingRect() {
        return boundingRect;
    }

    public RectF getDstRect() {
        return dstRect;
    }
}
