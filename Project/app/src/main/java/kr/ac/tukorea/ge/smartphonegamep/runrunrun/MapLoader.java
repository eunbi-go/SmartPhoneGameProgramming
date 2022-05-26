package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.Random;

public class MapLoader implements GameObject {

    private static MapLoader instance;
    private final Random random;
    private float scroll;
    private int current;
    private float speed;
    private static String[] MAP_FILES = {
            "stage_01.txt"
    };

    private MapLoader() {
        random = new Random();
    }

    public static MapLoader get() {
        if (instance == null) {
            instance = new MapLoader();
        }
        return instance;
    }

    public void init(int mapIndex) {
        scroll = 0;
        current = 0;
        loadFromTextAsset(MAP_FILES[mapIndex]);
        speed = 5.f;
    }

    private void loadFromTextAsset(String filename) {
        
    }

    @Override
    public void update(float frameTime) {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public RectF getDstRect() {
        return null;
    }
}
