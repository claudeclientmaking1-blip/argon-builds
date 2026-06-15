package com.argon.client.module;

import com.argon.client.module.impl.combat.*;
import com.argon.client.module.impl.movement.*;
import com.argon.client.module.impl.player.AutoClicker;
import com.argon.client.module.impl.render.ESP;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private static final List<Module> modules = new ArrayList<>();

    public static void init() {
        // Combat
        modules.add(new KillAura());
        modules.add(new Velocity());
        modules.add(new Reach());
        modules.add(new Criticals());

        // Movement
        modules.add(new Speed());
        modules.add(new NoFall());
        modules.add(new Sprint());

        // Player
        modules.add(new AutoClicker());

        // Render
        modules.add(new ESP());
    }

    public static List<Module> getModules() {
        return modules;
    }

    public static void tickAll(net.minecraft.client.MinecraftClient client) {
        for (Module m : modules) {
            if (m.isEnabled()) {
                m.onTick(client);
            }
        }
    }
}