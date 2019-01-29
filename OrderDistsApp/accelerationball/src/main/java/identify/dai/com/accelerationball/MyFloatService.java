package identify.dai.com.accelerationball;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * @Created by JiangXue
 * @Created on 2019/1/29.
 **/
public class MyFloatService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        FloatViewManager floatViewManager = FloatViewManager.getInstance(this);
        floatViewManager.showFloatViewManager();
        super.onCreate();
    }
}
