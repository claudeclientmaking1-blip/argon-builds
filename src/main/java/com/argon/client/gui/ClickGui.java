package com.argon.client.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

/**
 * Very basic placeholder click GUI.
 */
public class ClickGui extends Screen {

    public ClickGui() {
        super(Text.literal("Argon"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        // Draw title centered at the top
        context.drawCenteredText(this.textRenderer, "Argon ClickGui", this.width / 2, 20, 0xFFFFFF);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}