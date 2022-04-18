package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.RectF;

public class CollisionHelper {
    public static boolean collides(BoxCollidable o1, BoxCollidable o2) {
        RectF r1 = o1.getBoudingRect();
        RectF r2 = o2.getBoudingRect();

        if (r1.left > r2.right) return false;
        if (r1.top > r2.bottom) return false;
        if (r1.right < r2.left) return false;
        if (r1.bottom < r2.top) return false;

        return true;
    }

    public static boolean collideRectF(RectF o1, RectF o2) {
        if (o1.left > o2.right) return false;
        if (o1.top > o2.bottom) return false;
        if (o1.right < o2.left) return false;
        if (o1.bottom < o2.top) return false;

        return true;
    }

    public static boolean collides(BoxCollidable o1, RectF o2) {
        RectF r1 = o1.getBoudingRect();

        if (r1.left > o2.right) return false;
        if (r1.top > o2.bottom) return false;
        if (r1.right < o2.left) return false;
        if (r1.bottom < o2.top) return false;

        return true;
    }
}
