package com.argon.client.mixin;
import com.argon.client.module.ModuleManager;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(ClientPlayerEntity.class)
public class VelocityMixin {
    @Inject(method="pushOutOfBlocks",at=@At("HEAD"),cancellable=true)
    private void onPush(double x,double z,CallbackInfo ci){
        var m=ModuleManager.getModule("Velocity");
        if(m!=null&&m.isEnabled())ci.cancel();
    }
}