package com.argon.client.module.impl.player;

import com.argon.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Hand;

public class AutoClicker extends Module {

    private long lastClick = 0;
    private static final long INTERVAL_MS = 50; // 20 CPS

    public AutoClicker() {
        super("AutoClicker");
    }

    @Override
    protected void onEnable() {}

    @Override
    protected void onDisable() {}

    @Override
    public void onTick(MinecraftClient client) {
        if (client.player == null || client.interactionManager == null) return;

        long now = System.currentTimeMillis();
        if (now - lastClick >= INTERVAL_MS) {
            client.interactionManager.attackBlock(client.player.getBlockPos(), client.player.getHorizontalFacing());
            client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);
            lastClick = now;
        }
    }
}