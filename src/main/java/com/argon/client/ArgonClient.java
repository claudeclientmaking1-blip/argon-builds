package com.argon.client;

import com.argon.client.module.ModuleManager;
import com.argon.client.gui.ClickGui;
import com.argon.client.gui.HudRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class ArgonClient implements ClientModInitializer {

    public static final String MOD_ID = "argon";
    public static final ModuleManager moduleManager = new ModuleManager();
    public static final HudRenderer hudRenderer = new HudRenderer();
    public static final ClickGui clickGui = new ClickGui();

    private static KeyBinding toggleGuiKey;

    @Override
    public void onInitializeClient() {
        // Register modules
        moduleManager.registerAllDefaults();

        // Keybinding to open ClickGUI
        toggleGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.argon.toggle_gui",
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.argon"
        ));

        // Tick listener for key handling
        net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleGuiKey.wasPressed()) {
                clickGui.toggle();
            }
        });
    }
}