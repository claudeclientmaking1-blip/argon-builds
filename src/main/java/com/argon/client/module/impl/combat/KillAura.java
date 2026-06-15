package com.argon.client.module.impl.combat;

import com.argon.client.module.Module;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;

public class KillAura extends Module {

    private static final double RANGE = 4.5;

    public KillAura() {
        super("KillAura", "Argon style auto‑attack");
    }

    @Override
    protected void onUpdate() {
        ClientPlayerEntity player = mc().player;
        if (player == null || player.isDead()) return;

        Entity target = getNearestTarget();
        if (target != null) {
            player.lookAtEntity(target, 90.0f, 90.0f);
            player.swingHand(Hand.MAIN_HAND);
            player.interact(target, Hand.MAIN_HAND);
        }
    }

    private Entity getNearestTarget() {
        ClientPlayerEntity player = mc().player;
        if (player == null) return null;

        double closest = RANGE;
        Entity closestEntity = null;
        for (Entity e : mc().world.getEntities()) {
            if (e instanceof LivingEntity le && le != player && !le.isSpectator() && le.isAlive()) {
                double dist = player.squaredDistanceTo(e);
                if (dist < closest * closest) {
                    closest = Math.sqrt(dist);
                    closestEntity = e;
                }
            }
        }
        return closestEntity;
    }
}