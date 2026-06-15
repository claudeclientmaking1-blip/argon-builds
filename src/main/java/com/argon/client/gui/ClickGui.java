package com.argon.client.gui;

import com.argon.client.module.Module;
import com.argon.client.module.ModuleManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ClickGui extends Screen {

    protected ClickGui() {
        super(Text.literal("Argon Click GUI"));
    }

    @Override
    protected void init() {
        int y = 20;
        for (Module module : ModuleManager.getModules()) {
            ButtonWidget btn = ButtonWidget.builder(
                    Text.literal(module.getName() + (module.isEnabled() ? " [ON]" : " [OFF]")),
                    button -> {
                        module.toggle();
                        button.setMessage(Text.literal(module.getName() + (module.isEnabled() ? " [ON]" : " [OFF]")));
                    })
                    .position(20, y)
                    .size(120, 20)
                    .build();
            addDrawableChild(btn);
            y += 25;
        }
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}