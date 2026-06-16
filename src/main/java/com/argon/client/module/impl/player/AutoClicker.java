package com.argon.client.module.impl.player;
import com.argon.client.module.Module;
import net.minecraft.util.Hand;
public class AutoClicker extends Module {
    private int tick = 0;
    public AutoClicker() { super("AutoClicker", Category.PLAYER); }
    @Override public void onTick() {
        if (mc.player == null || mc.targetedEntity == null) return;
        if (tick++ >= 5) {
            tick = 0;
            mc.interactionManager.attackEntity(mc.player, mc.targetedEntity);
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }
}