package com.argon.client.module.impl.combat;

import com.argon.client.module.Module;
import net.minecraft.entity.damage.DamageSource;

public class Velocity extends Module {

    public Velocity() {
        super("Velocity", "Reduces knockback");
    }

    @Override
    public void onTick() {
        // Simple placeholder – real implementation would listen to damage events via mixins
        // Here we just set a very low knockback strength each tick
        if (mc.player != null) {
            mc.player.setVelocity(mc.player.getVelocity().multiply(0.9));
        }
    }
}