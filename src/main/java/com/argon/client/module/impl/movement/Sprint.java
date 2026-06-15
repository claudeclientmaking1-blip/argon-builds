package com.argon.client.module.impl.movement;

import com.argon.client.module.Module;

public class Sprint extends Module {

    public Sprint() {
        super("Sprint", "Automatically starts sprinting");
    }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (!mc.player.isSprinting() && mc.player.input.movementForward > 0.8f) {
            mc.player.setSprinting(true);
        }
    }
}