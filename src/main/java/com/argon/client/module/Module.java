package com.argon.client.module;

import net.minecraft.client.MinecraftClient;

public abstract class Module {
    private final String name;
    private final String description;
    private boolean enabled = false;

    protected Module(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void toggle() {
        enabled = !enabled;
        if (enabled) onEnable();
        else onDisable();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Called every client tick
    public void onTick() {
        if (enabled) {
            onUpdate();
        }
    }

    protected void onEnable() {}
    protected void onDisable() {}
    protected void onUpdate() {}

    protected MinecraftClient mc() {
        return MinecraftClient.getInstance();
    }
}