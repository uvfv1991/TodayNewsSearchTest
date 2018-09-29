package admin1.example.com.orderdistsapp;


import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.mob.MobApplication;
import com.squareup.leakcanary.ActivityRefWatcher;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;

public class DiskApplication extends MobApplication {
    @Nullable
    private static Context context;

    public com.squareup.leakcanary.RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initBugly();

        refWatcher = LeakCanary.install(this);

    }

    private void initBugly() {

        CrashReport.initCrashReport(getApplicationContext(), "3fa487a2cd", false);

    }


    public static Context getContext() {
        return DiskApplication.context;
    }

    public static RefWatcher getRefWatcher(Context context) {
        DiskApplication application = (DiskApplication) context.getApplicationContext();
        return application.refWatcher;
    }
}
