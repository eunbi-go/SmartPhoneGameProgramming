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

        //MainGame game = MainGame.getInstance();
        //getSupportActionBar().hide();

    }


    @Override
    protected void onDestroy() {
        GameView.view = null;
        MainGame.clear();
        super.onDestroy();
    }

    public void onBtnFirst(View view) {
        //MainGame game = MainGame.getInstance();
        Intent intent = new Intent(this, MainGame.class);
        intent.putExtra("stage2", 0);
        startActivity(intent);
    }
}