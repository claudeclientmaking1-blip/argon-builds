package com.argon.client.module.impl.combat;

import com.argon.client.module.Module;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;

import java.util.Comparator;
import java.util.List;

public class Reach extends Module {

    private final double extraReach = 3.0;

    public Reach() {
        super("Reach", "Extends attack reach");
    }

    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null) return;

        List<LivingEntity> targets = mc.world.getEntitiesByClass(LivingEntity.class,
                mc.player.getBoundingBox().expand(extraReach), e -> e != mc.player && e.isAlive());

        if (targets.isEmpty()) return;

        LivingEntity closest = targets.stream()
                .min(Comparator.comparing(e -> e.squaredDistanceTo(mc.player)))
                .orElse(null);

        if (closest == null) return;

        // Aim
        Vec3d eye = mc.player.getEyePos();
        Vec3d targetPos = closest.getBoundingBox().getCenter();

        double dx = targetPos.x - eye.x;
        double dy = targetPos.y - eye.y;
        double dz = targetPos.z - eye.z;

        float yaw = (float) Math.toDegrees(Math.atan2(dz, dx)) - 90f;
        float pitch = (float) -Math.toDegrees(Math.atan2(dy, Math.sqrt(dx * dx + dz * dz)));

        mc.player.setYaw(yaw);
        mc.player.setPitch(pitch);

        // Attack
        if (mc.interactionManager.getAttackCooldown() == 0) {
            mc.interactionManager.attackEntity(mc.player, closest);
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }
}