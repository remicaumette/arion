package fr.faygwenn.practice.utils;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.kits.Kit;
import fr.faygwenn.practice.objects.Ench;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Utils {
    public static final String serializeItem(ItemStack item) {
        if (item == null)
            return "null";

        String isType = String.valueOf(item.getType().toString());
        Map<Enchantment, Integer> isEnch = item.getEnchantments();
        String serializedItemStack = "t@" + isType;

        if (item.getDurability() != 0) {
            String isDurability = String.valueOf(item.getDurability());
            serializedItemStack += ":d@" + isDurability;
        }

        if (item.getAmount() != 1) {
            String isAmount = String.valueOf(item.getAmount());
            serializedItemStack += ":a@" + isAmount;
        }

        if (isEnch.size() > 0) {
            for (Entry<Enchantment, Integer> ench : isEnch.entrySet())
                serializedItemStack += ":e@" + ench.getKey().getName() + "@" + ench.getValue();
        }

        return serializedItemStack;
    }

    public static final ItemStack unserializeItem(String serialized) {
        ItemStack item = null;
        String[] serializedItemStack = serialized.split(":");
        boolean createdItemStack = false;

        for (String itemInfo : serializedItemStack) {
            String[] itemAttribute = itemInfo.split("@");

            if (itemAttribute[0].equals("t")) {
                item = new ItemStack(Material.getMaterial(itemAttribute[1]));
                createdItemStack = true;
            } else if (itemAttribute[0].equals("d") && createdItemStack)
                item.setDurability(Short.valueOf(itemAttribute[1]));
            else if (itemAttribute[0].equals("a") && createdItemStack)
                item.setAmount(Integer.valueOf(itemAttribute[1]));
            else if (itemAttribute[0].equals("e") && createdItemStack)
                item.addEnchantment(Enchantment.getByName(itemAttribute[1]), Integer.valueOf(itemAttribute[2]));
        }

        return item;
    }

    @SuppressWarnings("deprecation")
    public static final ItemStack createItem(Material mat, int data, String text, List<String> lore, Ench... enchants) {
        ItemStack item = new ItemStack(mat, 1, (short) 0, (byte) data);
        ItemMeta meta = item.getItemMeta();

        if (text != null)
            meta.setDisplayName(text);

        meta.setLore(lore);

        for (Ench ench : enchants) {
            item.addEnchantment(ench.ench, ench.level);
            meta.addEnchant(ench.ench, ench.level, false);
        }

        item.setItemMeta(meta);
        return item;
    }

    public static final ItemStack createItem(PotionType type, int level, boolean splash, boolean extended, String text, List<String> lore) {
        Potion pot = new Potion(type, level);
        pot.setSplash(splash);

        if (extended)
            pot.setHasExtendedDuration(true);

        ItemStack item = pot.toItemStack(1);
        ItemMeta meta = item.getItemMeta();

        if (text != null)
            meta.setDisplayName(text);

        if (lore != null)
            meta.setLore(lore);

        item.setItemMeta(meta);
        return item;
    }

    public static final ItemStack createItem(PotionType type, int level, boolean splash, boolean extended) {
        Potion pot = new Potion(type, level);
        pot.setSplash(splash);

        if (extended)
            pot.setHasExtendedDuration(true);

        return pot.toItemStack(1);
    }

    public static final int getElos(Player player, Kit kit) {
        return Practice.i.database.get().getInt("elos." + player.getUniqueId().toString() + "." + kit.toString());
    }

    public static final void addElos(Player player, int elos, Kit kit) {
        String uuid = player.getUniqueId().toString();
        Practice.i.database.set("elos." + uuid + "." + kit.toString(), Practice.i.database.get().getInt("elos." + uuid + "." + kit.toString()) + elos);
    }

    public static final Location getSpawnLocation() {
        if (Practice.i.database.get().contains("spawn"))
            return unserializeLocation(Practice.i.database.get().getString("spawn")).add(0.5D, 0.0, 0.5D);
        else
            return Bukkit.getWorlds().get(0).getSpawnLocation();
    }

    public static final String getArenaPath(String name) {
        return ChatColor.stripColor(name.toLowerCase());
    }

    public static final String getArenaName(String path) {
        return ChatColor.translateAlternateColorCodes('&', Practice.i.database.get().getString("arenas." + path + ".name"));
    }

    public static final Location getArenaLocation(String path, int point) {
        return unserializeLocation(Practice.i.database.get().getString("arenas." + path + ".location-" + point));
    }

    public static final String serializeLocation(Location location) {
        if (location == null)
            return null;

        return location.getWorld().getName() + "|" + location.getBlockX() + "|" + location.getBlockY() + "|" + location.getBlockZ() + "|" + location.getYaw() + "|" + location.getPitch();
    }

    public static final Location unserializeLocation(String string) {
        if (string == null)
            return null;

        World world = Bukkit.getWorld(string.split("\\|")[0]);
        double x = Double.parseDouble(string.split("\\|")[1]);
        double y = Double.parseDouble(string.split("\\|")[2]);
        double z = Double.parseDouble(string.split("\\|")[3]);
        float yaw = Float.parseFloat(string.split("\\|")[4]);
        float pitch = Float.parseFloat(string.split("\\|")[5]);

        return new Location(world, x, y, z, yaw, pitch);
    }

    public static String formatDuration(int seconds) {
        if (seconds < 60)
            return formatDurationS(seconds);
        else if (seconds < 3600)
            return formatDurationMS(seconds);
        else
            return formatDurationHMS(seconds);
    }

    private static String formatDurationS(int seconds) {
        seconds = seconds % 60;

        return twoDigitString(seconds) + "s";
    }

    private static String formatDurationMS(int seconds) {
        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;

        return minutes + "m" + twoDigitString(seconds) + "s";
    }

    private static String formatDurationHMS(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;

        return hours + "h" + twoDigitString(minutes) + "m" + twoDigitString(seconds) + "s";
    }

    private static String twoDigitString(int number) {
        if (number == 0)
            return "00";

        if (number / 10 == 0)
            return "0" + number;

        return String.valueOf(number);
    }
}
