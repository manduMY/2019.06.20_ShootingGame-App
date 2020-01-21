package kr.co.apps.gameactivity_framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.ImageView;

import Game.GameState_1;

public class IntroState implements IState {

    Bitmap icon;
    int x,y;
    ImageView imageView;

    @Override
    public void Init() {
        icon = AppManeger.getInstance().getBitmap(R.drawable.background2);
    }

    @Override
    public void Destroy() {

    }

    @Override
    public void Update() {

    }

    @Override
    public void Render(Canvas canvas) {
        canvas.drawBitmap(icon,150,150,null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AppManeger.getInstance().getGameView().changeGameState(new GameState_1());
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        AppManeger.getInstance().getGameView().changeGameState(new GameState_1());
        return true;
    }
}
