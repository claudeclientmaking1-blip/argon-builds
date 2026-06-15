package com.argon.client.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Example mixin that could modify movement vectors.
 */
@Mixin(ClientPlayerEntity.class)
public abstract class PlayerMixin {

    @Redirect(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;length()D"))
    private double redirectLength(Vec3d vec) {
        // No modification – placeholder
        return vec.length();
    }
}