package cn.com.coolsoft.notebook;

import android.app.Application;

import com.orm.SugarContext;

/**
 * @author admin
 * @time 2019/9/9 14
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
