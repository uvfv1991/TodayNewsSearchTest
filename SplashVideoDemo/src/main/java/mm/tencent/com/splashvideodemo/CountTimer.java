package mm.tencent.com.splashvideodemo;

import android.os.Handler;

/**
 * @Created by JiangXue
 * @Created on 2019/4/3.
 **/
public class CountTimer implements Runnable{

    private int counttime;
    private final Handler handler;
    private final ICountHandler countHandler;
    private boolean isTick;

    public CountTimer(int time,ICountHandler countHandler) {
        handler= new Handler();
        this.counttime=time;
        this.countHandler=countHandler;

    }

    @Override
    public void run() {
        if (isTick){
            if (countHandler != null){

                countHandler.onTicker(counttime);
            }

            if (counttime == 0){
                if (countHandler!= null){
                    countHandler.onFinish(); }

            }else{

                counttime=counttime-1;
                handler.postDelayed(this,1000);
            }
        }

    }

    public void start() {
        isTick = true;
        handler.post(this);

    }

    public void stop() {
        isTick = false;
        handler.removeCallbacks(this);

    }

    public interface ICountHandler {

        void onTicker(int time);

        void onFinish();

    }
}
