package com.argon.client.module.impl.render;

import com.argon.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.VertexConsumerProvider.Immediate;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

/**
 * Very simple ESP that draws a colored box around living entities.
 */
public class ESP extends Module {

    public ESP() {
        super("ESP", "Draw boxes around entities");
    }

    public void renderEntityBox(MinecraftClient client, LivingEntity entity, float tickDelta) {
        if (!isEnabled()) return;

        MatrixStack matrices = new MatrixStack();
        matrices.push();
        Vec3d camPos = client.gameRenderer.getCamera().getPos();
        matrices.translate(entity.getX() - camPos.x,
                entity.getY() - camPos.y,
                entity.getZ() - camPos.z);

        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.disableDepthTest();
        RenderSystem.disableCull();

        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(VertexFormat.DrawMode.LINES, VertexFormats.POSITION_COLOR);

        float r = 1f, g = 0f, b = 0f, a = 0.8f;

        // box corners
        float x1 = -entity.getWidth() / 2f;
        float x2 = entity.getWidth() / 2f;
        float y1 = 0f;
        float y2 = entity.getHeight();
        float z1 = -entity.getWidth() / 2f;
        float z2 = entity.getWidth() / 2f;

        // vertical edges
        addLine(buffer, x1, y1, z1, x1, y2, z1, r, g, b, a);
        addLine(buffer, x1, y1, z2, x1, y2, z2, r, g, b, a);
        addLine(buffer, x2, y1, z1, x2, y2, z1, r, g, b, a);
        addLine(buffer, x2, y1, z2, x2, y2, z2, r, g, b, a);
        // top rectangle
        addLine(buffer, x1, y2, z1, x2, y2, z1, r, g, b, a);
        addLine(buffer, x2, y2, z1, x2, y2, z2, r, g, b, a);
        addLine(buffer, x2, y2, z2, x1, y2, z2, r, g, b, a);
        addLine(buffer, x1, y2, z2, x1, y2, z1, r, g, b, a);
        // bottom rectangle
        addLine(buffer, x1, y1, z1, x2, y1, z1, r, g, b, a);
        addLine(buffer, x2, y1, z1, x2, y1, z2, r, g, b, a);
        addLine(buffer, x2, y1, z2, x1, y1, z2, r, g, b, a);
        addLine(buffer, x1, y1, z2, x1, y1, z1, r, g, b, a);

        BufferRenderer.drawWithShader(buffer.end());

        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();

        matrices.pop();
    }

    private void addLine(BufferBuilder buffer,
                         float x1, float y1, float z1,
                         float x2, float y2, float z2,
                         float r, float g, float b, float a) {
        buffer.vertex(x1, y1, z1).color(r, g, b, a).next();
        buffer.vertex(x2, y2, z2).color(r, g, b, a).next();
    }

    @Override
    public void onTick() {
        // No per‑tick logic needed; rendering is handled elsewhere.
    }
}