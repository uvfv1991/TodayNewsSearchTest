package mm.tencent.com.splashvideodemo;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends Activity {
    private MVideoView m_videoView;
    private TextView m_skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       m_videoView = (MVideoView) findViewById(R.id.vv_videoview);
       m_skip=(TextView) findViewById(R.id.tv_skip);
       m_videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+ File.separator+R.raw.my_video2));
       m_videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {

            mp.start();

        }


    });

       m_videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
           @Override
           public void onCompletion(MediaPlayer mp) {
               mp.start();
           }
       });

       CountTimer countTimer = new CountTimer(5, new CountTimer.ICountHandler() {
           @Override
           public void onTicker(int time) {
               m_skip.setText(time+"秒");
           }

           @Override
           public void onFinish() {
               m_skip.setText("跳过");

           }
       });
       countTimer.start();
    }
}
