package com.argon.client.module.impl.movement;

import com.argon.client.module.Module;
import net.minecraft.util.math.Vec3d;

public class Speed extends Module {

    private final double multiplier = 1.5;

    public Speed() {
        super("Speed", "Increases player movement speed");
    }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (mc.player.isSprinting()) {
            Vec3d vel = mc.player.getVelocity();
            mc.player.setVelocity(vel.x * multiplier, vel.y, vel.z * multiplier);
        }
    }
}