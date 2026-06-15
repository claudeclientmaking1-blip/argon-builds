package com.argon.client;

import com.argon.client.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class ArgonClient implements ClientModInitializer {
    public static final ModuleManager MODULE_MANAGER = new ModuleManager();

    @Override
    public void onInitializeClient() {
        // Register modules
        MODULE_MANAGER.register(new com.argon.client.module.impl.combat.ArgonAimAssist());
        MODULE_MANAGER.register(new com.argon.client.module.impl.combat.DoomsdayAimAssist());
        // Placeholder modules (no-op)
        MODULE_MANAGER.register(new com.argon.client.module.impl.combat.KillAura());
        MODULE_MANAGER.register(new com.argon.client.module.impl.combat.Velocity());
        MODULE_MANAGER.register(new com.argon.client.module.impl.combat.Reach());
        MODULE_MANAGER.register(new com.argon.client.module.impl.combat.Criticals());
        MODULE_MANAGER.register(new com.argon.client.module.impl.movement.Speed());
        MODULE_MANAGER.register(new com.argon.client.module.impl.movement.NoFall());
        MODULE_MANAGER.register(new com.argon.client.module.impl.movement.Sprint());
        MODULE_MANAGER.register(new com.argon.client.module.impl.player.AutoClicker());
        MODULE_MANAGER.register(new com.argon.client.module.impl.render.ESP());

        // Tick listener for modules that need it
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            MODULE_MANAGER.onTick();
        });
    }

    public static MinecraftClient getMC() {
        return MinecraftClient.getInstance();
    }
}