package com.argon.client.module.impl.combat;
import com.argon.client.module.Module;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
public class TriggerBot extends Module {
    private int cooldown = 0;
    public TriggerBot() { super("TriggerBot", Category.COMBAT); }
    @Override public void onTick() {
        if (mc.player == null) return;
        if (cooldown > 0) { cooldown--; return; }
        if (!(mc.targetedEntity instanceof LivingEntity target)) return;
        if (target.isDead() || target.getHealth() <= 0) return;
        if (mc.player.getAttackCooldownProgress(0) < 0.9f) return;
        mc.interactionManager.attackEntity(mc.player, target);
        mc.player.swingHand(Hand.MAIN_HAND);
        cooldown = 5;
    }
}