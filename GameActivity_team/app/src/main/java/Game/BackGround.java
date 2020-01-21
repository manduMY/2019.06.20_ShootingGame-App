package Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import kr.co.apps.gameactivity_framework.AppManeger;
import kr.co.apps.gameactivity_framework.GraphicObject;
import kr.co.apps.gameactivity_framework.R;

public class BackGround extends GraphicObject {
    private int width=AppManeger.getInstance().getGameView().getDisplay().getWidth();
    private int height=AppManeger.getInstance().getGameView().getDisplay().getHeight();
    static final float SCROLL_SPEED=2.0f;
    private float m_scroll=-2000+800;

    public BackGround(int backtype) {
        super(null);

        if(backtype==0) {
            m_bitmap = AppManeger.getInstance().getBitmap(R.drawable.back1);
            m_bitmap = Bitmap.createScaledBitmap(m_bitmap,width, m_bitmap.getHeight(), true);
        }
        else if(backtype==1) {
            m_bitmap = AppManeger.getInstance().getBitmap(R.drawable.back2);
            m_bitmap = Bitmap.createScaledBitmap(m_bitmap,width,m_bitmap.getHeight(), true);
        }
        else if(backtype==2)
        {
            m_bitmap = AppManeger.getInstance().getBitmap(R.drawable.back3);
            m_bitmap = Bitmap.createScaledBitmap(m_bitmap,width,m_bitmap.getHeight(), true);
        }

        setPosition(0,(int)m_scroll);

    }

    void Update(long GameTime)
    {
        m_scroll=m_scroll+SCROLL_SPEED;
        if(m_scroll>=0)
            m_scroll=-2000+800;
        setPosition(0,(int)m_scroll);

    }

    @Override
    public void Draw(Canvas canvas) {
        canvas.drawBitmap(m_bitmap,m_x,m_y,null); }
}
