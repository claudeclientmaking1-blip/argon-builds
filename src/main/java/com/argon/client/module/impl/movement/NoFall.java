package com.argon.client.module.impl.movement;

import com.argon.client.module.Module;

public class NoFall extends Module {

    public NoFall() {
        super("NoFall", "Prevents fall damage");
    }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (mc.player.fallDistance > 2.0f) {
            mc.player.fallDistance = 0.0f;
        }
    }
}