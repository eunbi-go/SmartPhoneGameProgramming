package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Item implements GameObject{
    protected Bitmap bitmap;
    protected RectF dstRect = new RectF();
    protected float x, y, radius;

    public Item(float x, float y, int radiusDimenId, int bitmapResID) {
        this.x = x;
        this.y = y;
        this.radius = Metrics.size(radiusDimenId);
        dstRect.set(x - radius, y - radius, x + radius, y + radius);
        bitmap = BitmapPool.get(bitmapResID);
    }

    @Override
    public void update(float frameTime) {

    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public RectF getDstRect() {return dstRect;}
}
