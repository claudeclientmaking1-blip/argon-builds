package com.argon.client.module.impl.movement;

import com.argon.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

public class Sprint extends Module {

    public Sprint() {
        super("Sprint");
    }

    @Override
    protected void onEnable() {}

    @Override
    protected void onDisable() {}

    @Override
    public void onTick(MinecraftClient client) {
        PlayerEntity player = client.player;
        if (player == null) return;

        if (!player.isSprinting() && player.input.movementForward > 0) {
            player.setSprinting(true);
        }
    }
}