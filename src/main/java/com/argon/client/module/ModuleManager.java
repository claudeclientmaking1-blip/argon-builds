package com.argon.client.module;
import com.argon.client.module.impl.combat.*;
import com.argon.client.module.impl.movement.*;
import com.argon.client.module.impl.player.*;
import com.argon.client.module.impl.render.*;
import java.util.ArrayList;
import java.util.List;
public class ModuleManager {
    private static final List<Module> modules = new ArrayList<>();
    public static void init() {
        modules.add(new AimAssist());
        modules.add(new Criticals());
        modules.add(new Reach());
        modules.add(new TriggerBot());
        modules.add(new Velocity());
        modules.add(new NoFall());
        modules.add(new Speed());
        modules.add(new Sprint());
        modules.add(new AutoClicker());
        modules.add(new ESP());
    }
    public static List<Module> getModules() { return modules; }
    public static Module getModule(String name) {
        return modules.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
    public static void onTick() { for (Module m : modules) { if (m.isEnabled()) m.onTick(); } }
}