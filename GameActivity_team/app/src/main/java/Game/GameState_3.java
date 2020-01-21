package Game;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

import kr.co.apps.gameactivity_framework.AppManeger;
import kr.co.apps.gameactivity_framework.GameClear;
import kr.co.apps.gameactivity_framework.GameOver;
import kr.co.apps.gameactivity_framework.IState;
import kr.co.apps.gameactivity_framework.R;
import kr.co.apps.gameactivity_framework.SoundManager;

public class GameState_3 implements IState {
    // 여기가 3탄

    int subcnt=0;// 서브미사일

    // 토마호크 미사일
    int twflag = -1;//토마호크 나가게할 플래그 변수
    long Tmw=System.currentTimeMillis();// 토마호크 미사일 시간 변수
    long TWTime;//토마호크 발동시간 체크할 변수
    ArrayList<Tomahawk> c_chk = new ArrayList<Tomahawk>();//궁극 비행기

    public void MakeMS()
    {

        chk1 = new Tomahawk();
        chk2 = new Tomahawk();
        chk3 = new Tomahawk();

        chk1.setPosition(50,1100);
        chk2.setPosition(400,800);
        chk3.setPosition(700,1100);

        c_chk.add(chk1);
        c_chk.add(chk2);
        c_chk.add(chk3);

    }
    public void MakeTomahawk()
    {

        if(System.currentTimeMillis()-Tmw >= 100) {
            Tmw = System.currentTimeMillis();
            ;// 여기서 플레이어와 같은 위치에서 미사일이 나감


            Missile_Tomahawk tms = new Missile_Tomahawk(chk1.getX()+70,chk1.getY(), 50);// 미사일 위치,위치,속도
            Missile_Tomahawk tms2 = new Missile_Tomahawk(chk2.getX()+70,chk2.getY(), 50);// 미사일 위치,위치,속도
            Missile_Tomahawk tms3 = new Missile_Tomahawk(chk3.getX()+70,chk3.getY(), 50);// 미사일 위치,위치,속도

            t_tom.add(tms);
            t_tom.add(tms2);
            t_tom.add(tms3);

            // 플레이어 미사일 배열에 추가
        }

    }

    ArrayList<Missile_Tomahawk> t_tom = new ArrayList<Missile_Tomahawk>();//토마호크
    Tomahawk chk1,chk2,chk3;


    int player_hp = 10; // 플레이어 라이프
    int player_missile_speed = 50;// 미사일 스피드
    int player_missile_regen_speed = 1000;//0.8초마다 미사일 나감
    int bomb = 2;// 궁극기 개수

    Boss boss;

    private Player m_player; // 플레이어
    private BackGround m_background; // 배경

    int x, y; // 좌우 이동
    int width = AppManeger.getInstance().getGameView().getDisplay().getWidth();
    int height = AppManeger.getInstance().getGameView().getDisplay().getHeight();

    long LastRegenEnemy = System.currentTimeMillis();// 적을 생성
    long PlayerMissile = System.currentTimeMillis();// 플레이어 미사일 만들어내는 시간 변수
    long LastRegenBox = System.currentTimeMillis();

    Random randomEnem = new Random();
    Random randomBox = new Random();

    ArrayList<Missile_Player> m_pmslist = new ArrayList<Missile_Player>();// 플레이어에 미사일 종류를 arraylist로 저장
    ArrayList<Missile_sub1>sub1List=new ArrayList<Missile_sub1>();// helpPlayer 미사일1
    ArrayList<Missile_sub2>sub2List=new ArrayList<Missile_sub2>(); // helpPlayer 미사일2
    ArrayList<Enemy> m_enemlist = new ArrayList<Enemy>();// 적을 리스트 형식으로 표현

    ArrayList<Missile> m_enemmslist = new ArrayList<Missile>();// 적들 미사일 변수
    ArrayList<RandomBox> m_randomboxList = new ArrayList<RandomBox>();

    @Override
    public void Init() {

        if (AppManeger.getInstance().pNum == 1)
            m_player = new Player(AppManeger.getInstance().getBitmap(R.drawable.aircraft1),player_hp);
        else {
            Bitmap bitmap = AppManeger.getInstance().getBitmap(R.drawable.aircraft2);
            bitmap = Bitmap.createScaledBitmap(bitmap, 450, 450, true);
            m_player = new Player(bitmap,player_hp);
        }

        m_background = new BackGround(2);
    }

    public GameState_3()// Appmanager에 GameState_1등록
    {
        AppManeger.getInstance().m_gamestate_3 = this;
    }

    //충돌 확인 메서드
    public void CheckCollision() {

        //토마호크 미사일 + 잡몹, 토마호크 미사일 + 보스몹몹
        for(int i=t_tom.size()-1;i>=0;i--)
        {
            if(boss!=null)
            {
                if(CollisionManager.CheckBoxToBox(boss.m_BoundBox,t_tom.get(i).m_BoundBox))
                {
                    boss.hp--;
                    t_tom.get(i).state=Enemy.STATE_OUT;
                    t_tom.remove(i);

                    /*플레이어 미사일과 보스몹이 충돌하면 효과음 재생*/
                    if(AppManeger.getInstance().m_effectsound == "on") {
                        SoundManager.getInstance().play(1);
                    }

                }
            }
            for(int j=m_enemlist.size()-1;j>=0;j--)
            {
                if(CollisionManager.CheckBoxToBox(t_tom.get(i).m_BoundBox,m_enemlist.get(j).m_BoundBox))
                {
                    t_tom.get(i).state=Enemy.STATE_OUT;
                    t_tom.remove(i);
                    m_enemlist.get(j).state=Enemy.STATE_OUT;
                    m_enemlist.remove(j);

                    /*플레이어 미사일과 보스몹이 충돌하면 효과음 재생*/
                    if(AppManeger.getInstance().m_effectsound == "on") {
                        SoundManager.getInstance().play(1);
                    }
                }
            }
        }

        //플레이어 + 보스몹 충돌
        if (boss != null) {
            if (CollisionManager.CheckBoxToBox(m_player.m_BoundBox, boss.m_BoundBox)) {
                m_player.destroyPlayer();

                /*플레이어 미사일과 보스몹이 충돌하면 효과음 재생*/
                if(AppManeger.getInstance().m_effectsound == "on") {
                    SoundManager.getInstance().play(1);
                }

            }
        }

        //플레이어 미사일 + 보스몹 충돌
        for (int i = m_pmslist.size() - 1; i >= 0; i--) {
            if (boss != null)
                if (CollisionManager.CheckBoxToBox(m_pmslist.get(i).m_BoundBox, boss.m_BoundBox)) {
                    boss.hp-=2;
                    m_pmslist.get(i).state = Missile.STATE_OUT;
                    m_pmslist.remove(i);

                    /*플레이어 미사일과 보스몹이 충돌하면 효과음 재생*/
                    if(AppManeger.getInstance().m_effectsound == "on") {
                        SoundManager.getInstance().play(1);
                    }
                }
        }

        //플레이어 + 적 충돌
        for (int i = m_enemlist.size() - 1; i >= 0; i--) {
            if (CollisionManager.CheckBoxToBox(m_player.m_BoundBox, m_enemlist.get(i).m_BoundBox)) {
                m_player.destroyPlayer();// 라이프 값을 하나 감소
                m_enemlist.get(i).state = Enemy.STATE_OUT;
                m_enemlist.remove(i);// 충돌 했으니까 적도 제거
                //여기에 진동 기능 추가
                AppManeger.getInstance().getGameView().viberator();

                /*플레이어 미사일과 보스몹이 충돌하면 효과음 재생*/
                if(AppManeger.getInstance().m_effectsound == "on") {
                    SoundManager.getInstance().play(2);
                }


            }
        }

        //플레이어 미사일 + 적군들 충돌처리
        for (int i = m_pmslist.size() - 1; i >= 0; i--) {
            for (int j = m_enemlist.size() - 1; j >= 0; j--) {
                if (CollisionManager.CheckBoxToBox(m_pmslist.get(i).m_BoundBox, m_enemlist.get(j).m_BoundBox)) {
                    m_pmslist.get(i).state = Missile.STATE_OUT;
                    m_pmslist.remove(i);// 플레이어 미사일 제거

                    m_enemlist.get(j).state = Enemy.STATE_OUT;
                    m_enemlist.remove(j); // 충돌당한 적군 제거

                    /*플레이어 미사일과 보스몹이 충돌하면 효과음 재생*/
                    if(AppManeger.getInstance().m_effectsound == "on") {
                        SoundManager.getInstance().play(1);
                    }
                    return;
                }
            }
        }

        //플레이어 + 적 미사일 충돌
        for (int i = m_enemmslist.size() - 1; i >= 0; i--) {
            if (CollisionManager.CheckBoxToBox(m_player.m_BoundBox, m_enemmslist.get(i).m_BoundBox)) {
                m_player.destroyPlayer();// 플레이어 라이프 감소
                m_enemmslist.get(i).state = Missile.STATE_OUT;
                AppManeger.getInstance().getGameView().viberator();
                m_enemmslist.remove(i);

                /*플레이어 미사일과 보스몹이 충돌하면 효과음 재생*/
                if(AppManeger.getInstance().m_effectsound == "on") {
                    SoundManager.getInstance().play(2);
                }

            }
        }

        // 랜덤박스 + 플레이어 충돌
        for (int i = m_randomboxList.size() - 1; i >= 0; i--) {
            if (CollisionManager.CheckBoxToBox(m_player.m_BoundBox, m_randomboxList.get(i).m_BoundBox)) {
                if (m_randomboxList.get(i).BoxType == 0)// hp회복
                {
                    if(subcnt<5)
                        subcnt++;

                    m_randomboxList.remove(i);
                    m_player.addLife();
                    AppManeger.getInstance().getGameView().viberator();
                } else if (m_randomboxList.get(i).BoxType == 1)// 플레이어 미사일 속도
                {
                    if(subcnt<5)
                        subcnt++;

                    player_missile_speed += 5;
                    m_randomboxList.remove(i);
                    AppManeger.getInstance().getGameView().viberator();

                } else if (m_randomboxList.get(i).BoxType == 2)// 미사일 리젠 속도
                {
                    if(subcnt<5)
                        subcnt++;

                    player_missile_regen_speed -= 10;// 0.01초 감소
                    m_randomboxList.remove(i);
                    AppManeger.getInstance().getGameView().viberator();
                }
                else if (m_randomboxList.get(i).BoxType == 3)// 플레이어 hp감소
                {
                    m_player.destroyPlayer();
                    m_randomboxList.remove(i);
                    AppManeger.getInstance().getGameView().viberator();

                }
                else if (m_randomboxList.get(i).BoxType == 4)// 미사일 속도 감소
                {
                    player_missile_speed-=10;
                    m_randomboxList.remove(i);
                    AppManeger.getInstance().getGameView().viberator();
                }
                else if (m_randomboxList.get(i).BoxType == 5)// 미사일 리젠 속도 감소
                {
                    player_missile_regen_speed-=50;//
                    m_randomboxList.remove(i);
                    AppManeger.getInstance().getGameView().viberator();
                }
                else if (m_randomboxList.get(i).BoxType == 6)// 궁극기 수 증가
                {
                    bomb++;
                    m_randomboxList.remove(i);
                    AppManeger.getInstance().getGameView().viberator();
                }
                return;
            }
        }


        // 보조 플레이어1,2 + 보스
        if(boss!=null)
        {
            if(AppManeger.getInstance().subMissilecnt>21)
            {
                for(int i=sub1List.size()-1;i>=0;i--)
                {
                    if(CollisionManager.CheckBoxToBox(sub1List.get(i).m_BoundBox, boss.m_BoundBox))
                    {
                        boss.hp--;
                        sub1List.get(i).state=Missile.STATE_OUT;
                        sub1List.remove(i);

                        /*플레이어 미사일과 보스몹이 충돌하면 효과음 재생*/
                        if(AppManeger.getInstance().m_effectsound == "on") {
                            SoundManager.getInstance().play(1);
                        }
                    }
                }

                for(int i=sub2List.size()-1;i>=0;i--)
                {
                    if(CollisionManager.CheckBoxToBox(sub2List.get(i).m_BoundBox, boss.m_BoundBox))
                    {
                        boss.hp--;
                        sub2List.get(i).state=Missile.STATE_OUT;
                        sub2List.remove(i);

                        /*플레이어 미사일과 보스몹이 충돌하면 효과음 재생*/
                        if(AppManeger.getInstance().m_effectsound == "on") {
                            SoundManager.getInstance().play(1);
                        }
                    }
                }
            }
        }
    }

    // 보스생성 메서드
    public void MakeBoss() {
        boss = new Boss3();
        boss.setPosition(350, 10);
        boss.speed = 60;

    }

    public void MakeEnemy()// 적을 생성
    {
        if (m_background.getY() < -1000)
            if (System.currentTimeMillis() - LastRegenEnemy >= 1000) // 3초가 지나면 적들이 생성
            {
                LastRegenEnemy = System.currentTimeMillis();

                Enemy enem = null;
                int enemtype = randomEnem.nextInt(6);

                if (enemtype == 0)
                    enem = new Enemy_1();

                else if (enemtype == 1)
                    enem = new Enemy_2();

                else if (enemtype == 2)
                    enem = new Enemy_3();

                else if (enemtype == 3)
                    enem = new Enemy_4();

                else if (enemtype == 4)
                    enem = new Enemy_5();

                else if (enemtype == 5)
                    enem = new Enemy_6();


                width = AppManeger.getInstance().getGameView().getDisplay().getWidth();
                enem.setPosition(60 + randomEnem.nextInt(width - 100), -60);// 화면 밖에서 생성(좌우는 랜덤)
                enem.movetype = randomEnem.nextInt(6);// 랜덤으로 무브타입 생성
                enem.speed=10+randomEnem.nextInt(20);

                m_enemlist.add(enem);
            }
    }
    public void MakePlayerMissile()// 미사일을 생성
    {
        //미사일이 0.5초마다 나감
        if(System.currentTimeMillis()-PlayerMissile>=1000) {
            PlayerMissile = System.currentTimeMillis();
            ;// 여기서 플레이어와 같은 위치에서 미사일이 나감
            Missile_Player pms = new Missile_Player(x, y-70, player_missile_speed);// 미사일 위치,위치,속도

            // 플레이어 미사일 list에 추가
            m_pmslist.add(pms);

            if(subcnt>=5)
            {
                ;// 여기서 플레이어와 같은 위치에서 미사일이 나감
                Missile_sub1 sub1 = new Missile_sub1(x-100, y-100, player_missile_speed);// 미사일 위치,위치,속도
                Missile_sub2 sub2=new Missile_sub2(x+100,y-100,player_missile_speed);

                sub1List.add(sub1);
                sub2List.add(sub2);
            }
        }

    }

    public void MakeRandomBox()// 랜덤 박스 생성
    {
        if(boss==null) {
            if (System.currentTimeMillis() - LastRegenBox >= 2500) {
                LastRegenBox = System.currentTimeMillis();

                RandomBox m_randomBox = null;
                int boxType = randomBox.nextInt(7);

                if (boxType == 0)
                    m_randomBox = new RandomBox_HP(); // 플레이어 목숨 수

                else if (boxType == 1)
                    m_randomBox = new RandomBox_MissileRegenSpeed();// 미사일 리젠 속도

                else if (boxType == 2)
                    m_randomBox = new RandomBox_MIssileSpeed();// 플레이어 미사일 속도

                else if(boxType==3)
                    m_randomBox=new RandomBox_DeHP();// 플레이어 hp감소

                else if(boxType==4)
                    m_randomBox=new RandomBox_DeMissileSpeed();// 플레이어 미사일 속도 감소

                else if(boxType==5)
                    m_randomBox=new RandomBox_DeMissileRegenSpeed();// 플레이어 미사일 속도 감소

                else if(boxType==6)
                    m_randomBox=new RandomBox_add_bomb();// 궁극기 증가가

               width = AppManeger.getInstance().getGameView().getDisplay().getWidth();

                m_randomBox.setPosition(160 + randomBox.nextInt(width - 320), -60); // 랜덤 박스 위추
                m_randomboxList.add(m_randomBox);

            }
        }
    }

    @Override
    public void Update()
    {

        long GameTime = System.currentTimeMillis();

        m_player.Update(GameTime); // 플레이어 이미지 갱신
        m_background.Update(GameTime); // 백그라운드 이미지 갱신

        //플레이어 미사일 업데이트 및 상태 확인
        for (int i = m_pmslist.size() - 1; i >= 0; i--) {
            m_pmslist.get(i).Update();
            if (m_pmslist.get(i).state == Missile.STATE_OUT) // 미사일의 상태가 OUT이면 제거
                m_pmslist.remove(i);
        }

        // 적군 미사일 업데이트 및 상태 확인
        for (int i = m_enemmslist.size() - 1; i >= 0; i--) {
            m_enemmslist.get(i).Update(GameTime);
            if (m_enemmslist.get(i).state == Missile.STATE_OUT)
                m_enemmslist.remove(i);
        }

        //적군 업데이트 및 상태 확인
        for (int j = m_enemlist.size() - 1; j >= 0; j--) {
            m_enemlist.get(j).Update(GameTime);
            if (m_enemlist.get(j).state == Enemy.STATE_OUT)// 적군의 상태가 OUT이면 제거거
                m_enemlist.remove(j);
        }

        //랜덤 박스 업데이트 및 상태 확인
        for (int k = m_randomboxList.size() - 1; k >= 0; k--) {
            m_randomboxList.get(k).Update(GameTime);
            if (m_randomboxList.get(k).state == RandomBox.STATE_OUT)
                m_randomboxList.remove(k);
        }

        if(subcnt>=5) {
            // 서브 미사일 업데이트
            for (int i = sub1List.size() - 1; i >= 0; i--) {
                sub1List.get(i).Update(GameTime);
                if (sub1List.get(i).state == Missile.STATE_OUT)
                    sub1List.remove(i);
            }

            //서브 미사일 업데이트
            for (int i = sub2List.size() - 1; i >= 0; i--) {
                sub2List.get(i).Update(GameTime);
                if (sub2List.get(i).state == Missile.STATE_OUT)
                    sub2List.remove(i);
            }
        }

        MakeEnemy(); // 적군을 생성
        CheckCollision();// 충돌 확인
        MakeRandomBox();// 랜덤 박스를 생성
        MakePlayerMissile();// 자동으로 미사일 나감

        //맵 높이 기준 1000일때 보스 생성
        if(m_background.getY()==-1000)
            MakeBoss();

        //보스 객체가 있으면 상태 업데이트 및 상태 체크
        if(boss!=null)
        {
            boss.Update(GameTime);
            if(boss.state==Boss.STATE_OUT) {
                boss = null;//보스가 죽으면 다음 스테이지로 넘어감
                Intent intent=new Intent(AppManeger.getInstance().getGameView().getContext(), GameClear.class);
                AppManeger.getInstance().getGameView().getContext().startActivity(intent);
            }
        }



        //토마호크 미사일
        for (int j = t_tom.size() - 1; j >= 0; j--) {
            t_tom.get(j).Update();
            if (t_tom.get(j).state == Enemy.STATE_OUT)// 적군의 상태가 OUT이면 제거거
                t_tom.remove(j);
        }

        //실시간 적 좌표(토마호크)(따라간다)
        for (Tomahawk ck : c_chk){
            for ( Enemy enem : m_enemlist ){
                if(boss!=null) {
                    int bossxx = boss.getX();
                    int bossyy = boss.getY();
                    if (bossxx > 0 &&bossxx < 1000 && bossyy > 0 && bossyy < 1000) {
                        ck.Update(bossxx, bossyy);
                        break;
                    }
                }
                int xx = enem.getX();
                int yy = enem.getY();
                if(boss==null) {
                    if (xx > 0 && xx < 1000 && yy > 0 && yy < 1000) {
                        ck.Update(xx, yy);
                        break;
                    }
                }
                //실시간 적기 좌표값 넘기는
            }
        }
        if(twflag == 1){
            MakeTomahawk();
        }
        if(System.currentTimeMillis() - TWTime >= 5000 && twflag == 1) {//10초뒤 제거
            twflag = 0;
            c_chk.clear();
            t_tom.clear();
        }
    }

    @Override
    public void Render(Canvas canvas) {// 여기서 업데이트된 이미지들을 그려준다
        m_background.Draw(canvas);
        m_player.Draw(canvas);

        Paint p = new Paint();
        p.setTextSize(50);
        p.setColor(Color.WHITE);

        canvas.drawText(" 3 Stage",width/10*4,100,p);// 스테이지 이름
        canvas.drawText(" 미사일 속도 " + String.valueOf(player_missile_speed), 10, height-150, p);// 현재 미사일 속도
        canvas.drawText("목숨 x"+m_player.m_Life,10,height-100,p);// 현재 플레리어 목숨

        Bitmap bitmap=AppManeger.getInstance().getBitmap(R.drawable.bomb);// 폭탄 이미지를 불러오고
        bitmap=bitmap.createScaledBitmap(bitmap,200,200,true);// 적절한 사이즈로 맞춘다
        canvas.drawBitmap(bitmap,width/10*8,height-300,null);//궁극기 이미지
        canvas.drawText(" x"+bomb, width/10*9,height-100,p);// 궁극기가 개수

        // 서브 미사일 그린다
        if(subcnt>=5)
        {
            for(Missile sub1:sub1List)
                sub1.Draw(canvas);
            for(Missile sub2:sub2List)
                sub2.Draw(canvas);
        }

        // 랜덤 박스 그려줌
        for (RandomBox randomBox : m_randomboxList)
            randomBox.Draw(canvas);

        //적 미사일 내려가는거 그려줌
        for (Missile enemms : m_enemmslist)
            enemms.Draw(canvas);

        // 적이미지 그려줌
        for (Enemy enem : m_enemlist)
            enem.Draw(canvas);

        //플레이어 미사일
        for (Missile_Player pms : m_pmslist)
            pms.Draw(canvas);

        //보스 객체가 있으면 그려준다
        if(boss!=null) {
            boss.Draw(canvas);
            canvas.drawText("보스 목숨" + boss.hp,0,400,p);
        }


        // 토마호크 케릭터
        if(twflag  == 1)
        {
            for (Tomahawk chk : c_chk) {
                chk.Draw(canvas);
            }
        }

        //토마호크 미사일
        for(Missile_Tomahawk tom: t_tom)
            tom.Draw(canvas);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {// 뒤로가기 등등 설정
        return true;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int action = event.getAction();
        x = (int) event.getX(0);
        y = (int) event.getY(0);
        if(x > width/10*8 && x < width/10*8+200
                && y > height-300&& y < height-100 && action == MotionEvent.ACTION_DOWN)
        {
            if(bomb>0) {
                MakeMS();
                twflag = 1;
                TWTime = System.currentTimeMillis();
                bomb--;
                return true       ;
            }
        }
        m_player.setPosition(x - 170, y - 200);
        return true;
    }

    @Override
    public void Destroy() {
    }
}
