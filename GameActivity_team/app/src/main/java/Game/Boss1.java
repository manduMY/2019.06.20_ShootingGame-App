package Game;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.MotionEvent;

import java.util.logging.Level;

import kr.co.apps.gameactivity_framework.AppManeger;
import kr.co.apps.gameactivity_framework.R;

public class Boss1 extends Boss {
    public Boss1()
    {
        super(AppManeger.getInstance().getBitmap(R.drawable.boss1));
        Bitmap bitmap=AppManeger.getInstance().getBitmap(R.drawable.boss1);
        Log.e(" 보스입니다",""+bitmap.getWidth());
        this.initSpriteData(177/160*570,212/160*570,6,6);
        hp=20;
    }

    @Override
    public void Update(long gameTime) {
        super.Update(gameTime);
        m_BoundBox.set(m_x,m_y,m_x+177*3/6*7,m_y+212/160*570);
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
