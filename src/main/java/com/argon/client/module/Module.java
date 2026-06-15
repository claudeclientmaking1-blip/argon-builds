package com.argon.client.module;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {

    private final String name;
    private final String description;
    private boolean enabled = false;
    protected final MinecraftClient mc = MinecraftClient.getInstance();

    public Module(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void toggle() {
        enabled = !enabled;
        onToggle(enabled);
        if (mc.player != null) {
            mc.player.sendMessage(Text.literal(getName() + (enabled ? " enabled" : " disabled")));
        }
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

    /** Called when the module is toggled */
    protected void onToggle(boolean enabled) {}

    /** Called each client tick when the module is enabled */
    public void onTick() {}

    /** Called when the world unloads (e.g., leaving a server) */
    public void onWorldUnload() {}

    /** Settings handling (placeholder) */
    protected final List<String> settings = new ArrayList<>();

    public List<String> getSettings() {
        return settings;
    }
}