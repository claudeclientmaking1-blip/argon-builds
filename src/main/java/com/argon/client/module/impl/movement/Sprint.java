package com.argon.client.module.impl.movement;
import com.argon.client.module.Module;
public class Sprint extends Module {
    public Sprint() { super("Sprint", Category.MOVEMENT); }
    @Override public void onTick() {
        if (mc.player == null) return;
        if (mc.player.forwardSpeed > 0) mc.player.setSprinting(true);
    }
}