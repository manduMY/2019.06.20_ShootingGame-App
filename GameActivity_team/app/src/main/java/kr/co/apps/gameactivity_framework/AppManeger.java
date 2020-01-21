package kr.co.apps.gameactivity_framework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import Game.GameState_1;
import Game.GameState_2;
import Game.GameState_3;
import Game.SelectState;

public class AppManeger {
    private GameView m_gameView;
    private Resources m_resources;

    // subMissile
    public int subMissilecnt=0;

    //GameState_1
    public GameState_1 m_gamestate_1;
    public GameState_2 m_gamestate_2;
    public GameState_3 m_gamestate_3;

    // 환경설정
    public String m_backgroundsound ="on";// 배경음악 gameview 케릭터 선택~인게임
    public String m_effectsound = "on";// 이펙트 사운드
    public String m_vibe="on";// 진동 효과

    // 게임 오버
    public GameOver gameOver;
    public GameClear gameClear;

    //selectState
    public SelectState m_selectstate;
    public int pNum = 0;// 플레이어 이미지 선택

    // 환경설정
    public SettingActivity m_settingActivity;

    // 게임 뷰 쓰레드
    public GameViewThread gameViewThread;



    void setGameView(GameView _gameView)
    {
        m_gameView=_gameView;
    }
    void setResources(Resources _resources)
    {
        m_resources=_resources;
    }
    public GameView getGameView() {
        return m_gameView;
    }

    public Resources getResources() {
        return m_resources;
    }
    public Bitmap getBitmap(int r)
    {
        return BitmapFactory.decodeResource(m_resources,r);
    }

    private static AppManeger s_instance;
    public static AppManeger getInstance()
    {
        if(s_instance==null)
            s_instance=new AppManeger();
        return s_instance;
    }
}
