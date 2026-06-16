package com.argon.client.module;
import net.minecraft.client.MinecraftClient;
public abstract class Module {
    protected static final MinecraftClient mc = MinecraftClient.getInstance();
    private final String name;
    private final Category category;
    private boolean enabled = false;
    public enum Category { COMBAT, MOVEMENT, PLAYER, RENDER }
    public Module(String name, Category category) { this.name = name; this.category = category; }
    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}
    public String getName() { return name; }
    public Category getCategory() { return category; }
    public boolean isEnabled() { return enabled; }
    public void toggle() { enabled = !enabled; if (enabled) onEnable(); else onDisable(); }
    public void setEnabled(boolean e) { this.enabled = e; if (e) onEnable(); else onDisable(); }
}