package Game;

import android.graphics.Bitmap;
import android.view.MotionEvent;

import kr.co.apps.gameactivity_framework.AppManeger;
import kr.co.apps.gameactivity_framework.R;

public class RandomBox_HP extends RandomBox {

    public RandomBox_HP() {
        super(AppManeger.getInstance().getBitmap(R.drawable.randombox));
        this.initSpriteData(160,160,1,1);
        speed=15;
        BoxType=1;
    }

    @Override
    public void Update(long gameTime) {
        super.Update(gameTime);
        m_BoundBox.set(m_x,m_y,m_x+120,m_y+120);
    }
}
