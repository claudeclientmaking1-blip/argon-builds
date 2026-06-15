package com.argon.client.module.impl.render;

import com.argon.client.module.Module;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class ESP extends Module {

    public ESP() {
        super("ESP", "Renders boxes around players");
    }

    @Override
    public void onTick() {
        // Rendering is done via a mixin on WorldRenderEvents – placeholder here.
    }
}