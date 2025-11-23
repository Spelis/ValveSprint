package eu.projnull.spelis.valvesprint.client;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.lang.reflect.Constructor;

public class KeybindCompat {

    public static KeyBinding createKeybind() {
        String id = "key.valvesprint.walk";
        int glfwKey = GLFW.GLFW_KEY_LEFT_SHIFT;
        try {
            Constructor<KeyBinding> newest = KeyBinding.class.getConstructor(String.class, InputUtil.Type.class, int.class, KeyBinding.Category.class);
            return newest.newInstance(id, InputUtil.Type.KEYSYM, glfwKey, KeyBinding.Category.MOVEMENT);
        } catch (NoSuchMethodException | NoClassDefFoundError e1) {
            try {
                Constructor<KeyBinding> old = KeyBinding.class.getConstructor(String.class, InputUtil.Type.class, int.class, String.class);
                return old.newInstance(id, InputUtil.Type.KEYSYM, glfwKey, "key.categories.movement");
            } catch (Exception e2) {
                throw new RuntimeException("Cannot create keybind: unsupported MC version", e2);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Failed to create keybind", ex);
        }
    }

}
