package com.argon.client.gui;

import com.argon.client.ArgonClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ClickGui extends Screen {

    private boolean open = false;

    protected ClickGui() {
        super(Text.of("Argon ClickGUI"));
    }

    public void toggle() {
        open = !open;
        if (open) {
            mc().setScreen(this);
        } else {
            mc().setScreen(null);
        }
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float delta) {
        this.renderBackground(drawContext);
        int y = 30;
        for (var module : ArgonClient.moduleManager.getModules()) {
            drawContext.drawText(mc().textRenderer,
                    module.getName() + (module.isEnabled() ? " [ON]" : " [OFF]"),
                    20, y, 0xFFFFFF, false);
            y += 12;
        }
        super.render(drawContext, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}