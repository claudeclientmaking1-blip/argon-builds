package com.argon.client.module.impl.combat;

import com.argon.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;

import java.util.List;

public class KillAura extends Module {

    private static final double RANGE = 5.0;

    public KillAura() {
        super("KillAura");
    }

    @Override
    protected void onEnable() {}

    @Override
    protected void onDisable() {}

    @Override
    public void onTick(MinecraftClient client) {
        PlayerEntity player = client.player;
        if (player == null || client.world == null) return;

        List<LivingEntity> targets = client.world.getEntitiesByClass(
                LivingEntity.class,
                player.getBoundingBox().expand(RANGE),
                e -> e != player && e.isAlive()
        );

        if (!targets.isEmpty()) {
            LivingEntity target = targets.get(0);
            // Rotate to target
            double dx = target.getX() - player.getX();
            double dy = target.getEyeY() - player.getEyeY();
            double dz = target.getZ() - player.getZ();
            double dist = Math.sqrt(dx * dx + dz * dz);
            float yaw = (float) Math.toDegrees(Math.atan2(-dx, dz));
            float pitch = (float) Math.toDegrees(-Math.atan2(dy, dist));
            player.setYaw(yaw);
            player.setPitch(pitch);

            // Attack
            client.interactionManager.attackEntity(player, target);
        }
    }
}