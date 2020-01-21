package kr.co.apps.gameactivity_framework;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import java.util.HashMap;

public class SoundManager {
    private static SoundManager s_instance;


    private SoundPool m_SoundPool;
    private HashMap m_SoundPoolMap;// 주소 값으로 찾아가기 때문에 빠르다
    private AudioManager m_AudioManager;
    private Context m_Activity;

    public MediaPlayer mp_background1;
    public MediaPlayer mp_background2;


    public void Init(Context _context)
    {
        /*배경음과 효과음을 셋팅하기 위해*/
        m_SoundPool=new SoundPool(4,AudioManager.STREAM_MUSIC,0);
        m_SoundPoolMap=new HashMap();
        m_AudioManager=(AudioManager)_context.getSystemService(Context.AUDIO_SERVICE);
        m_Activity=_context;

        mp_background1 = MediaPlayer.create(_context,R.raw.background);
        mp_background2 = MediaPlayer.create(_context, R.raw.background2);
    }
    public void playBackground1() {
        // 배경음1 재생
        mp_background1.setLooping(true);
        mp_background1.start();
    }
    public void stopBackground1() {
        // 배경음1 중지
        mp_background1.stop();
    }
    public void playBackground2() {
        // 배경음2 재생
        mp_background2.setLooping(true);
        mp_background2.start();
    }
    public void stopBackground2() {
        // 배경음2 중지
        mp_background2.stop();
    }
    public void addSound(int _index, int _soundID)
    {
        /*효과음을 셋팅 하기 위해*/
        int id=m_SoundPool.load(m_Activity,_soundID,1);
        m_SoundPoolMap.put(_index,id);
    }
    public void play( int _index) {
        // 효과음 재생
        float streamVolume = m_AudioManager .getStreamVolume(AudioManager. STREAM_MUSIC);
        streamVolume = streamVolume / m_AudioManager .getStreamMaxVolume(AudioManager. STREAM_MUSIC);
        m_SoundPool .play((Integer) m_SoundPoolMap .get(_index), streamVolume, streamVolume, 1, 0, 1f);
    }

    /*SoundManager를 어디서든 쓰기 위해서*/
    public static SoundManager getInstance()
    {
        if(s_instance==null)
            s_instance=new SoundManager();
        return s_instance;
    }
}