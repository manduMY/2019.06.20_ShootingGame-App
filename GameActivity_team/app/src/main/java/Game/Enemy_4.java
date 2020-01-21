package Game;

import android.graphics.Bitmap;
import android.util.Log;

import kr.co.apps.gameactivity_framework.AppManeger;
import kr.co.apps.gameactivity_framework.R;

public class Enemy_4 extends Enemy {

    public Enemy_4() {
        super(AppManeger.getInstance().getBitmap(R.drawable.enemy4));
        bitmap=AppManeger.getInstance().getBitmap(R.drawable.enemy4);

       // Log.e(" 너비는",""+bitmap.getWidth());
        this.initSpriteData(775/160*570,124/160*570,5,5);
    }

    @Override
    public void Update(long gameTIme) {
        super.Update(gameTIme);
        m_BoundBox.set(m_x,m_y,m_x+155/2*7,m_y+height);
    }
    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
        m_x=x;
        m_y=y;
    }
}
