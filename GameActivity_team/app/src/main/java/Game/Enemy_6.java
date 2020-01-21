package Game;

import kr.co.apps.gameactivity_framework.AppManeger;
import kr.co.apps.gameactivity_framework.R;

public class Enemy_6 extends Enemy {
    public Enemy_6() {
        super(AppManeger.getInstance().getBitmap(R.drawable.enemy6));
        bitmap=AppManeger.getInstance().getBitmap(R.drawable.enemy6);
        height=bitmap.getHeight();
        width=bitmap.getWidth();
        this.initSpriteData(71/160*570,77/160*570,5,5);

    }

    @Override
    public void Update(long gameTIme) {
        super.Update(gameTIme);
        m_BoundBox.set(m_x,m_y,m_x+70/2*7,m_y+height);
    }
    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
        m_x=x;
        m_y=y;
    }
}
