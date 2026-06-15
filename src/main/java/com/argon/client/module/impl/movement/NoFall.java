package com.argon.client.module.impl.movement;

import com.argon.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class NoFall extends Module {

    public NoFall() {
        super("NoFall");
    }

    @Override
    protected void onEnable() {}

    @Override
    protected void onDisable() {}

    @Override
    public void onTick(MinecraftClient client) {
        PlayerEntity player = client.player;
        if (player == null) return;

        if (player.fallDistance > 2.0f) {
            // Reset fall distance to prevent damage
            player.fallDistance = 0.0f;
            // Slight upward motion to keep in air
            player.setVelocity(new Vec3d(player.getVelocity().x, 0.05, player.getVelocity().z));
        }
    }
}