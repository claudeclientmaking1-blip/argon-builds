package com.argon.client.mixin;

import com.argon.client.module.impl.combat.KillAura;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Intercept entity interaction for KillAura.
 */
@Mixin(Entity.class)
public abstract class EntityMixin {

    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    private void onInteract(net.minecraft.util.Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;
        if (KillAura.INSTANCE.isEnabled()) {
            // Prevent normal interaction when Aura is active
            cir.setReturnValue(ActionResult.FAIL);
        }
    }
}