package com.argon.client.gui;

import com.argon.client.ArgonClient;
import com.argon.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class ClickGui extends Screen {
    public ClickGui() {
        super(Text.of("Argon ClickGui"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.drawBackground(context.getMatrices(), 0x55000000, mouseX, mouseY);
        super.render(context, mouseX, mouseY, delta);
        MatrixStack matrices = context.getMatrices();
        int y = 20;
        for (Module module : ArgonClient.MODULE_MANAGER.getModules()) {
            String status = module.isEnabled() ? "[ON]" : "[OFF]";
            String line = module.getName() + " " + status;
            context.drawText(mc.textRenderer, line, 20, y, 0xFFFFFF, false);
            y += 15;
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int y = 20;
        for (Module module : ArgonClient.MODULE_MANAGER.getModules()) {
            if (mouseY >= y && mouseY <= y + 12 && mouseX >= 20 && mouseX <= 20 + 100) {
                module.toggle();
                return true;
            }
            y += 15;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
}