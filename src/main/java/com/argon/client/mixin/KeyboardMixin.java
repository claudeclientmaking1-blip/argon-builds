package com.argon.client.mixin;
import com.argon.client.gui.ClickGui;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(method="onKey",at=@At("HEAD"))
    private void onKey(long window,int key,int scancode,int action,int mods,CallbackInfo ci){
        if(key==GLFW.GLFW_KEY_RIGHT_SHIFT&&action==GLFW.GLFW_PRESS){
            MinecraftClient mc=MinecraftClient.getInstance();
            if(mc.currentScreen==null)mc.setScreen(new ClickGui());
        }
    }
}