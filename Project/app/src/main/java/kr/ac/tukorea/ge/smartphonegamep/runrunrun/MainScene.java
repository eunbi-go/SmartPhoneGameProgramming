package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.RectF;

public class MainScene extends Scene {
    public static final String PARAM_STAGE_INDEX = "stage_index";
    private static final String TAG = MainScene.class.getSimpleName();
    public float frameTime;
    protected int mapIndex;

    public float size(float unit) {
        return Metrics.height / 10.f * unit;
    }

    public enum Layer {
        bg, player, itemBlock, groundBlock, enemy, bullets, buttons, player_heart, player_score, player_bulletUI, playerCoin, controller, shield_block, final_block, COUNT
    }

    public Player player;
    public HorzScrollBackground background;
    public Button attackButton, moveButton, jumpButton, backButton;

    public static MainScene getInstance() {
        if (singleton == null) {
            singleton = new MainScene();
        }
        return (MainScene) singleton;
    }

    public void setMapIndex(int mapIndex) {
        this.mapIndex = mapIndex;
    }

    public void init() {
        //objects.clear();
        super.init();
        initLayers(MainScene.Layer.COUNT.ordinal());

        player = new Player(size(3),size(7));
        add(MainScene.Layer.player.ordinal(), player);

        background = new HorzScrollBackground(R.mipmap.background, Metrics.size(R.dimen.bg_scroll_1));
        add(MainScene.Layer.bg.ordinal(), background);

        add(MainScene.Layer.controller.ordinal(), new CollisionChecker(player));

        MapLoader mapLoader = MapLoader.get();
        mapLoader.init(1);   // 1
        add(MainScene.Layer.controller.ordinal(), mapLoader);

        crateEnemys();

        for (int i = 0; i < 5; ++i) {
            PlayerHeart playerHeart = new PlayerHeart(R.mipmap.player_heart, R.dimen.player_heart_radius,
                    100 + i * 100, 100, i);
            add(MainScene.Layer.player_heart.ordinal(), playerHeart);
        }

        PlayerScore playerTenScore = new PlayerScore(R.mipmap.font_0, R.dimen.player_heart_radius, 0,Metrics.width-300, 100);
        add(MainScene.Layer.player_score.ordinal(), playerTenScore);

        PlayerScore playerOneScore = new PlayerScore(R.mipmap.font_0, R.dimen.player_heart_radius, 0,Metrics.width-200, 100);
        add(MainScene.Layer.player_score.ordinal(), playerOneScore);

        attackButton = new Button(Metrics.width - 200, Metrics.height - 200, R.dimen.button_radius, R.mipmap.before_attack,
                new Button.Callback() {
                    public boolean onTouch(Button.Action action) {
                        if (action != Button.Action.pressed) {
                            return false;
                        }
                        player.attack();
                        return true;
                    }
                });
        add(MainScene.Layer.buttons.ordinal(), attackButton);

        moveButton = new Button(Metrics.width / 6, Metrics.height - 200, R.dimen.button_radius, R.mipmap.go,
                new Button.Callback() {
                    @Override
                    public boolean onTouch(Button.Action action) {
                        if (action != Button.Action.pressed)  {
                            player.setIsMove(false);
                            background.setIsMove(false);
                            return false;
                        }
                        background.setIsMove(true);
                        background.setIsLeft(true);
                        player.setIsMove(true);
                        player.setIsLeftMove(false);
                        return true;
                    }
                });
        add(MainScene.Layer.buttons.ordinal(), moveButton);

        backButton = new Button(200, Metrics.height - 200, R.dimen.button_radius, R.mipmap.back,
                new Button.Callback() {
                    @Override
                    public boolean onTouch(Button.Action action) {
                        if (action != Button.Action.pressed) {
                            player.setIsMove(false);
                            background.setIsMove(false);
                            return false;
                        }
                        background.setIsMove(true);
                        background.setIsLeft(false);
                        player.setIsMove(true);
                        player.setIsLeftMove(true);
                        return true;
                    }
                });
        add(MainScene.Layer.buttons.ordinal(), backButton);

        jumpButton = new Button(Metrics.width / 3, Metrics.height - 200, R.dimen.button_radius, R.mipmap.jump,
                new Button.Callback() {
                    @Override
                    public boolean onTouch(Button.Action action) {
                        if (action != Button.Action.pressed) return false;
                        player.setIsJump(true);
                        background.setIsMove(false);
                        return false;
                    }
                });
        add(MainScene.Layer.buttons.ordinal(), jumpButton);
    }

    protected int getTouchLayerIndex() {
        return 6;
    }

    private void crateEnemys() {
        Enemy enemy = new Enemy(size(40), size(7));
        add(MainScene.Layer.enemy.ordinal(), enemy);

        AttackEnemy attackEnemy = new AttackEnemy(size(70), size(7));
        add(MainScene.Layer.enemy.ordinal(), attackEnemy);

            Flower flower = new Flower(size(50), size(7));
            add(MainScene.Layer.enemy.ordinal(), flower);

        // 170, 4
        FinalBlock finalBlock
                = new FinalBlock(size(65), size(4));
        add(MainScene.Layer.final_block.ordinal(), finalBlock);
    }

    public RectF getPlayerRect() {
        return player.getDstRect();
    }

    @Override
    public void start() {
        Sound.playMusic(R.raw.logo);
    }

    @Override
    public void end() {
        Sound.stopMusic();
    }
}
