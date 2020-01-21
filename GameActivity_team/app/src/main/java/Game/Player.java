package Game;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;

import kr.co.apps.gameactivity_framework.AppManeger;
import kr.co.apps.gameactivity_framework.GameOver;
import kr.co.apps.gameactivity_framework.GameView;
import kr.co.apps.gameactivity_framework.SpriteAnimation;

public class Player extends SpriteAnimation {

    Rect m_BoundBox = new Rect();

    int m_Life;// 임시로 라이프를 3개

    public Player(Bitmap bitmap,int m_Life) {
        super(bitmap);
        bitmap.createScaledBitmap(bitmap,300,500,true);
        initSpriteData(300*3/6*7,500,1,1);
        this.setPosition(500, 1500);
        this.m_Life=m_Life;
    }

    @Override
    public void Update(long gameTIme) {
        super.Update(gameTIme);
        m_BoundBox.set(m_x,m_y,m_x+100*3/6*7,m_y+600);

        if(m_Life<1)
        {
            AppManeger.getInstance().gameViewThread.setRunning(false);
            Intent intent=new Intent(AppManeger.getInstance().getGameView().getContext(), GameOver.class);
            AppManeger.getInstance().getGameView().getContext().startActivity(intent);
        }
    }

    //현재 목숨
    public int getM_Life() {
        return m_Life;
    }

    //여기서 목숨 가
    public void addLife() {
        m_Life++;
    }

    //목숨 감소
    public void destroyPlayer()
    {
        m_Life--;
    }
}
