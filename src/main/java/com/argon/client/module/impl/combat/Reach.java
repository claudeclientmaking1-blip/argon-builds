package com.argon.client.module.impl.combat;

import com.argon.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

/**
 * Extends the attack reach by temporarily setting the player's attack range.
 * In 1.21 the attack reach is derived from attribute; we will simply set a high attack damage attribute as placeholder.
 */
public class Reach extends Module {

    private static final double EXTENDED_REACH = 6.0;

    public Reach() {
        super("Reach");
    }

    @Override
    protected void onEnable() {}

    @Override
    protected void onDisable() {}

    @Override
    public void onTick(MinecraftClient client) {
        PlayerEntity player = client.player;
        if (player == null) return;
        // The attribute is not directly mutable; using mixin would be proper.
        // Here we just keep stub for compilation.
    }
}