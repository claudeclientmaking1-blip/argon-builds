package com.argon.client.module.impl.combat;

import com.argon.client.module.Module;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;

/**
 * Doomsday style aimbot – predicts the target's yaw/pitch a few ticks ahead.
 */
public class DoomsdayAim extends Module {

    private static final double RANGE = 5.0;
    private static final int PREDICTION_TICKS = 3;

    public DoomsdayAim() {
        super("DoomsdayAim", "Prediction based aim assist");
    }

    @Override
    protected void onUpdate() {
        ClientPlayerEntity player = mc().player;
        if (player == null) return;

        LivingEntity target = findTarget();
        if (target != null) {
            double[] predPos = predictPosition(target);
            facePosition(predPos[0], predPos[1], predPos[2], player);
            player.swingHand(Hand.MAIN_HAND);
            player.interact(target, Hand.MAIN_HAND);
        }
    }

    private LivingEntity findTarget() {
        double closest = RANGE;
        LivingEntity best = null;
        for (LivingEntity e : mc().world.getEntitiesByClass(LivingEntity.class, player -> true)) {
            if (e == mc().player || e.isSpectator() || !e.isAlive()) continue;
            double dist = mc().player.squaredDistanceTo(e);
            if (dist < closest * closest) {
                closest = Math.sqrt(dist);
                best = e;
            }
        }
        return best;
    }

    private double[] predictPosition(LivingEntity target) {
        double dx = target.getVelocity().x * PREDICTION_TICKS;
        double dy = target.getVelocity().y * PREDICTION_TICKS;
        double dz = target.getVelocity().z * PREDICTION_TICKS;
        return new double[]{
                target.getX() + dx,
                target.getY() + dy,
                target.getZ() + dz
        };
    }

    private void facePosition(double x, double y, double z, ClientPlayerEntity player) {
        double diffX = x - player.getX();
        double diffY = y - (player.getY() + player.getEyeHeight(player.getPose()));
        double diffZ = z - player.getZ();

        double dist = MathHelper.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = (float) (MathHelper.atan2(diffZ, diffX) * (180F / Math.PI)) - 90.0F;
        float pitch = (float) -(MathHelper.atan2(diffY, dist) * (180F / Math.PI));

        player.setYaw(yaw);
        player.setPitch(pitch);
    }
}