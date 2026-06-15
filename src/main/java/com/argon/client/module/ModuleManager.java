package com.argon.client.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Simple registry for modules.
 */
public class ModuleManager {

    private static final List<Module> MODULES = new ArrayList<>();

    public static void register(Module module) {
        MODULES.add(module);
    }

    public static List<Module> getModules() {
        return Collections.unmodifiableList(MODULES);
    }
}