package eu.projnull.spelis.valvesprint.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;

public class ValveSprintClient implements ClientModInitializer {
    private final KeyBinding walkBind = KeyBindingHelper.registerKeyBinding(KeybindCompat.createKeybind());

    @Override
    public void onInitializeClient() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                if (client.player.input.hasForwardMovement()) {
                    client.player.setSprinting(!walkBind.isPressed());
                }
            }
        });
    }
}
