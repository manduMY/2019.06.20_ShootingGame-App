package Game;

import kr.co.apps.gameactivity_framework.AppManeger;
import kr.co.apps.gameactivity_framework.R;

public class Boss2 extends Boss {
    public Boss2()
    {
        super(AppManeger.getInstance().getBitmap(R.drawable.boss2));
        this.initSpriteData(177/160*570,212/160*570,6,6);
        hp=30;
    }

    @Override
    public void Update(long gameTime) {
        super.Update(gameTime);
        m_BoundBox.set(m_x,m_y,m_x+177*3/6*7,m_y+700);
    }


    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
        m_x=x;
        m_y=y;
    }

    @Override
    public void setX(int m_x) {
        super.setX(m_x);
    }

    @Override
    public void setY(int m_y) {
        super.setY(m_y);
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }
}
