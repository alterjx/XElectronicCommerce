package com.example.xjj.core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.xjj.core.app.Equinox;

/**
 * @author : xjj
 * @date : 2018/4/22
 * email : alterjx@163.com
 * describe : TODO
 */
public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Equinox.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Equinox.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

}
