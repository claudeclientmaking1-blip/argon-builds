package com.argon.client.gui;
import com.argon.client.module.Module;
import com.argon.client.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
public class HudRenderer {
    public static void render(DrawContext ctx) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;
        int y = 2;
        for (Module m : ModuleManager.getModules()) {
            if (!m.isEnabled()) continue;
            String text = m.getName();
            int w = mc.textRenderer.getWidth(text);
            int x = ctx.getScaledWindowWidth() - w - 2;
            ctx.fill(x-1, y-1, x+w+1, y+9, 0x80000000);
            ctx.drawText(mc.textRenderer, text, x, y, 0xFF55FF55, true);
            y += 11;
        }
    }
}