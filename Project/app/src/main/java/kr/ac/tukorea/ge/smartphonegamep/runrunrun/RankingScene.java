package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

public class RankingScene extends  Scene{
    private static final String TAG = RankingScene.class.getSimpleName();
    private static RankingScene singleton;

    public static RankingScene get() {
        if (singleton == null) {
            singleton = new RankingScene();
        }
        return singleton;
    }

    public enum Layer {
        bg, ui, COUNT;
    }

    public void init() {
        super.init();

        HorzScrollBackground background = new HorzScrollBackground(R.mipmap.background, Metrics.size(R.dimen.bg_scroll_1));
        add(RankingScene.Layer.bg.ordinal(), background);

        initLayers(Layer.COUNT.ordinal());
    }

    @Override
    public void start() {
        Sound.playMusic(R.raw.logo);
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
