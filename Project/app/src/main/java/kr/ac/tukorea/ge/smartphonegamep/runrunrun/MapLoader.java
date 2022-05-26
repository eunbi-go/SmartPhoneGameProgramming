package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class MapLoader implements GameObject {
    private static final String TAG = MapLoader.class.getSimpleName();
    private static MapLoader instance;
    private final Random random;
    private float scroll;
    private int current;
    private float speed;
    private static String[] MAP_FILES = {
            "stage_01.txt"
    };
    private ArrayList<String> lines;
    private int colums;
    private int rows;
    private int length;
    private float unit;

    private MapLoader() {
        random = new Random();
        unit = MainGame.getInstance().size(1);
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
        speed = Metrics.size(R.dimen.bg_scroll_1);
    }

    private void loadFromTextAsset(String filename) {
        lines = new ArrayList<>();
        AssetManager assets = GameView.view.getContext().getAssets();
        try {
            InputStream is = assets.open(filename);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            String header = reader.readLine();
            String[] comps = header.split(" ");
            colums = Integer.parseInt(comps[0]);
            rows = Integer.parseInt(comps[1]);

            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                lines.add(line);
            }

            int pages = lines.size() / rows;
            int lastCol = lines.get(lines.size() - 1).length();
            length = (pages - 1) * colums + lastCol;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(float frameTime) {
        scroll += speed * frameTime;
        MainGame game = MainGame.getInstance();

        float left = scroll + current * unit;

        while (left < Metrics.width + unit) {
            createColumn(left / unit);
            current++;
            left += unit;
        }
    }

    private void createColumn(float leftUnit) {
        float y = 0;
        for (int row = 0; row < rows; row++) {
            char ch = getAt(current, row);
            if (ch == 0) {
                speed = 0;
                return;
            }
            createObject(ch, leftUnit, row);
            y += unit;
        }
    }

    private void createObject(char ch, float leftUnit, float topUnit) {
        MainGame game = MainGame.getInstance();
        if (ch == '-') {
            GroundBlock groundBlock
                    = new GroundBlock(Metrics.size(R.dimen.block_radius) + leftUnit * Metrics.size(R.dimen.block_radius),
                                        Metrics.height-Metrics.size(R.dimen.block_radius),
                                        R.dimen.block_radius, R.mipmap.normal_block);
            game.add(MainGame.Layer.groundBlock.ordinal(), groundBlock);
        }
    }

    private char getAt(int x, int y) {
        try {
            int lineIndex = x / colums * rows + y;
            String line = lines.get(lineIndex);
            return line.charAt(x % colums);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public void draw(Canvas canvas) {
    }

    @Override
    public RectF getDstRect() {
        return null;
    }
}