package com.example.xjj.xelectroniccommerce;

import android.app.Application;

import com.example.xjj.core.app.Equinox;
import com.example.xjj.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * @author : xjj
 * @date : 2018/4/22
 * email : alterjx@163.com
 * describe : TODO
 */
public class MyApplication
        extends Application
{

    @Override
    public void onCreate() {
        super.onCreate();
        Equinox.init(this)
               .withApiHost("http://114.67.235.114/RestServer/api/")
               .withIcon(new FontAwesomeModule())
               .withIcon(new FontEcModule())
               .configure();
    }
}
