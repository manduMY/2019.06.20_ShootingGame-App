package kr.co.apps.gameactivity_framework;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.util.concurrent.CancellationException;

public class GameViewThread extends Thread {
    // 접근을 위한 멤버 변수
    private SurfaceHolder m_surfaceHolder;
    private GameView m_gameView;


    // 스레드 실행 상태 멤버 변수
    private boolean m_run = false;

    public GameViewThread(SurfaceHolder surfaceHolder, GameView gameView) {
        m_surfaceHolder = surfaceHolder;
        m_gameView = gameView;
        AppManeger.getInstance().gameViewThread=this;
    }

    public void setRunning( boolean run ) {
        m_run = run;
    }
    @Override
    public void run() {
        Canvas _canvas;
        while (m_run)
        {
            _canvas=null;
            try{
                m_gameView.Update();
                _canvas = m_surfaceHolder.lockCanvas(null);
                synchronized (m_surfaceHolder)
                {
                    m_gameView.onDraw(_canvas);
                }
            }finally {
                if(_canvas!=null)
                    m_surfaceHolder.unlockCanvasAndPost(_canvas);// back에서 그린걸 화면에 그려준다
            }
        }
    }
}
