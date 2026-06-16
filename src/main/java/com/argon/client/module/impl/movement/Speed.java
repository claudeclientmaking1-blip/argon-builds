package com.argon.client.module.impl.movement;
import com.argon.client.module.Module;
import net.minecraft.entity.attribute.EntityAttributes;
public class Speed extends Module {
    public Speed() { super("Speed", Category.MOVEMENT); }
    @Override public void onEnable() {
        if (mc.player == null) return;
        var i = mc.player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if (i != null) i.setBaseValue(0.2);
    }
    @Override public void onDisable() {
        if (mc.player == null) return;
        var i = mc.player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if (i != null) i.setBaseValue(0.1);
    }
}