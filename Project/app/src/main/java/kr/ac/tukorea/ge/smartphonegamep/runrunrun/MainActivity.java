package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainGame game = MainGame.getInstance();
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onDestroy() {
        GameView.view = null;
        MainGame.clear();
        super.onDestroy();
    }
}