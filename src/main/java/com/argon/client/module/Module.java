package com.argon.client.module;

import net.minecraft.client.MinecraftClient;

public abstract class Module {
    private final String name;
    private boolean enabled = false;

    public Module(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void toggle() {
        enabled = !enabled;
        if (enabled) onEnable();
        else onDisable();
    }

    protected abstract void onEnable();
    protected abstract void onDisable();

    /** Called each tick on the client thread */
    public void onTick(MinecraftClient client) {}
}