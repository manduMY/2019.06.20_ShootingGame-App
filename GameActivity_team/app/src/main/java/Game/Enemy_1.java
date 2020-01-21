package Game;

import android.graphics.Bitmap;
import android.util.Log;

import kr.co.apps.gameactivity_framework.AppManeger;
import kr.co.apps.gameactivity_framework.R;

public class Enemy_1 extends Enemy {

    public Enemy_1() {
        super(AppManeger.getInstance().getBitmap(R.drawable.enemy1));
        this.initSpriteData(62*3/6*7,104/2*7,5,5);
    }

    @Override
    public void Update(long gameTime) {
        super.Update(gameTime);
        m_BoundBox.set(m_x,m_y,m_x+62*3/6*7,m_y+104/2*7);
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
