package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;
import android.widget.ArrayAdapter;

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
        playerBulletToEnemys(game);

        playerToItemBlock(game);
        playerToCoin(game);
    }

    // Player & NormalEnemy, AttackEnemy
    private void playerBulletToEnemys(MainGame game) {
        ArrayList<GameObject> bullets = game.objectsAt(MainGame.Layer.bullets.ordinal());
        for (GameObject bullet : bullets) {
            if (Bullet.OBJ.PLAYER_BULLET != ((Bullet)bullet).getObject())
                continue;
            if (!(bullet instanceof BoxCollidable)) {
                continue;
            }
            ArrayList<GameObject> enemys = game.objectsAt(MainGame.Layer.enemy.ordinal());
            for (GameObject enemy : enemys) {
                if (enemy instanceof Enemy) {
                    if (CollisionHelper.collides((Enemy)enemy, (Bullet) bullet)) {
                        BaseGame.getInstance().remove(bullet);
                        ArrayList<GameObject> playerHearts = game.objectsAt(MainGame.Layer.player_heart.ordinal());
                        checkPlayerHearts(playerHearts);
                        return;
                    }
                }
                else if (enemy instanceof AttackEnemy) {
                    if (CollisionHelper.collides((AttackEnemy)enemy, (Bullet) bullet)) {
                        BaseGame.getInstance().remove(bullet);
                        ArrayList<GameObject> playerHearts = game.objectsAt(MainGame.Layer.player_heart.ordinal());
                        checkPlayerHearts(playerHearts);
                        return;
                    }
                }
            }
        }
    }


    // Player & Coin
    private void playerToCoin(MainGame game) {
        ArrayList<GameObject> coins = game.objectsAt(MainGame.Layer.playerCoin.ordinal());
        for (GameObject coin : coins) {
            if (!(coin instanceof BoxCollidable)) {
                continue;
            }
            if (CollisionHelper.collides(player, (Coin) coin)) {
                game.remove(coin);
                player.getCoin();
                return;
            }
        }
    }

    // Player & ItemBlock
    private void playerToItemBlock(MainGame game) {
        ArrayList<GameObject> itemBlocks = game.objectsAt(MainGame.Layer.itemBlock.ordinal());
        for (GameObject itemBlock : itemBlocks) {
            if (CollisionHelper.collides(player, (ItemBlock)itemBlock)) {
                BaseGame.getInstance().remove(itemBlock);
                int playerBullets = player.getAttackCount();

                player.setAttackCount();
                MainGame.getInstance().attackButton.onAttack(true);

                for (int i = playerBullets; i < 5 + playerBullets; ++i) {
                    PlayerBullet playerBullet = new PlayerBullet(R.mipmap.bullet_ui, R.dimen.player_heart_radius,
                            100 + i * 100, 200, i + playerBullets);
                    game.add(MainGame.Layer.player_bulletUI.ordinal(), playerBullet);
                }
            }
        }

    }

    // Player & AttackEnemy Bullet
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

    // Player & NormalEnemy
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
