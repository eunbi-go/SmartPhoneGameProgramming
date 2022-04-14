package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View implements Choreographer.FrameCallback {
    private static final String TAG = GameView.class.getSimpleName();

    private static Bitmap attackBitmap;
    private long previousTimeNanos;
    private int framePerSecond;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void doFrame(long currentTimeNanos) {
        long now = currentTimeNanos;
        int elapsed = (int) (now - previousTimeNanos);

        if (elapsed != 0) {
            framePerSecond = 1_000_000_000 / elapsed;
            previousTimeNanos = now;
            MainGame.getInstance().update(elapsed);
            invalidate();
        }
        Choreographer.getInstance().postFrameCallback(this);
    }
}
