package com.argon.client.module;

import com.argon.client.module.impl.combat.KillAura;
import com.argon.client.module.impl.combat.DoomsdayAim;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public void register(Module module) {
        modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }

    public void registerAllDefaults() {
        // Aim‑assist modules
        register(new KillAura());
        register(new DoomsdayAim());

        // Stubs for the rest (you can replace with real implementations later)
        // register(new Velocity());
        // register(new Reach());
        // register(new Criticals());
        // register(new Speed());
        // register(new NoFall());
        // register(new Sprint());
        // register(new AutoClicker());
        // register(new ESP());
    }

    // Called each client tick
    public void onTick() {
        for (Module m : modules) {
            m.onTick();
        }
    }
}