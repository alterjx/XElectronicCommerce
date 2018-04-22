package com.example.xjj.core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author : xjj
 * @date : 2018/4/22
 * email : alterjx@163.com
 * describe : TODO
 */
public class Configurator {

    private static final  HashMap<String, Object> CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();


    private Configurator() {
        CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    public static Configurator getInstance() {
        return ConfiguratorHolder.INSTANCE;
    }

    public void initIcons() {
        if (ICONS.size() > 0) {
            Iconify.IconifyInitializer iconifyInitializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                iconifyInitializer.with(ICONS.get(i));
            }
        }
    }

    public void configure() {
        initIcons();
        CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public HashMap<String, Object> getConfigurations() {
        return CONFIGS;
    }

    public Configurator withApiHost(String host) {
        CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    public Configurator withIcon(IconFontDescriptor iconFontDescriptor) {
        ICONS.add(iconFontDescriptor);
        return this;
    }

    private void checkConfiguration() {
        boolean isReady = (boolean) CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration not completed");
        }
    }

    @SuppressWarnings("unchecked")
    public<T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) CONFIGS.get(key.name());
    }



    private static class ConfiguratorHolder{
        public static final Configurator INSTANCE = new Configurator();
    }

}
