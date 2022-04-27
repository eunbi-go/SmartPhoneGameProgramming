package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;

public class Enemy extends Sprite implements BoxCollidable{
    protected RectF boundingRect = new RectF();

    public Enemy(float x, float y) {
        super(x, y, R.dimen.player_radius, R.mipmap.enemy1);
    }

    public Enemy(float x, float y, int radiusDimenID, int bitmpaResID) {
        super(x, y, radiusDimenID, bitmpaResID);
    }

    public void update() {
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    @Override
    public RectF getBoudingRect() {
        return boundingRect;
    }
}
