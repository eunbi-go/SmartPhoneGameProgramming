package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

public class RankingScene extends  Scene{
    private static final String TAG = RankingScene.class.getSimpleName();
    private static RankingScene singleton;
    private int score;
    boolean isClear;

    public static RankingScene get() {
        if (singleton == null) {
            singleton = new RankingScene();
        }
        return singleton;
    }

    public enum Layer {
        bg, ui, player_score, buttons, COUNT;
    }
    protected int getTouchLayerIndex() {
        return 3;
    }
    public void init(int score, boolean isClear) {
        super.init();
        initLayers(RankingScene.Layer.COUNT.ordinal());

        this.isClear = isClear;

        HorzScrollBackground background = new HorzScrollBackground(R.mipmap.background, Metrics.size(R.dimen.bg_scroll_1));
        add(RankingScene.Layer.bg.ordinal(), background);

        PlayerScore playerTenScore = new PlayerScore(R.mipmap.font_0, R.dimen.rankingScene_score,
                score/10,
                Metrics.width/2, Metrics.height/2);
        add(RankingScene.Layer.player_score.ordinal(), playerTenScore);

        PlayerScore playerOneScore = new PlayerScore(R.mipmap.font_0, R.dimen.rankingScene_score,
                score%10,
                Metrics.width/2+150.f, Metrics.height/2);
        add(RankingScene.Layer.player_score.ordinal(), playerOneScore);

        Button attackButton = new Button(Metrics.width - 200, Metrics.height - 200, R.dimen.button_radius, R.mipmap.retry,
                new Button.Callback() {
                    public boolean onTouch(Button.Action action) {
                        if (action != Button.Action.pressed) {
                            return false;
                        }
                        //player.attack();
                        RankingScene.getInstance().refreshLayers();
                        GameView.view.stageScene();
                        //return;
                        return true;
                    }
                });
        add(RankingScene.Layer.buttons.ordinal(), attackButton);
    }

    public void setScore(int score) {this.score = score;}

    @Override
    public void start() {
        Sound.playEffect(R.raw.clear);
    }

    @Override
    public void pause() {
        Sound.pauseMusic();
    }

    @Override
    public void resume() {
        Sound.resumeMusic();
    }

    @Override
    public void end() {
        Sound.stopMusic();
    }
}
