package com.argon.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import com.argon.client.gui.HudRenderer;
import com.argon.client.module.ModuleManager;

public class ArgonClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Initialise modules
        ModuleManager.init();

        // Register HUD renderer
        HudRenderCallback.EVENT.register((context, tickCounter) -> HudRenderer.render(context, tickCounter));
    }
}