package com.example.xjj.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * @author : xjj
 * @date : 2018/4/22
 * email : alterjx@163.com
 * describe : TODO
 */
public enum EcIcons implements Icon {
    //青蛙图标
    icon_frog('\ue782');

    private char mChar;

    EcIcons(char character) {
        mChar = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return mChar;
    }

    //TODO
    public static char changeUnicode(String string) {
        //&#xe782;     icon_frog('\ue782');
        return ' ';
    }
}
