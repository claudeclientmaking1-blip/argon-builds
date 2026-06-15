package com.argon.client.module;

/**
 * Base class for all modules.
 */
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
        if (enabled) onEnable(); else onDisable();
    }

    public void setEnabled(boolean state) {
        if (enabled != state) {
            enabled = state;
            if (enabled) onEnable(); else onDisable();
        }
    }

    /** Called when the module is enabled. */
    public void onEnable() {}

    /** Called when the module is disabled. */
    public void onDisable() {}

    /** Called each client tick. */
    public void onTick() {}
}