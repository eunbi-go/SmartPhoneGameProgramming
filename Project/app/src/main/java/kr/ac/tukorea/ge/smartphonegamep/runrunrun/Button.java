package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import com.google.android.material.tabs.TabLayout;

import javax.security.auth.callback.Callback;

public class Button implements GameObject, BoxCollidable, Touchable{
    private static final String TAG = Button.class.getSimpleName();


    protected final Callback callback;
    protected Bitmap bitmap;
    protected Bitmap afterBitmap;
    protected RectF dstRect = new RectF();
    protected float x, y, radius;
    protected RectF boundingRect = new RectF();
    private int bitmapResId;
    private boolean isOn = false;
    private boolean pressed = false;

    public enum Action {
        pressed, released,
    }

    public interface Callback {
        public boolean onTouch(Action action);
    }

    public Button(float x, float y, int radiusDimenResId, int bitmapResId, Callback callback) {
        this.x = x;
        this.y = y;
        this.radius = Metrics.size(radiusDimenResId);
        dstRect.set(x - radius, y - radius, x + radius, y + radius);
        bitmap = BitmapPool.get(bitmapResId);
        this.bitmapResId = bitmapResId;
        this.callback = callback;

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

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();
        if (!pressed && !dstRect.contains(x, y)) {
            Log.d(TAG, "안눌림");
            return false;
        }

        int action = e.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                pressed = true;
                return callback.onTouch(Action.pressed);
            case MotionEvent.ACTION_UP:
                pressed = false;
                return callback.onTouch(Action.released);
        }

        return false;
    }
}
