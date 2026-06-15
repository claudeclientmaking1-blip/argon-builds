package com.argon.client.module.impl.movement;

import com.argon.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

/**
 * Simple auto‑sprint module.
 */
public class Sprint extends Module {

    public Sprint() {
        super("Sprint", "Automatically sprint when moving forward");
    }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;
        PlayerEntity player = mc.player;

        // In 1.21 player input is accessed via getInput()
        if (!player.isSprinting() && player.getInput().movementForward > 0) {
            player.setSprinting(true);
        }
    }
}