package com.argon.client.gui;

import com.argon.client.ArgonClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class HudRenderer {

    public void render(MatrixStack matrices) {
        TextRenderer tr = net.minecraft.client.MinecraftClient.getInstance().textRenderer;
        int y = 10;
        for (var module : ArgonClient.moduleManager.getModules()) {
            if (module.isEnabled()) {
                tr.draw(matrices, module.getName(), 10, y, 0xFFFFFF);
                y += 10;
            }
        }
    }
}