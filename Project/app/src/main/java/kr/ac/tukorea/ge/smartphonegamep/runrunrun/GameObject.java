package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;

public interface GameObject {
    public void update(float frameTime);
    public void draw(Canvas canvas);

    public RectF getDstRect();
}
