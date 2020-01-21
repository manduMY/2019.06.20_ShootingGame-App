package Game;

import kr.co.apps.gameactivity_framework.AppManeger;
import kr.co.apps.gameactivity_framework.R;

public class Enemy_3 extends Enemy {
    public Enemy_3() {
        super(AppManeger.getInstance().getBitmap(R.drawable.enemy3));
        this.initSpriteData(62/2*7,104/2*7,3,6);
    }
    @Override
    public void Update(long gameTIme) {
        super.Update(gameTIme);
        m_BoundBox.set(m_x,m_y,m_x+62*3/6*7,m_y+104/2*7);
    }
    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
        m_x=x;
        m_y=y;
    }
}
