package identify.dai.com.accelerationball.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import identify.dai.com.accelerationball.R;

/**
 * @Created by JiangXue
 * @Created on 2019/1/28.
 **/
public class FloatCircleView extends View {

    public int height=150;
    public int width=150;
    private Paint textPaint;
    private Paint circlePaint;
    private Bitmap bt;
    private String text="50%";
    private boolean drag;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FloatCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaints();
    }

    public FloatCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaints();
    }

    public FloatCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaints();
    }

    public FloatCircleView(Context context) {
        super(context);
        initPaints();
    }

    private void initPaints() {

        circlePaint = new Paint();
        circlePaint.setColor(Color.GRAY);
        circlePaint.setAntiAlias(true);

        textPaint=new Paint();
        textPaint.setTextSize(25);
        textPaint.setColor(Color.WHITE);
        textPaint.setAntiAlias(true);
        textPaint.setFakeBoldText(true);


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.default_icon);
        bt =Bitmap.createScaledBitmap(bitmap,width,height,true);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width,height);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        if (drag){
            canvas.drawBitmap(bt,0,0,null);
        }else{
            canvas.drawCircle(width/2,height/2,width/2,circlePaint);
            float textwidth = textPaint.measureText(text);
            float x = width/2 -textwidth/2;
            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
            float dy = (fontMetrics.descent + fontMetrics.ascent) / 2;
            float y = height/2 +dy;
            canvas.drawText(text,x,y,textPaint);

        }



    }

    public void setDragState(boolean b) {
        drag = b;
        invalidate();
    }


}
