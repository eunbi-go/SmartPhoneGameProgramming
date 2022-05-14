package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

public class Button implements GameObject, BoxCollidable{
    protected Bitmap bitmap;
    protected Bitmap afterBitmap;
    protected RectF dstRect = new RectF();
    protected float x, y, radius;
    protected RectF boundingRect = new RectF();
    private int bitmapResId;
    private boolean isOn = false;

    public Button(float x, float y, int radiusDimenResId, int bitmapResId) {
        this.x = x;
        this.y = y;
        this.radius = Metrics.size(radiusDimenResId);
        dstRect.set(x - radius, y - radius, x + radius, y + radius);
        bitmap = BitmapPool.get(bitmapResId);
        this.bitmapResId = bitmapResId;

        if (bitmapResId == R.mipmap.before_attack)
            afterBitmap = BitmapPool.get(R.mipmap.after_attack);
    }

    @Override
    public void update(float frameTime) {
        boundingRect.set(dstRect);
        boundingRect.inset(radius / 16, radius / 16);
    }

    @Override
    public void draw(Canvas canvas) {
        if (!isOn)
            canvas.drawBitmap(bitmap, null, dstRect, null);
        else
            canvas.drawBitmap(afterBitmap, null, dstRect, null);
    }

    public void onAttack(boolean isAttack) {
        if (bitmapResId == R.mipmap.before_attack) {
            isOn = isAttack;
        }
    }

    @Override
    public RectF getBoudingRect() {
        return boundingRect;
    }
    public RectF getDstRect() {
        return dstRect;
    }
}
