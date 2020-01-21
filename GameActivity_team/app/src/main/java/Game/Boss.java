package Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

import kr.co.apps.gameactivity_framework.AppManeger;
import kr.co.apps.gameactivity_framework.R;
import kr.co.apps.gameactivity_framework.SpriteAnimation;

public class Boss extends SpriteAnimation{

    //충돌 구현을 위한 Rect
    Rect m_BoundBox=new Rect();

    int hp;
    int speed;

    //방향 플레그?? 같은 느낌
    public static final int go=0;
    public static final int back=1;
    public int goback=go;
    public int updown=go;


    public static final int STATE_NORMAL=0;// 기본 상태
    public static final int STATE_OUT=1;
    public int state=STATE_NORMAL;

    Random rnadom =new Random();
    int movespeed;

    public Boss(Bitmap bitmap) {
        super(bitmap);
    }

    @Override
    public void Update(long gameTime) {
        super.Update(gameTime);
        if(hp<=0)
            state=STATE_OUT;
        Move();
        Attack();
    }

    long LastShoot=System.currentTimeMillis();// 적 미사일 시간

    public void Attack()
    {
        if (System.currentTimeMillis() - LastShoot >= 1000) {
            LastShoot = System.currentTimeMillis();

            // 미사일 발사 로직
            if(AppManeger.getInstance().m_gamestate_1!=null)
                AppManeger.getInstance().m_gamestate_1.m_enemmslist.add(new Missile_Enemy(m_x+150, m_y+500));

            if(AppManeger.getInstance().m_gamestate_2!=null) {
                AppManeger.getInstance().m_gamestate_2.m_enemmslist.add(new Missile_Enemy(m_x + 150, m_y + 500));

            }

            if(AppManeger.getInstance().m_gamestate_3!=null)
                AppManeger.getInstance().m_gamestate_3.m_enemmslist.add(new Missile_Enemy(m_x+150, m_y+500));
        }
    }

    public void Move()
    {
        // 스테이지 1에서 움직임(좌우)
        if(AppManeger.getInstance().m_gamestate_1!=null)
        {
            // 좌우
            if (goback == go)
            {
                m_x += 20;
                if (m_x > 800)
                    goback = back;
            } else
            {
                m_x -= 20;
                if (m_x < 0)
                    goback = go;
            }
        }

         //스테이지 2에서 움직임
        if(AppManeger.getInstance().m_gamestate_2!=null)
        {
            // 마름모 꼴
            if (goback == go)
            {
                m_x += 20;
                if (m_x > 600)
                    goback = back;
            } else
            {
                m_x -= 20;
                if (m_x < 0)
                    goback = go;
            }
            if (updown == go)
            {
                m_y += 50;
                if (m_y > 1000)
                    updown = back;
            } else
            {
                m_y -= 50;
                if (m_x <200)
                    updown = go;
            }
        }
        //스테이지 2에서 움직임 + 속도 변화
        if(AppManeger.getInstance().m_gamestate_3!=null)
        {
            //마름모 + 속도 ↑
            movespeed=30+rnadom.nextInt(30);
            // 마름모 꼴
            if (goback == go)
            {
                m_x += movespeed;
                if (m_x > 500)
                    goback = back;
            } else
            {
                m_x -= movespeed;
                if (m_x < 0)
                    goback = go;
            }
            if (updown == go)
            {
                m_y += 60;
                if (m_y > 1000)
                    updown = back;
            } else
            {
                m_y -= 60;
                if (m_x <200)
                    updown = go;
            }
        }

    }

    @Override
    public void initSpriteData(int _width, int _height, int _fps, int iFrame) {
        super.initSpriteData(_width, _height, _fps, iFrame);
    }

    @Override
    public void Draw(Canvas canvas) {
        super.Draw(canvas);
    }

}

