package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class GroundBlock extends MapSprite{
    public GroundBlock(float topX, float topY) {
        bitmap = BitmapPool.get(R.mipmap.normal_block);
        MainGame game = MainGame.getInstance();
        float left = topX;
        float top = topY;
        dstRect.set(left, top, left + Metrics.size(R.dimen.block_radius), top + + Metrics.size(R.dimen.block_radius));
    }

    @Override
    public void update(float frameTime) {
        super.update(frameTime);

    }
}
