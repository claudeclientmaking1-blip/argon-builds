package com.argon.client.module.impl.combat;

import com.argon.client.module.Module;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class Criticals extends Module {

    public Criticals() {
        super("Criticals", "Force critical hits on attack");
    }

    @Override
    public void onTick() {
        // Simple placeholder: make the player slightly jump each attack cooldown
        if (mc.player == null) return;
        if (mc.interactionManager.getAttackCooldown() == 0 && mc.player.isOnGround()) {
            mc.player.jump();
        }
    }
}