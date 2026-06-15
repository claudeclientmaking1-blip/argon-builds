package com.argon.client.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Cancels knockback for the Velocity module (very simple demonstration).
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "applyDamage", at = @At("HEAD"))
    private void onApplyDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        // In a real mod we would check if the Velocity module is enabled.
    }
}