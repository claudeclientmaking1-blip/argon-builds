package com.argon.client.module.impl.player;

import com.argon.client.module.Module;
import net.minecraft.util.Hand;

public class AutoClicker extends Module {

    private int tickCounter = 0;
    private final int clicksPerSecond = 12;

    public AutoClicker() {
        super("AutoClicker", "Automatically clicks when holding mouse");
    }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (mc.options.useKey.isPressed()) {
            tickCounter++;
            if (tickCounter >= 20 / clicksPerSecond) {
                mc.interactionManager.attackEntity(mc.player, mc.targetedEntity);
                mc.player.swingHand(Hand.MAIN_HAND);
                tickCounter = 0;
            }
        } else {
            tickCounter = 0;
        }
    }
}