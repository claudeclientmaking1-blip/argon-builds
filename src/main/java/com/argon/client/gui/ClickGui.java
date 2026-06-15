package com.argon.client.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.client.gui.DrawContext;

public class ClickGui extends Screen {

    public ClickGui() {
        super(Text.literal("Argon"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        // Simple placeholder GUI
        context.drawCenteredText(this.textRenderer, "Argon ClickGui", this.width / 2, 20, 0xFFFFFF);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}