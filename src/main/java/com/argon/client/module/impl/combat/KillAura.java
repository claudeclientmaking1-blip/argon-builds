package com.argon.client.module.impl.combat;

import com.argon.client.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class KillAura extends Module {

    private final double range = 4.5; // argon style range

    public KillAura() {
        super("KillAura", "Simple argon‑style aim assist");
    }

    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null) return;
        if (mc.player.isUsingItem() && mc.player.getActiveItem().isFood()) return;

        List<LivingEntity> targets = mc.world.getEntitiesByClass(LivingEntity.class, mc.player.getBoundingBox().expand(range), e -> 
                e != mc.player && e.isAlive() && !e.isSpectator());

        if (targets.isEmpty()) return;

        // Choose the closest target
        LivingEntity target = targets.stream()
                .min(Comparator.comparing(e -> e.squaredDistanceTo(mc.player)))
                .orElse(null);

        if (target == null) return;

        // Aim at target
        aimAt(target);

        // Attack
        if (mc.interactionManager.getAttackCooldown() == 0) {
            mc.interactionManager.attackEntity(mc.player, target);
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }

    private void aimAt(Entity target) {
        Vec3d eyePos = mc.player.getEyePos();
        Vec3d targetPos = target.getBoundingBox().getCenter();
        double diffX = targetPos.x - eyePos.x;
        double diffY = targetPos.y - eyePos.y;
        double diffZ = targetPos.z - eyePos.z;
        float yaw = (float) Math.toDegrees(Math.atan2(diffZ, diffX)) - 90F;
        float pitch = (float) -Math.toDegrees(Math.atan2(diffY, Math.sqrt(diffX * diffX + diffZ * diffZ)));
        mc.player.setYaw(yaw);
        mc.player.setPitch(pitch);
    }
}