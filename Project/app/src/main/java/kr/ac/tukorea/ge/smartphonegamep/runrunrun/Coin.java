package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.RectF;

public class Coin extends MapSprite {
    public Coin(float topX, float topY) {
        bitmap = BitmapPool.get(R.mipmap.coin);
        MainGame game = MainGame.getInstance();
        float left = topX;
        float top = topY;
        dstRect.set(left, top, left + Metrics.size(R.dimen.block_radius), top + + Metrics.size(R.dimen.block_radius));
    }

    @Override
    public void update(float frameTime) {
        super.update(frameTime);
    }
    public RectF getDstRect() {return dstRect;}
}