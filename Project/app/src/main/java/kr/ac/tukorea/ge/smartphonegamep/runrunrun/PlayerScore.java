package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class PlayerScore implements GameObject{

    private Bitmap bitmap;
    private final int imageWidth;
    private final int imageHeight;
    private final float radius;
    private int number;
    private RectF srcRect = new RectF();
    private RectF dstRect = new RectF();

    public PlayerScore(int mipmapResId, int radius, int number, float x, float y) {
        this.bitmap = BitmapPool.get(mipmapResId);
        this.number = number;

        this.imageWidth = bitmap.getWidth();
        this.imageHeight = bitmap.getHeight();
        this.radius = Metrics.size(radius);

        srcRect.set(0, 0, imageWidth, imageHeight);
        dstRect.set(x - this.radius, y - this.radius, x + this.radius, y + this.radius);
    }

    @Override
    public void update(float frameTime) {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    @Override
    public RectF getDstRect() {
        return dstRect;
    }

    public void setScore(int number) {
        this.number = number;
        setScoreRes();
    }

    private void setScoreRes() {
        switch (this.number) {
            case 0:
                this.bitmap = BitmapPool.get(R.mipmap.font_0);
                break;
            case 1:
                this.bitmap = BitmapPool.get(R.mipmap.font_1);
                break;
            case 2:
                this.bitmap = BitmapPool.get(R.mipmap.font_2);
                break;
            case 3:
                this.bitmap = BitmapPool.get(R.mipmap.font_3);
                break;
            case 4:
                this.bitmap = BitmapPool.get(R.mipmap.font_4);
                break;
            case 5:
                this.bitmap = BitmapPool.get(R.mipmap.font_5);
                break;
            case 6:
                this.bitmap = BitmapPool.get(R.mipmap.font_6);
                break;
            case 7:
                this.bitmap = BitmapPool.get(R.mipmap.font_7);
                break;
            case 8:
                this.bitmap = BitmapPool.get(R.mipmap.font_8);
                break;
            case 9:
                this.bitmap = BitmapPool.get(R.mipmap.font_9);
                break;
        }
    }
}
