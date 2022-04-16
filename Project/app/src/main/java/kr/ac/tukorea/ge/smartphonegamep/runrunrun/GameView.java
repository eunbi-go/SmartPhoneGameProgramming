package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View implements Choreographer.FrameCallback {
    private static final String TAG = GameView.class.getSimpleName();
    public static GameView view;

    private static Bitmap attackBitmap;
    private long previousTimeNanos;
    private int framePerSecond;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        view = this;

        MainGame game = MainGame.getInstance();
        game.init();
        Choreographer.getInstance().postFrameCallback(this);
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

    protected void onDraw(Canvas canvas) {
        MainGame.getInstance().draw(canvas);
    }
}
