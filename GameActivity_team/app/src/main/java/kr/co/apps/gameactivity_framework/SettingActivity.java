package kr.co.apps.gameactivity_framework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    String backgroundsound;
    String effectsound;
    String vibeeffect;

    TextView t_background;
    TextView t_effect;
    TextView t_vibe;

    Button back, complete;

    Switch effect, background, vibe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        effect=findViewById(R.id.effect);
        background=findViewById(R.id.background);
        vibe=findViewById(R.id.vibe);

        back=findViewById(R.id.backbtn);
        complete=findViewById(R.id.complete);

        // 세팅->메인->세팅 하면 초기값이 되므로, 스위치 on,off를 기억하기 위한 if문
        if(AppManeger.getInstance().m_effectsound.equals("on"))
        {
            effect.setChecked(true);
        }
        else
            effect.setChecked(false);
        // 인게임 이펙트 on, off설정
        effect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppManeger.getInstance().m_effectsound = "on";
                } else {
                    AppManeger.getInstance().m_effectsound = "off";
                }
            }
        });

        // 세팅->메인->세팅 하면 초기값이 되므로, 스위치 on,off를 기억하기 위한 if문
        if(AppManeger.getInstance().m_backgroundsound.equals("on")) {
            background.setChecked(true);
        }
        else
        {
            background.setChecked(false);
        }
        // 배경음악 on,off 설정
        background.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //배경음악 1(맨 처음 배경음악)은 이미 실행중인 상태라서 여기서 실시간으로 on/off를 해준다

                if(isChecked) {
                    AppManeger.getInstance().m_backgroundsound = "on";
                    SoundManager.getInstance().playBackground1();//
                }
                else {
                    AppManeger.getInstance().m_backgroundsound = "off";
                    SoundManager.getInstance().stopBackground1();
                }
            }
        });

        // 세팅->메인->세팅 하면 초기값이 되므로, 스위치 on,off를 기억하기 위한 if문
        if(AppManeger.getInstance().m_vibe.equals("on")) {
            vibe.setChecked(true);
        }
        else
        {
            vibe.setChecked(false);
        }
        //인게임 진동 on, off설정
        vibe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    AppManeger.getInstance().m_vibe = "on";
                    vibe.setChecked(true);
                }
                else {
                    AppManeger.getInstance().m_vibe = "off";
                    vibe.setChecked(false);
                }
            }
        });

        //뒤로
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppManeger.getInstance().m_backgroundsound.equals("on"))
                SoundManager.getInstance().playBackground1();//
                else
                    SoundManager.getInstance().stopBackground1();
                finish();
            }
        });

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }



}