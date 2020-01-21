package Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import kr.co.apps.gameactivity_framework.AppManeger;
import kr.co.apps.gameactivity_framework.R;
import kr.co.apps.gameactivity_framework.SpriteAnimation;

public class RandomBox extends SpriteAnimation {

    Rect m_BoundBox=new Rect();
    int speed;

    // 박스의 상태를 나타내는 변수
    public static final int STATE_NORMAL=0;// 기본 상태
    public static final int STATE_OUT=1;
    public int state=STATE_NORMAL;

    // 박스의 종류를 나타내는 변수
    public static final int BoxType1=0;
    public static final int BoxType2=1;
    public static final int BoxType3=2;
    public int BoxType;

    public RandomBox(Bitmap bitmap) {
        super(bitmap);
    }

    public void Update()
    {
        if(m_y>1800)
            state=STATE_OUT;
    }

    public void Move()
    {
        m_y+=speed;
    }


    @Override
    public void Draw(Canvas canvas) {
        super.Draw(canvas);
    }

    @Override
    public void Update(long gameTIme) {
        super.Update(gameTIme);
        Move();
    }
}
