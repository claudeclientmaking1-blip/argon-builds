package com.argon.client;
import com.argon.client.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ArgonClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("argon");
    @Override
    public void onInitializeClient() {
        LOGGER.info("Argon Client loading...");
        ModuleManager.init();
        ClientTickEvents.END_CLIENT_TICK.register(c -> ModuleManager.onTick());
        LOGGER.info("Argon Client loaded!");
    }
}