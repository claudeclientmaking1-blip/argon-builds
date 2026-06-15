package com.argon.client;

import com.argon.client.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class ArgonClient implements ClientModInitializer {

    public static final String MOD_ID = "argon";

    @Override
    public void onInitializeClient() {
        // Initialize modules
        ModuleManager.init();

        // Register tick event to update modules each client tick
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.world != null && client.player != null) {
                ModuleManager.getModules().forEach(module -> {
                    if (module.isEnabled()) {
                        module.onTick();
                    }
                });
            }
        });
    }
}