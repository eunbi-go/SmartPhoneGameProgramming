package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import java.util.ArrayList;

public class CollisionChecker implements GameObject{
    private final Player player;
    private static final String TAG = CollisionChecker.class.getSimpleName();

    public CollisionChecker(Player player) {this.player = player;}

    @Override
    public void update(float frameTime) {
        MainGame game = MainGame.getInstance();

        playerToNormalEnemy(game);
        playerToAttackEnemyBullet(game);

        playerToItemBlock(game);
    }

    private void playerToItemBlock(MainGame game) {
        ArrayList<GameObject> itemBlocks = game.objectsAt(MainGame.Layer.itemBlock.ordinal());
        for (GameObject itemBlock : itemBlocks) {
            if (CollisionHelper.collides(player, (ItemBlock)itemBlock)) {
                BaseGame.getInstance().remove(itemBlock);
                player.setAttackCount();
            }
        }

    }

    private void playerToAttackEnemyBullet(MainGame game) {
        ArrayList<GameObject> bullets = game.objectsAt(MainGame.Layer.bullets.ordinal());
        for (GameObject bullet : bullets) {
            if (Bullet.OBJ.ENEMY_BULLET != ((Bullet)bullet).getObject())
                continue;
            if (!(bullet instanceof BoxCollidable)) {
                continue;
            }
            if (CollisionHelper.collides(player, (Bullet) bullet)) {
                BaseGame.getInstance().remove(bullet);
                ArrayList<GameObject> playerHearts = game.objectsAt(MainGame.Layer.player_heart.ordinal());
                checkPlayerHearts(playerHearts);
                return;
            }
        }
    }

    private void playerToNormalEnemy(MainGame game) {
        ArrayList<GameObject> enemys = game.objectsAt(MainGame.Layer.enemy.ordinal());
        for (GameObject enemy : enemys) {
            if (!(enemy instanceof BoxCollidable)) {
                continue;
            }
            if (CollisionHelper.collides(player, (Enemy) enemy)) {
                ((Enemy) enemy).changeDirection();
                ArrayList<GameObject> playerHearts = game.objectsAt(MainGame.Layer.player_heart.ordinal());
                checkPlayerHearts(playerHearts);
                return;
            }
        }
    }

    private void checkPlayerHearts(ArrayList<GameObject> playerHearts) {
        for (GameObject playerHeart : playerHearts) {
            if (!((PlayerHeart) playerHeart).getIsDead()) {
                ((PlayerHeart) playerHeart).setDead(true);
                return;
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
