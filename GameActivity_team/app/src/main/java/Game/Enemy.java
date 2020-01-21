package Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

import kr.co.apps.gameactivity_framework.AppManeger;
import kr.co.apps.gameactivity_framework.SpriteAnimation;

public class Enemy extends SpriteAnimation {
    protected int StageNumber;
    protected int hp; // 상속 받을 적군들의 HP
    protected float speed; // 상속 받을 적군들의 스피드

    protected int width;// 불러온 이미지 너비
    protected int height;// 불러온 이미지 높이
    protected Bitmap bitmap;

    public static final int MOVE_PATTERN_1= 0;// 무브 타임에 들어갈 변수
    public static final int MOVE_PATTERN_2= 1;
    public static final int MOVE_PATTERN_3= 2;
    public static final int MOVE_PATTERN_4= 3;
    public static final int MOVE_PATTERN_5= 4;
    public static final int MOVE_PATTERN_6= 5;

    protected int movetype;

    //상태를 나타내는 변수들
    public static final int STATE_NORMAL=0;// 기본 상태
    public static final int STATE_OUT=1;
    public int state=STATE_NORMAL;

    //충돌 구현을 위한 Rect
    Rect m_BoundBox=new Rect();

    void Move()
    {
        if(movetype==MOVE_PATTERN_1)//행동 패턴1
        {
            if(m_y<=200)
                m_y+=speed;
            else
                m_y+= speed* 2;//
        }
        else if(movetype== MOVE_PATTERN_2)
        {
            if (m_y <= 200) m_y += speed;// 중간지점까지일자로이동
            else {
                m_x += speed;// 중감지점이후대각선이동
                m_y += speed;// 오른쪽 아래로 이동
            }
        }
        else if(movetype== MOVE_PATTERN_3)
        {
            if (m_y <= 300)
                m_y += speed;// 중간지점까지일자로이동
            else
                {
                m_x -= speed;// 중감지점이후대각선이동
                m_y += speed;// 왼쪽 아래로 이동동
           }
        }

        if(movetype==MOVE_PATTERN_4)//행동 패턴1
        {
            if (m_y <= 200) {
                m_x -= speed;
                m_y += speed;// 중간지점까지일자로이동
            }
            else if(m_y > 200 && m_y <= 500){
                m_x += speed;// 중감지점이후대각선이동
                m_y += speed;// 오른쪽 아래로 이동
            }
            else if(m_y > 500 && m_y <= 1000)
            {
                m_x -= speed;
                m_y += speed;// 오른쪽 아래로 이동
            }
            else if(m_y > 1000 && m_y <= 1800){
                m_x += speed;
                m_y += speed;// 오른쪽 아래로 이동
            }
        }
        else if(movetype== MOVE_PATTERN_5)
        {
            if (m_y <= 400) {
                m_y += speed;// 중간지점까지일자로이동
            }
            else if(m_y > 400 && m_y <= 600){
                m_x += speed;// 중감지점이후대각선이동
                m_y += speed;// 오른쪽 아래로 이동
            }
            else if(m_y > 600 && m_y <= 800)
            {
                m_x -= speed;
                m_y += speed;// 오른쪽 아래로 이동
            }
            else if(m_y > 800){
                m_y += speed;// 오른쪽 아래로 이동
            }
        }
        else if(movetype== MOVE_PATTERN_6) {
            if (m_y <= 200) {
                m_x -= speed;
                m_y += speed + 50;// 중간지점까지일자로이동
            }
            else if(m_y > 200 && m_y <= 500){
                m_x += speed;// 중감지점이후대각선이동
                m_y += speed;// 오른쪽 아래로 이동
            }
            else if(m_y > 500 && m_y <= 1000)
            {
                m_x -= speed;
                m_y += speed+ 50;// 오른쪽 아래로 이동
            }
            else if(m_y > 1000 && m_y <= 1800){
                m_x += speed;
                m_y += speed;// 오른쪽 아래로 이동
            }

        }

    }

    long LastShoot=System.currentTimeMillis();

    public void Attack()
    {
        if (System.currentTimeMillis() - LastShoot >= 1000) {
            LastShoot = System.currentTimeMillis();

            // 미사일 발사 로직
            if(AppManeger.getInstance().m_gamestate_1!=null)
                AppManeger.getInstance().m_gamestate_1.m_enemmslist.add(new Missile_Enemy(m_x+10, m_y+200));

            if(AppManeger.getInstance().m_gamestate_2!=null) {
                AppManeger.getInstance().m_gamestate_2.m_enemmslist.add(new Missile_Enemy(m_x + 10, m_y + 200));

            }

            if(AppManeger.getInstance().m_gamestate_3!=null)
                AppManeger.getInstance().m_gamestate_3.m_enemmslist.add(new Missile_Enemy(m_x+10, m_y+200));
        }
    }
    public Enemy(Bitmap bitmap) {
        super(bitmap);
    }

    @Override
    public void initSpriteData(int _width, int _height, int _fps, int iFrame) {
        super.initSpriteData(_width, _height, _fps, iFrame);
    }

    @Override
    public void Draw(Canvas canvas) {
        super.Draw(canvas);
    }

    @Override
    public void Update(long gameTime) {
        super.Update(gameTime);
        if(m_y>1800)
            state=STATE_OUT;
        Move();
    }

}
