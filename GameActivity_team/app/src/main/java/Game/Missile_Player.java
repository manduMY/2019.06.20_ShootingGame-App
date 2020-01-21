package Game;

import android.graphics.Bitmap;
import android.util.Log;

import kr.co.apps.gameactivity_framework.AppManeger;
import kr.co.apps.gameactivity_framework.GraphicObject;
import kr.co.apps.gameactivity_framework.R;

public class Missile_Player extends Missile {

    public Missile_Player(int x, int y,int speed) {
        super(AppManeger.getInstance().getBitmap(R.drawable.missile_3_1));// 이미지를 가져옴
        this.initSpriteData(37/2*7,200,1,1);
        this.setPosition(x,y); // gamestate에서 이미지의 위치를 지정
        this.speed=speed;
    }



    public void Update() {

        m_y-=speed;// 미사일을 위로 올라가게 함

        //위로 올라가면서 50보다 작으면 아웃 상태로 변화
        if(m_y<50)
            state=STATE_OUT;

        // 충돌 떄 쓸, rect
        m_BoundBox.set(m_x,m_y,m_x+37*3/6*7,m_y+25);
    }


}
