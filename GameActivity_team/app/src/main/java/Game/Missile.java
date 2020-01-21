package Game;

import android.graphics.Bitmap;
import android.graphics.Rect;

import kr.co.apps.gameactivity_framework.GraphicObject;
import kr.co.apps.gameactivity_framework.SpriteAnimation;

public class Missile extends SpriteAnimation {
    // 미사일의 상태를 나타내는 변수들
    public static final int STATE_NORMAL = 0;
    public static final int STATE_OUT = 1;
    public int state = STATE_NORMAL;

    int speed;

    //충돌 처리를 위한 Rect
    Rect m_BoundBox=new Rect();

    public Missile(Bitmap bitmap) {
        super(bitmap);
    }

    @Override
    public void Update(long gameTime) {
        super.Update(gameTime);
        if(m_y>1800)
            state=STATE_OUT;
    }

}
