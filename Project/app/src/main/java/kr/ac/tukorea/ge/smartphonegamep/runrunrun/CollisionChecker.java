package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import java.util.ArrayList;

public class CollisionChecker implements GameObject{
    private final Player player;

    public CollisionChecker(Player player) {this.player = player;}

    @Override
    public void update(float frameTime) {
        MainGame game = MainGame.getInstance();
        ArrayList<GameObject> enemys = game.objectsAt(MainGame.Layer.enemy.ordinal());
        for (GameObject enemy : enemys) {
            if (CollisionHelper.collideRectF(player.getDstRect(), enemy.getDstRect())) {
                ArrayList<GameObject> playerHearts = game.objectsAt(MainGame.Layer.player_heart.ordinal());
                checkPlayerHearts(playerHearts);
            }
        }
    }

    private void checkPlayerHearts(ArrayList<GameObject> playerHearts) {
        for (GameObject playerHeart : playerHearts) {
            if (!((PlayerHeart) playerHeart).getIsDead()) {
                ((PlayerHeart) playerHeart).setDead(true);
                break;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public RectF getDstRect() {
        return null;
    }
}
