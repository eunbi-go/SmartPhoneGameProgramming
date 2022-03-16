package kr.ac.tukorea.ge.smartphonegamep.spgame;

import static kr.ac.tukorea.ge.smartphonegamep.spgame.R.layout.activity_main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 효율을 위해 미리 멤버변수 선언
    private TextView subTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        subTextView = findViewById(R.id.subText);
        subTextView.setText("I am a good programmer");
    }

    public void onBtnPushMe(View view) {
        //tv = findViewById(R.id.subText);s
        subTextView.setText("Clicked !");
    }

}