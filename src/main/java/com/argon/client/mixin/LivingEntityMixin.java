package com.argon.client.mixin;

import com.argon.client.module.impl.render.ESP;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Render ESP boxes around living entities when ESP module is enabled.
 */
@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityMixin {

    @Inject(method = "render", at = @At("HEAD"))
    private void beforeRender(LivingEntity entity, float limbAngle, float limbDistance,
                              MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                              int light, CallbackInfo ci) {
        if (!isEspEnabled()) return;
        MinecraftClient client = MinecraftClient.getInstance();
        new ESP().renderEntityBox(client, entity, client.getRenderTickCounter().getTickDelta(true));
    }

    private boolean isEspEnabled() {
        return com.argon.client.module.ModuleManager.getModules().stream()
                .anyMatch(m -> m.getName().equals("ESP") && m.isEnabled());
    }
}