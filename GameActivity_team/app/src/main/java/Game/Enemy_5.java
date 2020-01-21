package Game;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.logging.Level;

import kr.co.apps.gameactivity_framework.AppManeger;
import kr.co.apps.gameactivity_framework.R;

public class Enemy_5 extends Enemy {
    public Enemy_5() {
        super(AppManeger.getInstance().getBitmap(R.drawable.enemy5));
        bitmap=AppManeger.getInstance().getBitmap(R.drawable.enemy5);
        height=bitmap.getHeight();
        width=bitmap.getWidth();
        this.initSpriteData(60/160*570,78/160*570,6,6);
    }

    @Override
    public void Update(long gameTIme) {
        super.Update(gameTIme);
        m_BoundBox.set(m_x,m_y,m_x+100/2*7,m_y+height);
    }
    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
        m_x=x;
        m_y=y;
    }
}
