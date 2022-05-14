package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.RectF;

public class Sprite implements GameObject {
    protected Bitmap bitmap;
    protected Bitmap reverseBitmap;
    protected RectF dstRect = new RectF();
    protected float x, y, radius;

    public Sprite(float x, float y, int radiusDimenID, int bitmpaResID) {
        this.x = x;
        this.y = y;
        this.radius = Metrics.size(radiusDimenID);
        dstRect.set(x - radius, y - radius, x + radius, y + radius);
        bitmap = BitmapPool.get(bitmpaResID);

        Matrix matrix = new Matrix();
        matrix.preScale(-1.f, 1.f);
        reverseBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
    }

    public Sprite(float x, float y, float w, float h, int bitmapResId) {
        this.x = x;
        this.y = y;
        this.radius = w / 2;
        dstRect.set(x - w / 2, y - h / 2, x + w / 2, y + h / 2);
        bitmap = BitmapPool.get(bitmapResId);

        Matrix matrix = new Matrix();
        matrix.preScale(-1.f, 1.f);
        reverseBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
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
