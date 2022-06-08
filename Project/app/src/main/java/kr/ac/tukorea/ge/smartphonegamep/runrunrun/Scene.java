package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

import java.util.ArrayList;

public class Scene {
    protected static Scene singleton;
    protected float frameTime, elapsedTime;
    private Paint collisionPaint;

    protected ArrayList<ArrayList<GameObject>> layers;

    public static Scene getInstance() {
        return singleton;
    }

    public static void clear() {
        singleton = null;
    }

    public void init() {
        elapsedTime = 0;

        collisionPaint = new Paint();
        collisionPaint.setColor(Color.WHITE);
        collisionPaint.setStyle(Paint.Style.STROKE);
    }

    protected void initLayers(int count) {
        layers = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            layers.add(new ArrayList<>());
        }
    }

    public void update(int elapsedNanos) {
        frameTime = (float) (elapsedNanos / 1_000_000_000f);
        elapsedTime += frameTime;
        for (ArrayList<GameObject> gameObjects : layers) {
            for (GameObject gobj : gameObjects) {
                gobj.update(frameTime);
            }
        }
    }

    public void draw(Canvas canvas) {
        for (ArrayList<GameObject> gameObjects : layers) {
            for (GameObject gobj : gameObjects) {
                gobj.draw(canvas);
                if (gobj instanceof BoxCollidable) {
                    RectF rect = ((BoxCollidable) gobj).getBoudingRect();
                    canvas.drawRect(rect, collisionPaint);
                }
            }
        }
    }

    public void add(int layerIndex, GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> gameObjects = layers.get(layerIndex);
                gameObjects.add(gameObject);
            }
        });
    }

    public void remove(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                for (ArrayList<GameObject> gameObjects : layers) {
                    boolean removed = gameObjects.remove(gameObject);
                    if (!removed) continue;
                }
            }
        });
    }

    public boolean onTouchEvent(MotionEvent event) {
        int touchLayer = getTouchLayerIndex();
        if (touchLayer < 0) return false;
        ArrayList<GameObject> gameObjects = layers.get(touchLayer);

        for (GameObject gobj : gameObjects) {
            if (!(gobj instanceof Touchable)) {
                continue;
            }
            boolean processed = ((Touchable) gobj).onTouchEvent(event);
            if (processed) return true;
        }
        return false;
    }

    public ArrayList<GameObject> objectsAt(int layerIndex) {
        return layers.get(layerIndex);
    }

    protected int getTouchLayerIndex() {
        return 6;
    }
}
