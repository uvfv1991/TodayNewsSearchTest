package identify.dai.com.accelerationball.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import identify.dai.com.accelerationball.R;

/**
 * @Created by JiangXue
 * @Created on 2019/1/29.
 **/
public class MyProgressView extends View {
    public int height = 200;
    public int width = 200;
    private Canvas bitmapCanvas;
    private Bitmap bt;

    private Path path = new Path();
    private Paint paint;
    private Paint textPaint;
    private Paint progressPaint;
    private int progress = 100;
    private int currentprogress = 0;
    private int max = 100;
    private Bitmap bitmap;
    private boolean drag;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {


        }
    };


    public MyProgressView(Context context) {
        super(context);
        init();
    }

    private void init() {

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.argb(0xff, 0x3a, 0x8c, 0x6c));

        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);
        progressPaint.setColor(Color.argb(0xff, 0x4e, 0xc9, 0x63));
        progressPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //只绘制重叠的部分


        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(25);

        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);//创建画布

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.default_icon);
        bt =Bitmap.createScaledBitmap(bitmap,width,height,true);


        final GestureDetector gestureDetector = new GestureDetector(new MyGestureDetectorListener());

        setClickable(true);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    public void setDragState(boolean b) {
    }

    class MyGestureDetectorListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDoubleTap(MotionEvent e) {

            startDoubleAnimate();
            return super.onDoubleTap(e);
        }

        private void startDoubleAnimate() {

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    currentprogress++;
                    if (currentprogress <= progress) {
                        invalidate();
                        handler.postDelayed(this, 50);
                    } else {

                        handler.removeCallbacks(this);
                        currentprogress = 0;
                    }
                }
            }, 50);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }

    }

    public MyProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (drag){
            canvas.drawBitmap(bt,0,0,null);
        }else {
            bitmapCanvas.drawCircle(width / 2, height / 2, width / 2, paint);
            path.reset();
            float y = (1 - (float) currentprogress / max) * height;
            path.moveTo(width, y);
            path.lineTo(width, height);//右下角
            path.lineTo(0, height);//左下角
            path.lineTo(0, y);

            float d = (1 - ((float) currentprogress / max)) * 10;
            for (int i = 0; i < 5; i++) {

                path.rQuadTo(10, -d, 20, 0);
                path.rQuadTo(10, d, 20, 0);

            }
            path.close();

            bitmapCanvas.drawPath(path, progressPaint);
            String text = (float) currentprogress + "%";
            float measureText = textPaint.measureText(text);
            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
            float baseline = height / 2 - (fontMetrics.descent + fontMetrics.ascent) / 2;
            bitmapCanvas.drawText(String.valueOf(currentprogress + "%"), width / 2 - measureText / 2, baseline, textPaint);
            canvas.drawBitmap(bitmap, 0, 0, null);
        }
        super.onDraw(canvas);

    }
}
