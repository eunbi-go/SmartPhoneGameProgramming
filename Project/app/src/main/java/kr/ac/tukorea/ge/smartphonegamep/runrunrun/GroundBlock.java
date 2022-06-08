package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

public class GroundBlock extends MapSprite{
    public GroundBlock(float topX, float topY) {
        bitmap = BitmapPool.get(R.mipmap.normal_block);
        MainScene game = MainScene.getInstance();
        float left = topX;
        float top = topY;
        dstRect.set(left, top, left + Metrics.size(R.dimen.block_radius), top + + Metrics.size(R.dimen.block_radius));
    }

    @Override
    public void update(float frameTime) {
        super.update(frameTime);

    }
}
