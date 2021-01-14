package asher.tyramusweapons.Main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public final class ItemNBT {

    public static ItemStack setNBTTag(final ItemStack itemStack, final String key, final String value) {
        if (itemStack == null || itemStack.getType() == Material.AIR) return itemStack;

        Object nmsItemStack = asNMSCopy(itemStack);

        Object itemCompound = hasTag(nmsItemStack) ? getTag(nmsItemStack) : newNBTTagCompound();

        setString(itemCompound, key, value);
        setTag(nmsItemStack, itemCompound);

        return asBukkitCopy(nmsItemStack);
    }

    public static String getNBTTag(final ItemStack itemStack, final String key) {
        if (itemStack == null || itemStack.getType() == Material.AIR) return "";

        Object nmsItemStack = asNMSCopy(itemStack);
        Object itemCompound = hasTag(nmsItemStack) ? getTag(nmsItemStack) : newNBTTagCompound();

        return getString(itemCompound, key);
    }

    private static void setString(final Object itemCompound, final String key, final String value) {
        try {
            Objects.requireNonNull(getNMSClass("NBTTagCompound")).getMethod("setString", String.class, String.class).invoke(itemCompound, key, value);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
        }
    }

    private static String getString(final Object itemCompound, final String key) {
        try {
            return (String) Objects.requireNonNull(getNMSClass("NBTTagCompound")).getMethod("getString", String.class).invoke(itemCompound, key);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    private static boolean hasTag(final Object nmsItemStack) {
        try {
            return (boolean) Objects.requireNonNull(getNMSClass("ItemStack")).getMethod("hasTag").invoke(nmsItemStack);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return false;
        }
    }

    private static Object getTag(final Object nmsItemStack) {
        try {
            return Objects.requireNonNull(getNMSClass("ItemStack")).getMethod("getTag").invoke(nmsItemStack);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    private static void setTag(final Object nmsItemStack, final Object itemCompound) {
        try {
            Objects.requireNonNull(getNMSClass("ItemStack")).getMethod("setTag", getNMSClass("NBTTagCompound")).invoke(nmsItemStack, itemCompound);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
        }
    }

    private static Object newNBTTagCompound() {
        try {
            return Objects.requireNonNull(getNMSClass("NBTTagCompound")).newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        }
    }

    private static Object asNMSCopy(final ItemStack itemStack) {
        try {
            return Objects.requireNonNull(getCraftItemStackClass()).getMethod("asNMSCopy", ItemStack.class).invoke(null, itemStack);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    private static ItemStack asBukkitCopy(final Object nmsItemStack) {
        try {
            return (ItemStack) Objects.requireNonNull(getCraftItemStackClass()).getMethod("asBukkitCopy", getNMSClass("ItemStack")).invoke(null, nmsItemStack);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    private static String getServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().substring(Bukkit.getServer().getClass().getPackage().getName().lastIndexOf('.') + 1);
    }

    private static Class<?> getNMSClass(final String className) {
        try {
            return Class.forName("net.minecraft.server." + getServerVersion() + "." + className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private static Class<?> getCraftItemStackClass() {
        try {
            return Class.forName("org.bukkit.craftbukkit." + getServerVersion() + ".inventory.CraftItemStack");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

}