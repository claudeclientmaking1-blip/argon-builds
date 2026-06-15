package com.argon.client.gui;

import com.argon.client.module.Module;
import com.argon.client.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class HudRenderer {
    public static void render(DrawContext context, net.minecraft.client.util.math.MatrixStack matrices) {
        MinecraftClient client = MinecraftClient.getInstance();
        int y = 10;
        for (Module module : ModuleManager.getModules()) {
            if (module.isEnabled()) {
                String text = module.getName() + " ON";
                context.drawText(client.textRenderer, text, 10, y, 0x00FF00, true);
                y += client.textRenderer.fontHeight + 2;
            }
        }
    }
}