package kr.co.apps.gameactivity_framework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));

        if(AppManeger.getInstance().m_backgroundsound.equals("on")) {
            SoundManager.getInstance().playBackground2();
            SoundManager.getInstance().playBackground2();
        }
    }
}
