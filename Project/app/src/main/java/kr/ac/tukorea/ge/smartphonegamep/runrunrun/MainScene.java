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
        bg, player, itemBlock, groundBlock, enemy, bullets, buttons, player_heart, player_score, player_bulletUI, playerCoin, controller, COUNT
    }

    public Player player;
    private HorzScrollBackground background;
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
        initLayers(Layer.COUNT.ordinal());

        player = new Player(size(5),size(7));
        add(Layer.player.ordinal(), player);

        background = new HorzScrollBackground(R.mipmap.background, Metrics.size(R.dimen.bg_scroll_1));
        add(Layer.bg.ordinal(), background);
        add(Layer.controller.ordinal(), new CollisionChecker(player));

        MapLoader mapLoader = MapLoader.get();
        mapLoader.init(mapIndex);   // 1
        add(Layer.controller.ordinal(), mapLoader);

        //Enemy enemy = new Enemy(size(10), size(7));
        //add(Layer.enemy.ordinal(), enemy);

        //AttackEnemy attackEnemy = new AttackEnemy(size(10), size(7));
        //add(Layer.enemy.ordinal(), attackEnemy);

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
                        if (action != Button.Action.pressed) {
                            return false;
                        }
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
        add(Layer.buttons.ordinal(), moveButton);

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
        add(Layer.buttons.ordinal(), backButton);

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
        add(Layer.buttons.ordinal(), jumpButton);
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
