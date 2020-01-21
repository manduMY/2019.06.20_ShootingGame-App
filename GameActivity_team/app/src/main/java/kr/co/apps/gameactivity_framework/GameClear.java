package kr.co.apps.gameactivity_framework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class GameClear extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_clear);
        imageView=findViewById(R.id.over);

        AppManeger.getInstance().gameClear=this;

        //터치하면 메인화면으로 이동
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GameStartActivity.class);
                startActivity(intent);
                finish();
            }
        });

        /*게임 클리어시 배경음을 끔.*/
        SoundManager.getInstance().stopBackground2();

    }
}
