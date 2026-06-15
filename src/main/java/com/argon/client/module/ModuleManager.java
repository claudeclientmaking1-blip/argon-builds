package com.argon.client.module;

import com.argon.client.module.impl.combat.KillAura;
import com.argon.client.module.impl.combat.DoomsdayAim;
import com.argon.client.module.impl.combat.Velocity;
import com.argon.client.module.impl.combat.Reach;
import com.argon.client.module.impl.combat.Criticals;
import com.argon.client.module.impl.movement.Speed;
import com.argon.client.module.impl.movement.NoFall;
import com.argon.client.module.impl.movement.Sprint;
import com.argon.client.module.impl.player.AutoClicker;
import com.argon.client.module.impl.render.ESP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModuleManager {

    private static final List<Module> modules = new ArrayList<>();

    public static void init() {
        // Register all modules
        register(new KillAura());
        register(new DoomsdayAim());
        register(new Velocity());
        register(new Reach());
        register(new Criticals());
        register(new Speed());
        register(new NoFall());
        register(new Sprint());
        register(new AutoClicker());
        register(new ESP());
    }

    private static void register(Module module) {
        modules.add(module);
    }

    public static List<Module> getModules() {
        return Collections.unmodifiableList(modules);
    }

    public static Module getByName(String name) {
        return modules.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}