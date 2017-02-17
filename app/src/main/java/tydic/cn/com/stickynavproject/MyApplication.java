package tydic.cn.com.stickynavproject;

import android.app.Application;

import com.bilibili.boxing.BoxingCrop;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.loader.IBoxingMediaLoader;

/**
 * Created by yechenglong on 2017/2/17.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        IBoxingMediaLoader loader = new BoxingFrescoLoader(this);
        BoxingMediaLoader.getInstance().init(loader);
        BoxingCrop.getInstance().init(new BoxingUcrop());

    }
}
