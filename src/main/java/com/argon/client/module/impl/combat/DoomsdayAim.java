package com.argon.client.module.impl.combat;

import com.argon.client.module.Module;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

import java.util.Comparator;
import java.util.List;

/**
 * Doomsday style aim assist – faster target acquisition,
 * slight randomization to appear less bot‑like.
 */
public class DoomsdayAim extends Module {

    private final double range = 5.0;
    private final double randomness = 0.15; // random offset to yaw/pitch

    public DoomsdayAim() {
        super("DoomsdayAim", "Aggressive aim assist with jitter");
    }

    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null) return;

        List<LivingEntity> targets = mc.world.getEntitiesByClass(LivingEntity.class, mc.player.getBoundingBox().expand(range), e ->
                e != mc.player && e.isAlive() && !e.isSpectator());

        if (targets.isEmpty()) return;

        LivingEntity target = targets.stream()
                .min(Comparator.comparing(e -> e.squaredDistanceTo(mc.player)))
                .orElse(null);

        if (target == null) return;

        aimAt(target);

        // Attack as fast as possible
        if (mc.interactionManager.getAttackCooldown() == 0) {
            mc.interactionManager.attackEntity(mc.player, target);
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }

    private void aimAt(LivingEntity target) {
        Vec3d eye = mc.player.getEyePos();
        Vec3d pos = target.getBoundingBox().getCenter();

        double dx = pos.x - eye.x;
        double dy = pos.y - eye.y;
        double dz = pos.z - eye.z;

        float yaw = (float) Math.toDegrees(Math.atan2(dz, dx)) - 90f;
        float pitch = (float) -Math.toDegrees(Math.atan2(dy, Math.sqrt(dx * dx + dz * dz)));

        // add slight random jitter
        yaw += (float) ((Math.random() - 0.5) * randomness * 180);
        pitch += (float) ((Math.random() - 0.5) * randomness * 90);

        mc.player.setYaw(yaw);
        mc.player.setPitch(pitch);
    }
}