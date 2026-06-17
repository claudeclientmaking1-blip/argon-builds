```java
package com.argonmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.server.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ArgonPvPOptimiser implements ModInitializer {

    private Entity lastHitEntity;

    @Override
    public void onInitialize() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (PlayerEntity player : server.getPlayers()) {
                if (player.getLastAttackedEntity() != null && player.getLastAttackedEntity().getType() == EntityType.PLAYER) {
                    lastHitEntity = player.getLastAttackedEntity();
                }

                if (lastHitEntity != null && lastHitEntity.isRemoved()) {
                    lastHitEntity = null;
                }

                boolean isAimingAtLastHit = isAimingAtEntity(player, lastHitEntity);

                if (lastHitEntity != null && isAimingAtLastHit) {
                    player.getAttributeInstance(net.minecraft.entity.attribute.EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1 * 40);
                    player-camera-sensitivity-is-set-to-40-automatically-in-this-case;
                } else if (lastHitEntity != null) {
                    player.getAttributeInstance(net.minecraft.entity.attribute.EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1 * 180);
                    player-camera-sensitivity-is-set-to-180-automatically-in-this-case;
                }
            }
        });
    }

    private boolean isAimingAtEntity(PlayerEntity player, Entity target) {
        if (target == null || !(target instanceof PlayerEntity)) {
            return false;
        }

        double playerPosX = player.getX();
        double playerPosZ = player.getZ();
        double targetPosX = target.getX();
        double targetPosZ = target.getZ();

        double viewVecX = targetPosX - playerPosX;
        double viewVecZ = targetPosZ - playerPosZ;

        double rotationYaw = player.bodyYaw;
        double rotationPitch = player.pitch;

        double lookVecX = MathHelper.cos(-rotationYaw * 0.017453292F - (float)Math.PI);
        double lookVecZ = MathHelper.sin(-rotationYaw * 0.017453292F - (float)Math.PI);

        double dotProduct = (viewVecX * lookVecX) + (viewVecZ * lookVecZ);
        double magnitudeViewVec = Math.sqrt((viewVecX * viewVecX) + (viewVecZ * viewVecZ));
        double magnitudeLookVec = Math.sqrt((lookVecX * lookVecX) + (lookVecZ * lookVecZ));

        double degrees = Math.toDegrees(Math.acos(dotProduct / (magnitudeViewVec * magnitudeLookVec)));

        return degrees < 10;
    }
}
```