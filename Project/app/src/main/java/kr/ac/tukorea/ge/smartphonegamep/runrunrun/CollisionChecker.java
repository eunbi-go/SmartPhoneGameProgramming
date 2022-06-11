package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.ArrayList;

public class CollisionChecker implements GameObject{
    private final Player player;
    private static final String TAG = CollisionChecker.class.getSimpleName();

    public CollisionChecker(Player player) {this.player = player;}

    @Override
    public void update(float frameTime) {
        MainScene game = MainScene.getInstance();

        //playerToNormalEnemy(game);
        //playerToAttackEnemyBullet(game);
        //playerBulletToEnemys(game);

        playerToItemBlock(game);
        playerToCoin(game);
    }

    // Player & NormalEnemy, AttackEnemy
    private void playerBulletToEnemys(MainScene game) {
        ArrayList<GameObject> bullets = game.objectsAt(MainScene.Layer.bullets.ordinal());
        for (GameObject bullet : bullets) {
            if (Bullet.OBJ.PLAYER_BULLET != ((Bullet)bullet).getObject())
                continue;
            if (!(bullet instanceof BoxCollidable)) {
                continue;
            }
            ArrayList<GameObject> enemys = game.objectsAt(MainScene.Layer.enemy.ordinal());
            for (GameObject enemy : enemys) {
                if (enemy instanceof Enemy) {
                    if (CollisionHelper.collides((Enemy)enemy, (Bullet) bullet)) {
                        Scene.getInstance().remove(bullet);
                        Scene.getInstance().remove(enemy);
                        player.getCoin(3);
                        return;
                    }
                }
                else if (enemy instanceof AttackEnemy) {
                    if (CollisionHelper.collides((AttackEnemy)enemy, (Bullet) bullet)) {
                        Scene.getInstance().remove(bullet);
                        Scene.getInstance().remove(enemy);
                        player.getCoin(5);
                        return;
                    }
                }
            }
        }
    }


    // Player & Coin
    private void playerToCoin(MainScene game) {
        ArrayList<GameObject> coins = game.objectsAt(MainScene.Layer.playerCoin.ordinal());
        for (GameObject coin : coins) {
            if (!(coin instanceof BoxCollidable)) {
                continue;
            }
            if (CollisionHelper.collides(player, (Coin) coin)) {
                game.remove(coin);
                Sound.playEffect(R.raw.coin);
                player.getCoin(1);
                return;
            }
        }
    }

    // Player & ItemBlock
    private void playerToItemBlock(MainScene game) {
        ArrayList<GameObject> itemBlocks = game.objectsAt(MainScene.Layer.itemBlock.ordinal());
        for (GameObject itemBlock : itemBlocks) {
            if (CollisionHelper.collides(player, (ItemBlock)itemBlock)) {
                Scene.getInstance().remove(itemBlock);
                int playerBullets = player.getAttackCount();

                player.setAttackCount();
                MainScene.getInstance().attackButton.onAttack(true);

                for (int i = playerBullets; i < 5 + playerBullets; ++i) {
                    PlayerBullet playerBullet = new PlayerBullet(R.mipmap.bullet_ui, R.dimen.player_heart_radius,
                            100 + i * 100, 200, i + playerBullets);
                    game.add(MainScene.Layer.player_bulletUI.ordinal(), playerBullet);
                }
            }
        }

    }

    // Player & AttackEnemy Bullet
    private void playerToAttackEnemyBullet(MainScene game) {
        ArrayList<GameObject> bullets = game.objectsAt(MainScene.Layer.bullets.ordinal());
        for (GameObject bullet : bullets) {
            if (Bullet.OBJ.ENEMY_BULLET != ((Bullet)bullet).getObject())
                continue;
            if (!(bullet instanceof BoxCollidable)) {
                continue;
            }
            if (CollisionHelper.collides(player, (Bullet) bullet)) {
                Scene.getInstance().remove(bullet);

                // 플레이어가 공격상태면 일반상태로 바뀜
                if (player.getAttackCount() > 0) {
                    player.hitByEnemy();
                }

                // 일반상태면 하트 제거
                ArrayList<GameObject> playerHearts = game.objectsAt(MainScene.Layer.player_heart.ordinal());
                checkPlayerHearts(playerHearts);
                return;
            }
        }
    }

    // Player & NormalEnemy
    private void playerToNormalEnemy(MainScene game) {
        ArrayList<GameObject> enemys = game.objectsAt(MainScene.Layer.enemy.ordinal());
        for (GameObject enemy : enemys) {
            if (!(enemy instanceof BoxCollidable)) {
                continue;
            }
            if (CollisionHelper.collides(player, (Enemy) enemy)) {
                ((Enemy) enemy).changeDirection();

                // 플레이어가 공격상태면 일반상태로 바뀜
                if (player.getAttackCount() > 0) {
                    player.hitByEnemy();
                }

                // 일반상태면 하트 제거
                ArrayList<GameObject> playerHearts = game.objectsAt(MainScene.Layer.player_heart.ordinal());
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
