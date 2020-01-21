package kr.co.apps.gameactivity_framework;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

public interface IState {
     void Init();
    void Destroy();
    void Update();
    void Render(Canvas canvas);
    boolean onKeyDown(int keyCode, KeyEvent event);
    boolean onTouchEvent(MotionEvent event);
}
