package identify.dai.com.accelerationball;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import identify.dai.com.accelerationball.view.FloatCircleView;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

/**
 * @Created by JiangXue
 * @Created on 2019/1/28.
 **/
public class FloatViewManager {

    private Context context;
    public FloatCircleView circleView;
    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    float startx = 0;
    float starty = 0;

    private View.OnTouchListener onTouchListener=new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()){
                
                case ACTION_DOWN:
                    startx =event.getRawX();
                    starty = event.getRawY();
                break;

                case ACTION_MOVE:
                    float x =event.getRawX();
                    float y = event.getRawY();
                    float dx = x - startx;
                    float dy = y - starty;
                    layoutParams.x+=dx;
                    layoutParams.y+=dy;
                    circleView.setDragState(true);
                    windowManager.updateViewLayout(circleView,layoutParams);
                    startx=x;
                    starty=y;

                    break;

                case ACTION_UP:
                    float x1 =event.getRawX();
                    if (x1>getScreenWidth()/2){
                        layoutParams.x=getScreenWidth()-circleView.width;

                    }else{
                        layoutParams.x=0;
                    }
                    circleView.setDragState(false);
                    windowManager.updateViewLayout(circleView,layoutParams);

                    break;
                
                


            }
            return false;
        }
    };



    private int  getScreenHeight() {
        return  windowManager.getDefaultDisplay().getHeight();
    }

    private int getScreenWidth() {
        return  windowManager.getDefaultDisplay().getWidth();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private FloatViewManager(Context context) {

        this.context = context;
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        circleView = new FloatCircleView(context,null,0,0);
        circleView.setOnTouchListener(onTouchListener);
    }

    private static FloatViewManager instance;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static FloatViewManager getInstance(Context context) {

        if (instance == null) {
            synchronized (FloatViewManager.class) {
                instance = new FloatViewManager(context);
            }


        }
        return instance;
    }

    public void updateFloatViewManager() {
    }

    public void removeFloatViewManager() {
    }

    public void showFloatViewManager() {

        layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = circleView.width;
        layoutParams.height = circleView.height;
        layoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.format = PixelFormat.RGB_888;
        windowManager.addView(circleView, layoutParams);

    }


}
