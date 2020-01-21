package Game;

import android.graphics.Bitmap;

import kr.co.apps.gameactivity_framework.AppManeger;
import kr.co.apps.gameactivity_framework.GraphicObject;
import kr.co.apps.gameactivity_framework.R;

public class Tomahawk extends GraphicObject {

    public static final int STATE_NORMAL = 0;
    public static final int STATE_OUT = 1;
    public int state = STATE_NORMAL;

    int speed=4;

    public Tomahawk() {
        super(Bitmap.createScaledBitmap(AppManeger.getInstance().getBitmap(R.drawable.player1),300,300,true));
        this.setPosition(600,1300);
    }

    public void Update(int x,int y){

        if(x != m_x){
            if(x >m_x){
                m_x += speed;
            }else{
                m_x -= speed;
            }
        }

        if(m_y < 0)
            state = STATE_OUT;
    }
}