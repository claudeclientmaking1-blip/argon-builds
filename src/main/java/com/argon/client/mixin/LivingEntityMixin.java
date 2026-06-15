package com.argon.client.mixin;

import com.argon.client.module.impl.render.ESP;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.ItemRenderer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.ItemRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.DiffuseLighting;
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
        if (!ESPEnabled()) return;
        MinecraftClient client = MinecraftClient.getInstance();
        new ESP().renderEntityBox(client, entity, client.getRenderTickCounter().getTickDelta(true));
    }

    private boolean ESPEnabled() {
        return com.argon.client.module.ModuleManager.getModules().stream()
                .anyMatch(m -> m.getName().equals("ESP") && m.isEnabled());
    }
}