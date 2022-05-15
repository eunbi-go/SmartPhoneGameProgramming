package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;

import java.util.ArrayList;

public class CollisionChecker implements GameObject{
    private final Player player;

    public CollisionChecker(Player player) {this.player = player;}

    @Override
    public void update(float frameTime) {
        MainGame game = MainGame.getInstance();
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
