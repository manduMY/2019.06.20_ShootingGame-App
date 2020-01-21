package kr.co.apps.gameactivity_framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class SpriteAnimation extends GraphicObject {
    // 여기는 애니메이션 관련 설정을 전부 한다(플레이어, 배경화면)

    private Rect m_rect;
    private int m_fps; // 초당 프레임
    private int m_iFrames; // 프레임의 개수
    private long m_frameTImer;

    private int m_currentFrame;// 최근 프레임 = 전에 프레임
    private int m_spriteWidth;
    private int m_spriteHeight;

    public SpriteAnimation(Bitmap bitmap) {
        super(bitmap);

        m_rect=new Rect(0,0,0,0);
        m_frameTImer=0;
        m_currentFrame=0;
    }

    public void initSpriteData(int _width, int _height, int _fps, int iFrame)
    {
        m_spriteWidth=_width;
        m_spriteHeight=_height;
        m_fps=1000/_fps;
        m_iFrames=iFrame;
        m_rect.top=0;
        m_rect.bottom=m_spriteHeight;
        m_rect.left=0;
        m_rect.right=m_spriteWidth;
    }

    @Override
    public void Draw(Canvas canvas) {
        Rect dest=new Rect(m_x,m_y,m_x+m_spriteWidth,m_y+m_spriteHeight);
        canvas.drawBitmap(m_bitmap,m_rect,dest,null);

    }

    public void Update(long gameTime)
    {
        if(gameTime>m_frameTImer+m_fps)
        {
            m_frameTImer=gameTime;
            m_currentFrame+=1;

            // 넘어가면 다시 처음 이미지로
            if(m_currentFrame>=m_iFrames)
                m_currentFrame=0;
        }
        m_rect.left=m_currentFrame*m_spriteWidth;
        m_rect.right=m_rect.left+m_spriteWidth;
    }
}
