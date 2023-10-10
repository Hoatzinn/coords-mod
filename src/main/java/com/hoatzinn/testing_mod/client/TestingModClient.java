package com.hoatzinn.testing_mod.client;

/*import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import org.lwjgl.glfw.GLFW;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;*/

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;
import net.minecraft.text.Text;



public class TestingModClient implements ClientModInitializer {
    private static KeyBinding showLocationKey;

    @Override
    public void onInitializeClient() {
        showLocationKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.show_location",
                GLFW.GLFW_KEY_Y, // You can change this key code to any other key
                "category.gaming" // Customize the category
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (showLocationKey.wasPressed()) {
                // Get player's coordinates
                double x = client.player.getX();
                double y = client.player.getY();
                double z = client.player.getZ();

                // Send location in chat
                client.player.sendMessage(Text.literal("Your coordinates: X=" + x + ", Y=" + y + ", Z=" + z), false);
            }
        });
    }
}