package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;

public class MapSprite extends Sprite implements BoxCollidable{
    protected MapSprite() {

    }
    public MapSprite(float x, float y, int radiusDimenID, int bitmpaResID) {
        //super(x, y, radiusDimenID, bitmpaResID);
    }

    @Override
    public void update(float frameTime) {
        if (MainGame.getInstance().player.isMove()) {
            float speed = MapLoader.get().speed;
            float dx = speed * frameTime;
            dstRect.offset(dx, 0);
            if (dstRect.right < 0)
                BaseGame.getInstance().remove(this);
        }
    }


    @Override
    public RectF getBoudingRect() {
        return dstRect;
    }
}
