package com.argon.client.module.impl.combat;

import com.argon.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

/**
 * Minimal stub for DoomsdayAim to satisfy compilation.
 * This example simply rotates the player toward the nearest target.
 */
public class DoomsdayAim extends Module {

    public DoomsdayAim() {
        super("DoomsdayAim");
    }

    @Override
    public void onEnable() {
        // No special enable logic.
    }

    @Override
    public void onDisable() {
        // No special disable logic.
    }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.world == null) return;

        LivingEntity nearest = null;
        double bestDist = Double.MAX_VALUE;

        for (LivingEntity e : mc.world.getEntitiesByClass(LivingEntity.class,
                mc.player.getBoundingBox().expand(10.0), ent -> ent != mc.player && ent.isAlive())) {
            double d = mc.player.squaredDistanceTo(e);
            if (d < bestDist) {
                bestDist = d;
                nearest = e;
            }
        }

        if (nearest != null) {
            // Simple rotation towards target
            double dx = nearest.getX() - mc.player.getX();
            double dy = nearest.getEyeY() - mc.player.getEyeY();
            double dz = nearest.getZ() - mc.player.getZ();
            double dist = Math.sqrt(dx * dx + dz * dz);
            float yaw = (float) Math.toDegrees(Math.atan2(-dx, dz));
            float pitch = (float) Math.toDegrees(-Math.atan2(dy, dist));
            mc.player.setYaw(yaw);
            mc.player.setPitch(pitch);
        }
    }
}