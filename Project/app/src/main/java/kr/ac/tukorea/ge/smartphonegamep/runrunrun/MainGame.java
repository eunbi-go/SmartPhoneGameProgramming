package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;
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

        player = new Player(0,500);
        objects.add(player);

        ItemBlock block = new ItemBlock(400, 250, R.dimen.block_radius, R.mipmap.item_block);
        objects.add(block);

        GroundBlock groundBlock = new GroundBlock(100, 100, R.dimen.block_radius, R.mipmap.normal_block);
        objects.add(groundBlock);

        Enemy enemy = new Enemy(100, 500);
        objects.add(enemy);

        //AttackEnemy attackEnemy = new AttackEnemy(100, 500);
        //objects.add(attackEnemy);

        attackButton = new Button(Metrics.width - 200, Metrics.height - 200, R.dimen.button_radius, R.mipmap.attack);
        moveButton = new Button(Metrics.width/6, Metrics.height-200, R.dimen.button_radius, R.mipmap.run);
        jumpButton = new Button(Metrics.width/3, Metrics.height-200, R.dimen.button_radius, R.mipmap.jump);
    }

    public RectF getPlayerRect() {
        return player.getDstRect();
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
                int x = (int) event.getX();
                int y = (int) event.getY();
                RectF clickRtF = new RectF(x - 5, y - 5, x + 5, y + 5);

                if (CollisionHelper.collideRectF(moveButton.getDstRect(), clickRtF))
                    player.setIsMove(true);
                if (CollisionHelper.collideRectF(attackButton.getDstRect(), clickRtF))
                    player.attack();
                if (CollisionHelper.collideRectF(jumpButton.getDstRect(), clickRtF))
                    player.setIsJump(true);
                return true;
        }
        player.setIsMove(false);
        return false;
    }

    public void add(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                objects.add(gameObject);
            }
        });
    }

    public void remove(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                objects.remove(gameObject);
            }
        });
    }
}
