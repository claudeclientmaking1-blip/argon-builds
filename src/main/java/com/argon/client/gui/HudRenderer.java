package com.argon.client.gui;

import com.argon.client.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

/**
 * Renders enabled modules on the HUD.
 */
public class HudRenderer {

    public static void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        int y = 10;
        for (var module : ModuleManager.getModules()) {
            if (module.isEnabled()) {
                String text = module.getName() + " ON";
                context.drawText(client.textRenderer, text, 10, y, 0x00FF00, true);
                y += 10;
            }
        }
    }
}