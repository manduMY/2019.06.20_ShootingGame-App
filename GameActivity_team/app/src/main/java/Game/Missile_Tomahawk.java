package Game;

import kr.co.apps.gameactivity_framework.AppManeger;
import kr.co.apps.gameactivity_framework.R;

public class Missile_Tomahawk extends Missile {

    public Missile_Tomahawk(int x, int y, int speed) {
        super(AppManeger.getInstance().getBitmap(R.drawable.missile_1));// 이미지를 가져옴
        this.initSpriteData(43/2*7,43/2*7,1,1);
        this.setPosition(x,y); // gamestate에서 이미지의 위치를 지정
        this.speed=speed;
    }

    public void Update() {
        m_y-=speed;// 미사일을 위로 올라가게 함

        //위로 올라가면서 50보다 작으면 아웃 상태로 변화
        if(m_y<50)
            state=STATE_OUT;

        // 충돌 떄 쓸, rect
        m_BoundBox.set(m_x,m_y,m_x+43/2*7,m_y+43/2*7);
    }


}