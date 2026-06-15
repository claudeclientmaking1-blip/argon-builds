package com.argon.client.gui;

import com.argon.client.ArgonClient;
import com.argon.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class HudRenderer {
    public static void render(DrawContext context, float tickDelta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        TextRenderer textRenderer = mc.textRenderer;
        MatrixStack matrices = context.getMatrices();
        int y = 10;
        for (Module module : ArgonClient.MODULE_MANAGER.getModules()) {
            if (module.isEnabled()) {
                String text = module.getName() + " [ON]";
                textRenderer.draw(matrices, text, 10, y, 0xFFFFFF);
                y += 10;
            }
        }
    }
}