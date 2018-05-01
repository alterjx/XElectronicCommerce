package com.example.xjj.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * @author : xjj
 * @date : 2018/4/22
 * email : alterjx@163.com
 * describe : TODO
 */
public class FontEcModule
        implements IconFontDescriptor
{
    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}
