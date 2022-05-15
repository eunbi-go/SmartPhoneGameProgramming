package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View implements Choreographer.FrameCallback {
    private static final String TAG = GameView.class.getSimpleName();
    public static GameView view;

    private static Bitmap attackBitmap;
    private long previousTimeNanos;
    private int framePerSecond;
    private boolean running, initialized;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //initView();
        view = this;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Metrics.width = w;
        Metrics.height = h;

        if (!initialized) {
            initView();
            initialized = true;
            running = true;
        }
    }

    private void initView() {
        view = this;

        BaseGame.getInstance().init();

        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override
    public void doFrame(long currentTimeNanos) {
        long now = currentTimeNanos;
        int elapsed = (int) (now - previousTimeNanos);

        if (elapsed != 0) {
            framePerSecond = 1_000_000_000 / elapsed;
            previousTimeNanos = now;
            BaseGame game = BaseGame.getInstance();
            game.update(elapsed);
            invalidate();
        }
        Choreographer.getInstance().postFrameCallback(this);
    }

    protected void onDraw(Canvas canvas) {
        BaseGame.getInstance().draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return BaseGame.getInstance().onTouchEvent(event);
    }



}
