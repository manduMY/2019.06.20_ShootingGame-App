package Game;

import kr.co.apps.gameactivity_framework.AppManeger;
import kr.co.apps.gameactivity_framework.R;

public class RandomBox_DeHP extends RandomBox {
    public RandomBox_DeHP() {
        super(AppManeger.getInstance().getBitmap(R.drawable.randombox_de));
        this.initSpriteData(160,160,1,1);
        speed=15;
        BoxType=4;
    }

    @Override
    public void Update(long gameTime) {
        super.Update(gameTime);
        m_BoundBox.set(m_x,m_y,m_x+120,m_y+120);
    }
}
