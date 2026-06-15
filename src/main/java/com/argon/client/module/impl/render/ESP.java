package com.argon.client.module.impl.render;

import com.argon.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class ESP extends Module {

    public ESP() {
        super("ESP");
    }

    @Override
    protected void onEnable() {}

    @Override
    protected void onDisable() {}

    @Override
    public void onTick(MinecraftClient client) {
        // Render is handled via mixin (LivingEntityMixin) calling this method
    }

    public void renderEntityBox(MinecraftClient client, LivingEntity entity, float tickDelta) {
        if (!entity.isAlive()) return;

        Box box = entity.getBoundingBox();
        Vec3d camPos = client.gameRenderer.getCamera().getPos();

        MatrixStack ms = new MatrixStack();
        ms.translate(-camPos.x, -camPos.y, -camPos.z);
        RenderSystem.setShader(GameRenderer::getPositionShader);
        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        RenderSystem.disableDepthTest();
        RenderSystem.disableCull();
        buffer.begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);

        // 8 corners of the box
        double x1 = box.minX;
        double y1 = box.minY;
        double z1 = box.minZ;
        double x2 = box.maxX;
        double y2 = box.maxY;
        double z2 = box.maxZ;
        float r = 0f, g = 1f, b = 0f, a = 1f;

        // Bottom rectangle
        addLine(buffer, x1, y1, z1, x2, y1, z1, r, g, b, a);
        addLine(buffer, x2, y1, z1, x2, y1, z2, r, g, b, a);
        addLine(buffer, x2, y1, z2, x1, y1, z2, r, g, b, a);
        addLine(buffer, x1, y1, z2, x1, y1, z1, r, g, b, a);
        // Top rectangle
        addLine(buffer, x1, y2, z1, x2, y2, z1, r, g, b, a);
        addLine(buffer, x2, y2, z1, x2, y2, z2, r, g, b, a);
        addLine(buffer, x2, y2, z2, x1, y2, z2, r, g, b, a);
        addLine(buffer, x1, y2, z2, x1, y2, z1, r, g, b, a);
        // Verticals
        addLine(buffer, x1, y1, z1, x1, y2, z1, r, g, b, a);
        addLine(buffer, x2, y1, z1, x2, y2, z1, r, g, b, a);
        addLine(buffer, x2, y1, z2, x2, y2, z2, r, g, b, a);
        addLine(buffer, x1, y1, z2, x1, y2, z2, r, g, b, a);

        BufferRenderer.drawWithShader(buffer.end());
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
    }

    private void addLine(BufferBuilder buffer, double x1, double y1, double z1,
                         double x2, double y2, double z2,
                         float r, float g, float b, float a) {
        buffer.vertex(x1, y1, z1).color(r, g, b, a).next();
        buffer.vertex(x2, y2, z2).color(r, g, b, a).next();
    }
}