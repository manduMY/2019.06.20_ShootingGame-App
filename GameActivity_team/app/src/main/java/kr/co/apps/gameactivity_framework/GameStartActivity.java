package kr.co.apps.gameactivity_framework;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class GameStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);

        /*배경 음악, 효과음 셋팅*/
        SoundManager.getInstance().Init(this);
        SoundManager.getInstance().addSound(1,R.raw.effect1);// 효과음
        SoundManager.getInstance().addSound(2,R.raw.effect1);//

        if(AppManeger.getInstance().m_backgroundsound.equals("on")) {
            SoundManager.getInstance().playBackground1();
        }
        else
            SoundManager.getInstance().stopBackground1();



        Button b = (Button) findViewById(R.id.button1); // 게임 시작
        Button b2 = (Button) findViewById(R.id.button2); // 환경설정 (진동,)
        Button b3 = (Button) findViewById(R.id.button3);//게임 끝내기 버튼

        b.setOnClickListener(new View.OnClickListener() {
            // 게임 시작
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            // 환경설정
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SettingActivity.class);
                startActivity(intent);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            //종료 버튼
            @Override
            public void onClick(View view) {
                System.exit(1);
            }
        });
    }
}