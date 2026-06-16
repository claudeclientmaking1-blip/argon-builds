package com.argon.client.module.impl.combat;
import com.argon.client.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
public class KillAura extends Module {
    public KillAura() { super("KillAura", Category.COMBAT); }
    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null) return;
        if (mc.player.getAttackCooldownProgress(0) < 0.9f) return;
        Entity target = null;
        double closest = 4.5 * 4.5;
        for (Entity e : mc.world.getEntities()) {
            if (e == mc.player || !(e instanceof LivingEntity l)) continue;
            if (l.isDead() || l.getHealth() <= 0) continue;
            if (e instanceof PlayerEntity p && p.isCreative()) continue;
            double d = mc.player.squaredDistanceTo(e);
            if (d < closest) { closest = d; target = e; }
        }
        if (target != null) {
            mc.interactionManager.attackEntity(mc.player, target);
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }
}