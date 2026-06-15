package com.argon.client.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Placeholder mixin – real implementations could modify entity behavior.
 */
@Mixin(Entity.class)
public abstract class EntityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTickHead(CallbackInfo ci) {
        // No-op for now
    }
}