package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

public class MainGame extends BaseGame {
    private static final String TAG = MainGame.class.getSimpleName();
    public float frameTime;

    public enum Layer {
        player, itemBlock, groundBlock, enemy, bullets, buttons, player_heart, player_score, COUNT
    }

    private Player player;
    private Button attackButton, moveButton, jumpButton, backButton;

    public static MainGame getInstance() {
        if (singleton == null) {
            singleton = new MainGame();
        }
        return (MainGame) singleton;
    }



    public void init() {
        //objects.clear();
        super.init();
        initLayers(Layer.COUNT.ordinal());

        player = new Player(100,845);
        add(Layer.player.ordinal(), player);

        ItemBlock block = new ItemBlock(700, 600, R.dimen.itemBlock_radius, R.mipmap.item_block);
        //ItemBlock block = new ItemBlock(700, 870, R.dimen.itemBlock_radius, R.mipmap.item_block);
        add(Layer.itemBlock.ordinal(), block);

        loadMapBlock();

        //Enemy enemy = new Enemy(100, 850);
        //objects.add(enemy);

        AttackEnemy attackEnemy = new AttackEnemy(100, 850);
        add(Layer.enemy.ordinal(), attackEnemy);

        for (int i = 0; i < 5; ++i) {
            PlayerHeart playerHeart = new PlayerHeart(R.mipmap.player_heart, R.dimen.player_heart_radius,
                    100 + i * 100, 100, i);
            add(Layer.player_heart.ordinal(), playerHeart);
        }

        PlayerScore playerTenScore = new PlayerScore(R.mipmap.font_0, R.dimen.player_heart_radius, 0,Metrics.width-300, 100);
        add(Layer.player_score.ordinal(), playerTenScore);

        PlayerScore playerOneScore = new PlayerScore(R.mipmap.font_0, R.dimen.player_heart_radius, 0,Metrics.width-200, 100);
        add(Layer.player_score.ordinal(), playerOneScore);

        attackButton = new Button(Metrics.width - 200, Metrics.height - 200, R.dimen.button_radius, R.mipmap.before_attack,
                new Button.Callback() {
            public boolean onTouch(Button.Action action) {
                if (action != Button.Action.pressed) return false;
                player.attack();
                return true;
            }
                });
        add(Layer.buttons.ordinal(), attackButton);

        moveButton = new Button(Metrics.width / 6, Metrics.height - 200, R.dimen.button_radius, R.mipmap.go,
                new Button.Callback() {
                    @Override
                    public boolean onTouch(Button.Action action) {
                        if (action != Button.Action.pressed)  {
                            player.setIsMove(false);
                            return false;
                        }
                        player.setIsMove(true);
                        player.setIsLeftMove(false);
                        return true;
                    }
                });
        add(Layer.buttons.ordinal(), moveButton);

        backButton = new Button(200, Metrics.height - 200, R.dimen.button_radius, R.mipmap.back,
                new Button.Callback() {
                    @Override
                    public boolean onTouch(Button.Action action) {
                        if (action != Button.Action.pressed) {
                            player.setIsMove(false);
                            return false;
                        }
                        player.setIsMove(true);
                        player.setIsLeftMove(true);
                        return true;
                    }
                });
        add(Layer.buttons.ordinal(), backButton);

        jumpButton = new Button(Metrics.width / 3, Metrics.height - 200, R.dimen.button_radius, R.mipmap.jump,
                new Button.Callback() {
                    @Override
                    public boolean onTouch(Button.Action action) {
                        if (action != Button.Action.pressed) return false;
                        player.setIsJump(true);
                        return false;
                    }
                });
        add(Layer.buttons.ordinal(), jumpButton);
    }

    private void loadMapBlock() {
        for (int i = 0; i < 22; ++i) {
            GroundBlock groundBlock
                    = new GroundBlock(Metrics.size(R.dimen.block_radius) + i * Metrics.size(R.dimen.block_radius), Metrics.height-Metrics.size(R.dimen.block_radius),
                    R.dimen.block_radius, R.mipmap.normal_block);
            add(Layer.groundBlock.ordinal(), groundBlock);
        }

        for (int i = 0; i < 22; ++i) {
            GroundBlock groundBlock
                    = new GroundBlock(Metrics.size(R.dimen.block_radius) + i * Metrics.size(R.dimen.block_radius), Metrics.height-Metrics.size(R.dimen.block_radius) * 3,
                    R.dimen.block_radius, R.mipmap.normal_block);
            add(Layer.groundBlock.ordinal(), groundBlock);
        }
    }

    public RectF getPlayerRect() {
        return player.getDstRect();
    }







}
