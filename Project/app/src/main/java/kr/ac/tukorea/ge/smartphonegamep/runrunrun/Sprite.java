package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Sprite implements GameObject {

    protected Bitmap bitmap;
    protected RectF dstRect = new RectF();
    protected float x, y, radius;

    public Sprite(float x, float y, int radiusDimenID, int bitmpaResID) {
        this.x = x;
        this.y = y;
        this.radius = Metrics.size(radiusDimenID);
        dstRect.set(x - radius, y - radius, x + radius, y + radius);
        bitmap = BitmapPool.get(bitmpaResID);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }
}
