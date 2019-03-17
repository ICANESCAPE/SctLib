package org.sct.sctlib;

import org.bukkit.plugin.java.JavaPlugin;

public final class SctLib extends JavaPlugin {

    private static SctLib instance;

    @Override
    public void onEnable() {
        instance = this;

    }

    @Override
    public void onDisable() {

    }

    public static org.sct.sctlib.SctLib getInstance() {
        return instance;
    }
}
