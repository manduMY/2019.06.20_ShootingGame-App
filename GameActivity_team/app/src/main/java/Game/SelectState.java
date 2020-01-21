package Game;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

import kr.co.apps.gameactivity_framework.AppManeger;
import kr.co.apps.gameactivity_framework.IState;
import kr.co.apps.gameactivity_framework.R;


//IState를 구현상속받은 클래스로서 캐릭터를 플레이할 비행기를 선택할 수 있음
//onTouchEvent에서 설정한 좌표를 터치했을 시 비행기 선택 및 게임으로의 전한이 이뤄진다.
public class SelectState implements IState {

    private Bitmap m_startbackground;//배경을 받아올 비트맵
    private Bitmap c1;//비행기 1의 선택 전후의 화면을 넣을 비트맵
    private Bitmap c2;//비행기 2의 선택 전후의 화면을 넣을 비트맵
    private Bitmap gamestart;//게임 시작으로 넘어갈
    int x, y;//화면의 좌표값을 받아올 변수
    int flag1 = -1;//

    public SelectState()//스테이트 정보를 앱매니저에게 전달함
    {
        AppManeger.getInstance().m_selectstate = this;
    }

    @Override
    public void Init() {
        c1 = AppManeger.getInstance().getBitmap(R.drawable.aircraft6);
        c1 = Bitmap.createScaledBitmap(c1,400, c1.getHeight(), true);//비행기 1의 비트맵을 받아와 사이즈까지 조절 함

        c2 = AppManeger.getInstance().getBitmap(R.drawable.aircraft8);
        c2 = Bitmap.createScaledBitmap(c2,400, c1.getHeight(), true);//비행기 2의 비트맵을 받아와 사이즈까지 조절 함

        gamestart = AppManeger.getInstance().getBitmap(R.drawable.gamestart);// 게임 시작 이미지를 받아와 저장

        m_startbackground = AppManeger.getInstance().getBitmap(R.drawable.gamestartimage); // 선택화면에서 백그라운드
        m_startbackground = Bitmap.createScaledBitmap(m_startbackground,852/2*7, 2800, true);//배경의 비트맵을 받아와 저장하고 사이즈 조절
    }

    @Override
    public void Render(Canvas canvas) {//배경, 비행기( 1, 2), 게임 시작 이미지를 그리게할 메서드
        canvas.drawBitmap(m_startbackground,0,0,null);//
        canvas.drawBitmap(c1,852/10,380,null);
        canvas.drawBitmap(c2,852/10*7,380,null);
        canvas.drawBitmap(gamestart,852/10*3,m_startbackground.getHeight()/10*5,null);

        Paint p=new Paint();//화면 상단에 캐릭터 선택하라는 문구를 띄움
        p.setTextSize(80);
        p.setColor(Color.GREEN);
        canvas.drawText("캐릭터를 선택하세요!",825/10*2,200, p);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        x = (int) event.getX(0);
        y = (int) event.getY(0);//x, y의 int형 변수에 화면의 좌표를 가져온다

        if (x > 852/10 && x < 852/10+400 && y > 380 && y < 1200)//비행기 1의 좌표를 선택했을 시 선택 이미지를 불러옴
        {//비행기 1 선택 시
            c1 = Bitmap.createScaledBitmap(AppManeger.getInstance().getBitmap(R.drawable.aircraft6s),400, c1.getHeight(), true);
            AppManeger.getInstance().pNum = 1;

            flag1 = 0;//플래그 변수를 이용하여 비행이가 이중으로 선택하지 않도록 함

            if(flag1 == 0) {//비행기 1이 선택 이미지가 선택되면 비행기 2는 선택화면이 되지 않게 처리
                c2 = Bitmap.createScaledBitmap(AppManeger.getInstance().getBitmap(R.drawable.aircraft8),400, c1.getHeight(), true);
            }
        }

        else if (x > 852/10*7 && x < 852/10*7+400&& y > 380 && y < 1200)//비행기 2 선택 시 위의 if문과 처리는 같음
        {//비행기 2 선택 시
            c2 = Bitmap.createScaledBitmap(AppManeger.getInstance().getBitmap(R.drawable.aircraft8s),400, c2.getHeight(), true);
            AppManeger.getInstance().pNum = 2;
            flag1 = 1;

            if(flag1 == 1) {
                c1 = Bitmap.createScaledBitmap(AppManeger.getInstance().getBitmap(R.drawable.aircraft6),400, c1.getHeight(), true);
            }
        }
        else if (x > 852/10*3&& x < 852/10*3+gamestart.getWidth() && y > m_startbackground.getHeight()/10*5
                && y < m_startbackground.getHeight()/10*5+gamestart.getHeight()) {//게임 스타트 선택 시
            if(AppManeger.getInstance().pNum == -1)//AppManeger에 pNum이라는 변수를 0으로 초기화해 놓고 비행기 1이 선택되면 1로 2가 선택되면 2로 바꾸고 처음에 0의 선택되지 않은 상태일 시에는 토스트 메시지를 띄워서 게임화면으로 가지 않게 함
                Toast.makeText(AppManeger.getInstance().getGameView().getContext(),"캐릭터를 선택해주세요!!", Toast.LENGTH_SHORT).show();
            else
                AppManeger.getInstance().getGameView().changeGameState( new GameState_1() );
        }
        //Toast.makeText(AppManeger.getInstance().getGameView().getContext()," " + x + " " + " , " + y + " ", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void Destroy() {
    }
    @Override
    public void Update() {
    }
}