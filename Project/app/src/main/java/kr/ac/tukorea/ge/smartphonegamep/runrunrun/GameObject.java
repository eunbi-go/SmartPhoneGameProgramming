package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;

public interface GameObject {
    public void update(float frameTime);
    public void draw(Canvas canvas);
}
