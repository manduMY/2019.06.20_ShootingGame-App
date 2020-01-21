package Game;

import android.graphics.Bitmap;

import kr.co.apps.gameactivity_framework.AppManeger;
import kr.co.apps.gameactivity_framework.R;

public class Missile_Enemy extends Missile {

    int width=AppManeger.getInstance().getGameView().getDisplay().getHeight();

    Missile_Enemy(int x, int y)
    {
        super(AppManeger.getInstance().getBitmap(R.drawable.missile_5));
        this.initSpriteData(58*540/160,60*540/160,5,11);
        this.setPosition(x,y);
    }

    //방향 플레그?? 같은 느낌
    public static final int go=0;
    public static final int back=1;
    public int goback=go;


    @Override
    public void Update(long gameTime) {
        super.Update(gameTime);
        // 스테이지 1에서 미사일
        if (AppManeger.getInstance().m_gamestate_1 != null) {
            m_y += 15;
        }
        // 스테이지 2에서 미사일
        else if (AppManeger.getInstance().m_gamestate_2 != null) {
            m_y += 15;
            if (goback == go) {
                m_x += 15;
                if (m_x > 1200)
                    goback = back;
            } else {
                m_x -= 15;
                if (m_x < 0)
                    goback = go;
            }
        }
        //스테이지 3에서 미사일
        else if (AppManeger.getInstance().m_gamestate_3 != null) {
            m_y += 15;
            if (goback == go) {
                m_x += 15;
                if (m_x > 1200)
                    goback = back;
            } else {
                m_x -= 15;
                if (m_x < 0)
                    goback = go;
            }
        }
        // 아래로 내려가다가 화면 높이를 넘으면 제거 상태
        if(m_y>=width-50)
            state=STATE_OUT;

        m_BoundBox.set(m_x,m_y,m_x+43,m_y+43);
    }

}
