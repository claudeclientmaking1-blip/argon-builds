package com.argon.client.module.impl.combat;

import com.argon.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public class Velocity extends Module {

    public Velocity() {
        super("Velocity");
    }

    @Override
    protected void onEnable() {}

    @Override
    protected void onDisable() {}

    @Override
    public void onTick(MinecraftClient client) {
        // Simple anti-knockback: nullify motion each tick
        if (client.player != null) {
            client.player.setVelocity(Vec3d.ZERO);
        }
    }
}