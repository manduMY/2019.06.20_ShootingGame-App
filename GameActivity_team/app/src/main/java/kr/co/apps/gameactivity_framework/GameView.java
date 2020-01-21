package kr.co.apps.gameactivity_framework;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import Game.GameState_1;
import Game.GameState_2;
import Game.SelectState;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private GameViewThread m_thread;
    private IState m_state;

    Vibrator vibrator;

    public GameView(Context context) {
        super(context);

        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        setFocusable(true);// 키 입력하기 위해서
        AppManeger.getInstance().setGameView(this);// AppManager에 GameView 클래스를 등록
        AppManeger.getInstance().setResources(getResources());
        getHolder().addCallback(this);// 콜백 함수에 현재 클래스 등록

        changeGameState(new SelectState());// 초기 이미지

        m_thread=new GameViewThread(getHolder(),this);//GameView를 쓰레드에 등록한다
    }
    public void viberator()
    {
        if(AppManeger.getInstance().m_vibe.equals("on"))
            vibrator.vibrate(100);// 0.1초 진동
    }
    @Override
    protected void onDraw(Canvas canvas) {
        m_state.Render(canvas);// 현재 GameState모드 그림을 그린다
    }

    public void Update(){
        //상태는 업데이트하고 surfaceviewholder가 업데이트된 상태를 surface에 그리고
        // 다 그린걸 callback함수로 gameview를 부르고 surface에 그려진 그림을 ondraw로 넘겨준다
        m_state.Update();// 현재 GameState_1 모드를 업데이트 한다
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        m_state.onKeyDown(keyCode,event);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        m_state.onTouchEvent(event);
        return true;
    }

    public void changeGameState(IState _state)
    {
        if(m_state!=null)
            m_state.Destroy();// 다른 상태로 이동을 할때 그전에 있던 상태를 지우고, 다른 상태를 저장한다
        _state.Init();// init 바로 실행
        m_state=_state;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        m_thread.setRunning(true);// 쓰레드를 동작할 준비를 한다
        m_thread.start();// 쓰레드를 실행한다(현재 State를 실행)
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry=true;
        m_thread.setRunning(false);
        while (retry)
        {
            try {
                //쓰레드가 한번에 안멈추기 때문에 멈출때까지 정지 시도를 한다
                m_thread.join();
                retry=false;
                }
            catch (InterruptedException e)
            {

            }
        }
    }
}
