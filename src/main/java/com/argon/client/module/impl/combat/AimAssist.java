package com.argon.client.module.impl.combat;
import com.argon.client.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
public class AimAssist extends Module {
    public AimAssist() { super("AimAssist", Category.COMBAT); }
    @Override public void onTick() {
        if (mc.player == null || mc.world == null) return;
        Entity target = null; double closest = 6.0 * 6.0;
        for (Entity e : mc.world.getEntities()) {
            if (e == mc.player || !(e instanceof LivingEntity l) || l.isDead()) continue;
            double d = mc.player.squaredDistanceTo(e);
            if (d < closest) { closest = d; target = e; }
        }
        if (target == null) return;
        Vec3d diff = target.getPos().add(0, target.getHeight()/2, 0).subtract(mc.player.getEyePos());
        double dist = Math.sqrt(diff.x*diff.x + diff.z*diff.z);
        float yaw   = (float) Math.toDegrees(Math.atan2(-diff.x, diff.z));
        float pitch = (float) Math.toDegrees(Math.atan2(-diff.y, dist));
        float dy = yaw - mc.player.getYaw(); float dp = pitch - mc.player.getPitch();
        while (dy > 180) dy -= 360; while (dy < -180) dy += 360;
        mc.player.setYaw(mc.player.getYaw()   + Math.min(Math.abs(dy), 2.5f) * Math.signum(dy));
        mc.player.setPitch(mc.player.getPitch() + Math.min(Math.abs(dp), 2.5f) * Math.signum(dp));
    }
}