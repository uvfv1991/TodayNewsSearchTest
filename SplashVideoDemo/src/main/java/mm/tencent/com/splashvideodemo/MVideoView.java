package mm.tencent.com.splashvideodemo;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * @Created by JiangXue
 * @Created on 2019/4/2.
 **/
public class MVideoView extends VideoView {

    public MVideoView(Context context) {
        super(context);

    }
    //自定义属性
    public MVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //支持自定义属性和自定义样式
    public MVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width =getDefaultSize(0,widthMeasureSpec);
        int height =getDefaultSize(0,heightMeasureSpec);
        setMeasuredDimension(width,height);
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
