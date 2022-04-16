package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;

public class Player extends Sprite{
    private static final String TAG = Player.class.getSimpleName();

    public Player(float x, float y) {
        super(x, y, 100, R.mipmap.player);
    }

    public void update() {

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }
}
