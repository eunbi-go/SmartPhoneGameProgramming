package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;

import java.util.ArrayList;

public class MainGame {
    private static final String TAG = MainGame.class.getSimpleName();
    private static MainGame singleton;
    private float frameTime;

    private ArrayList<GameObject> objects = new ArrayList<>();
    private Player player;

    public static MainGame getInstance() {
        if (singleton == null) {
            singleton = new MainGame();
        }
        return singleton;
    }

    public void init() {
        objects.clear();

        player = new Player(0,0);
        objects.add(player);
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
    }
}