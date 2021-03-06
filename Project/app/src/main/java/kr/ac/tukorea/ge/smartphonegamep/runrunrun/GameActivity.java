package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(new GameView(this, null));

        MainScene game = MainScene.getInstance();
        game.setMapIndex(1);
        Scene.push(game);

    }

    public void end() {

    }
    @Override
    protected void onDestroy() {
            GameView.view = null;
            Scene.clear();
            super.onDestroy();
    }
}
