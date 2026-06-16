package com.argon.client.module.impl.combat;
import com.argon.client.module.Module;
public class Criticals extends Module {
    public Criticals() { super("Criticals", Category.COMBAT); }
    @Override public void onTick() {
        if (mc.player == null) return;
        if (mc.player.isOnGround()) mc.player.jump();
    }
}