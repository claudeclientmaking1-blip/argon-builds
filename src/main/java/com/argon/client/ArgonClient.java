package com.argon.client;

import com.argon.client.gui.HudRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

/**
 * Main entry point for the Argon client.
 */
public class ArgonClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Register HUD rendering. The lambda receives DrawContext and tickDelta.
        HudRenderCallback.EVENT.register((context, tickDelta) -> HudRenderer.render(context, tickDelta));
    }
}