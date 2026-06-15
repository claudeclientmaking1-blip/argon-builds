package com.argon.client.module.impl.combat;

import com.argon.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;

import java.util.List;

public class Criticals extends Module {

    private static final double RANGE = 4.0;

    public Criticals() {
        super("Criticals");
    }

    @Override
    protected void onEnable() {}

    @Override
    protected void onDisable() {}

    @Override
    public void onTick(MinecraftClient client) {
        PlayerEntity player = client.player;
        if (player == null || client.world == null) return;
        if (!player.isOnGround()) return; // only when on ground to trigger a jump for crit

        List<LivingEntity> targets = client.world.getEntitiesByClass(
                LivingEntity.class,
                player.getBoundingBox().expand(RANGE),
                e -> e != player && e.isAlive()
        );

        if (!targets.isEmpty()) {
            // Simple "critical" by making the player jump just before attack
            player.jump();
        }
    }
}