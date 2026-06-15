package com.argon.client.gui;

import com.argon.client.module.Module;
import com.argon.client.module.ModuleManager;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class HudRenderer {

    private final TextRenderer font;

    public HudRenderer(TextRenderer font) {
        this.font = font;
    }

    public void render(DrawContext context) {
        int y = 10;
        for (Module module : ModuleManager.getModules()) {
            if (module.isEnabled()) {
                String text = module.getName();
                context.drawText(font, text, 10, y, 0xFFFFFF, true);
                y += 10;
            }
        }
    }
}