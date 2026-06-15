package com.argon.client.module;

import com.argon.client.ArgonClient;
import net.minecraft.client.MinecraftClient;

public abstract class Module {
    private final String name;
    private final Category category;
    private boolean enabled;

    public Module(String name, Category category) {
        this.name = name;
        this.category = category;
        this.enabled = false;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        if (this.enabled == enabled) return;
        this.enabled = enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void toggle() {
        setEnabled(!enabled);
    }

    protected void onEnable() {
        // Override if needed
    }

    protected void onDisable() {
        // Override if needed
    }

    public void onTick() {
        // Override if needed
    }

    public enum Category {
        COMBAT,
        MOVEMENT,
        PLAYER,
        RENDER
    }
}