package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

public class MainGame {
    private static final String TAG = MainGame.class.getSimpleName();
    private static MainGame singleton;
    public float frameTime;

    private ArrayList<GameObject> objects = new ArrayList<>();
    private Player player;
    private Button attackButton, moveButton, jumpButton;

    public static MainGame getInstance() {
        if (singleton == null) {
            singleton = new MainGame();
        }
        return singleton;
    }

    public static void clear() {
        singleton = null;
    }

    public void init() {
        objects.clear();

        player = new Player(0,0);
        objects.add(player);

        attackButton = new Button(Metrics.width - 200, Metrics.height - 200, R.dimen.button_radius, R.mipmap.attack);
        moveButton = new Button(Metrics.width/6, Metrics.height-200, R.dimen.button_radius, R.mipmap.run);
        jumpButton = new Button(Metrics.width/3, Metrics.height-200, R.dimen.button_radius, R.mipmap.jump);
    }

    public void update(int elapsedNanos) {
        frameTime = elapsedNanos * 1e-9f; // 1_000_000_000.0f;

        for (GameObject obj : objects) {
            obj.update();
        }
    }

    public void draw(Canvas canvas) {
        for (GameObject obj : objects) {
            obj.draw(canvas);
        }

        attackButton.draw(canvas);
        moveButton.draw(canvas);
        jumpButton.draw(canvas);
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                player.setIsMove(true);
                return true;
        }
        player.setIsMove(false);
        return false;
    }
}
