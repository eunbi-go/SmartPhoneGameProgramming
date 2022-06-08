package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }


    @Override
    protected void onDestroy() {
        GameView.view = null;
        MainScene.clear();
        super.onDestroy();
    }

    public void onBtnFirst(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(MainScene.PARAM_STAGE_INDEX, 0);
        startActivity(intent);
    }
}