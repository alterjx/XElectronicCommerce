package com.example.xjj.core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * @author : xjj
 * @date : 2018/4/21
 * email : alterjx@163.com
 * describe : 全局的核心工具类
 */
public final class Equinox {

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String, Object> getConfigurations() {
        return Configurator.getInstance()
                           .getConfigurations();
    }

    public static Context getApplication() {
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }

}
