package fr.faygwenn.practice.kit;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.object.Ench;
import fr.faygwenn.practice.util.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;

public enum Kit {
    NODEBUFF("Nodebuff"),
    DEBUFF("Debuff"),
    G_APPLE("GApple"),
    BUILD_UHC("BuildUHC"),
    SUMO("Sumo");

    public final String name;

    private Kit(String name) {
        this.name = name;
    }

    @SuppressWarnings("deprecation")
    public void give(Player player, boolean edited) {
        player.getInventory().clear();
        player.updateInventory();

        if (edited) {
            String uuid = player.getUniqueId().toString();

            if (Practice.i.database.contains("edited-kits." + uuid + "." + name.toLowerCase())) {
                for (int i = 0; i < 36; i++)
                    player.getInventory().setItem(i, Utils.unserializeItem(Practice.i.database.getString("edited-kits." + uuid + "." + name.toLowerCase() + ".content").split(";")[i]));

                player.getInventory().setHelmet(Utils.unserializeItem(Practice.i.database.getString("edited-kits." + uuid + "." + name.toLowerCase() + ".helmet")));
                player.getInventory().setChestplate(Utils.unserializeItem(Practice.i.database.getString("edited-kits." + uuid + "." + name.toLowerCase() + ".chestplate")));
                player.getInventory().setLeggings(Utils.unserializeItem(Practice.i.database.getString("edited-kits." + uuid + "." + name.toLowerCase() + ".leggings")));
                player.getInventory().setBoots(Utils.unserializeItem(Practice.i.database.getString("edited-kits." + uuid + "." + name.toLowerCase() + ".boots")));
                player.updateInventory();
            } else
                give(player, false);
        } else if (equals(NODEBUFF)) {
            ItemStack heal = Utils.createItem(PotionType.INSTANT_HEAL, 2, true, false);
            ItemStack speed = Utils.createItem(PotionType.SPEED, 2, false, false);
            ItemStack fire = Utils.createItem(PotionType.FIRE_RESISTANCE, 1, false, true);

            for (int i = 9; i < 36; i++)
                player.getInventory().setItem(i, heal);

            player.getInventory().setItem(18, speed);
            player.getInventory().setItem(27, speed);

            player.getInventory().setItem(0, Utils.createItem(Material.DIAMOND_SWORD, 0, null, null,
                    Ench.n(Enchantment.DAMAGE_ALL, 2), Ench.n(Enchantment.FIRE_ASPECT, 2), Ench.n(Enchantment.DURABILITY, 3)));
            player.getInventory().setItem(1, new ItemStack(Material.GRILLED_PORK, 64));
            player.getInventory().setItem(2, new ItemStack(Material.ENDER_PEARL, 16));
            player.getInventory().setItem(3, fire);
            player.getInventory().setItem(4, speed);

            for (int i = 5; i < 9; i++)
                player.getInventory().setItem(i, heal);

            player.getInventory().setHelmet(Utils.createItem(Material.DIAMOND_HELMET, 0, null, null,
                    Ench.n(Enchantment.PROTECTION_ENVIRONMENTAL, 1), Ench.n(Enchantment.DURABILITY, 3)));
            player.getInventory().setChestplate(Utils.createItem(Material.DIAMOND_CHESTPLATE, 0, null, null,
                    Ench.n(Enchantment.PROTECTION_ENVIRONMENTAL, 1), Ench.n(Enchantment.DURABILITY, 3)));
            player.getInventory().setLeggings(Utils.createItem(Material.DIAMOND_LEGGINGS, 0, null, null,
                    Ench.n(Enchantment.PROTECTION_ENVIRONMENTAL, 1), Ench.n(Enchantment.DURABILITY, 3)));
            player.getInventory().setBoots(Utils.createItem(Material.DIAMOND_BOOTS, 0, null, null,
                    Ench.n(Enchantment.PROTECTION_ENVIRONMENTAL, 1), Ench.n(Enchantment.DURABILITY, 3)));
        } else if (equals(DEBUFF)) {
            ItemStack heal = Utils.createItem(PotionType.INSTANT_HEAL, 2, true, false);
            ItemStack poison = Utils.createItem(PotionType.POISON, 1, true, false);
            ItemStack speed = Utils.createItem(PotionType.SPEED, 2, false, false);
            ItemStack fire = Utils.createItem(PotionType.FIRE_RESISTANCE, 1, false, true);

            for (int i = 9; i < 36; i++)
                player.getInventory().setItem(i, heal);

            player.getInventory().setItem(16, poison);
            player.getInventory().setItem(17, poison);
            player.getInventory().setItem(25, poison);
            player.getInventory().setItem(26, poison);
            player.getInventory().setItem(34, poison);
            player.getInventory().setItem(35, poison);

            player.getInventory().setItem(18, speed);
            player.getInventory().setItem(27, speed);

            player.getInventory().setItem(0, Utils.createItem(Material.DIAMOND_SWORD, 0, null, null,
                    Ench.n(Enchantment.DAMAGE_ALL, 2), Ench.n(Enchantment.FIRE_ASPECT, 2), Ench.n(Enchantment.DURABILITY, 3)));
            player.getInventory().setItem(1, new ItemStack(Material.GRILLED_PORK, 64));
            player.getInventory().setItem(2, new ItemStack(Material.ENDER_PEARL, 16));
            player.getInventory().setItem(3, fire);
            player.getInventory().setItem(4, speed);

            for (int i = 5; i < 9; i++)
                player.getInventory().setItem(i, heal);

            player.getInventory().setHelmet(Utils.createItem(Material.DIAMOND_HELMET, 0, null, null,
                    Ench.n(Enchantment.PROTECTION_ENVIRONMENTAL, 1), Ench.n(Enchantment.DURABILITY, 3)));
            player.getInventory().setChestplate(Utils.createItem(Material.DIAMOND_CHESTPLATE, 0, null, null,
                    Ench.n(Enchantment.PROTECTION_ENVIRONMENTAL, 1), Ench.n(Enchantment.DURABILITY, 3)));
            player.getInventory().setLeggings(Utils.createItem(Material.DIAMOND_LEGGINGS, 0, null, null,
                    Ench.n(Enchantment.PROTECTION_ENVIRONMENTAL, 1), Ench.n(Enchantment.DURABILITY, 3)));
            player.getInventory().setBoots(Utils.createItem(Material.DIAMOND_BOOTS, 0, null, null,
                    Ench.n(Enchantment.PROTECTION_ENVIRONMENTAL, 1), Ench.n(Enchantment.DURABILITY, 3)));
        } else if (equals(G_APPLE)) {
            ItemStack speed = Utils.createItem(PotionType.SPEED, 2, false, true);
            ItemStack strength = Utils.createItem(PotionType.STRENGTH, 2, false, true);

            player.getInventory().setItem(0, Utils.createItem(Material.DIAMOND_SWORD, 0, null, null,
                    Ench.n(Enchantment.DAMAGE_ALL, 5), Ench.n(Enchantment.DURABILITY, 3)));

            player.getInventory().setItem(1, new ItemStack(Material.GOLDEN_APPLE, 64, (short) 0, (byte) 1));
            player.getInventory().setItem(7, strength);
            player.getInventory().setItem(8, speed);

            player.getInventory().setHelmet(Utils.createItem(Material.DIAMOND_HELMET, 0, null, null,
                    Ench.n(Enchantment.PROTECTION_ENVIRONMENTAL, 4), Ench.n(Enchantment.DURABILITY, 3)));
            player.getInventory().setChestplate(Utils.createItem(Material.DIAMOND_CHESTPLATE, 0, null, null,
                    Ench.n(Enchantment.PROTECTION_ENVIRONMENTAL, 4), Ench.n(Enchantment.DURABILITY, 3)));
            player.getInventory().setLeggings(Utils.createItem(Material.DIAMOND_LEGGINGS, 0, null, null,
                    Ench.n(Enchantment.PROTECTION_ENVIRONMENTAL, 4), Ench.n(Enchantment.DURABILITY, 3)));
            player.getInventory().setBoots(Utils.createItem(Material.DIAMOND_BOOTS, 0, null, null,
                    Ench.n(Enchantment.PROTECTION_ENVIRONMENTAL, 4), Ench.n(Enchantment.DURABILITY, 3)));
        } else if (equals(BUILD_UHC)) {
            player.getInventory().setItem(0, Utils.createItem(Material.DIAMOND_SWORD, 0, null, null,
                    Ench.n(Enchantment.DAMAGE_ALL, 3), Ench.n(Enchantment.DURABILITY, 3)));

            player.getInventory().setItem(1, new ItemStack(Material.FISHING_ROD, 1));

            player.getInventory().setItem(2, Utils.createItem(Material.BOW, 0, null, null,
                    Ench.n(Enchantment.ARROW_DAMAGE, 3), Ench.n(Enchantment.DURABILITY, 3)));

            player.getInventory().setItem(3, new ItemStack(Material.GRILLED_PORK, 64));
            player.getInventory().setItem(4, new ItemStack(Material.GOLDEN_APPLE, 6));
            player.getInventory().setItem(5, Practice.i.goldenHeadsItem);
            player.getInventory().setItem(6, new ItemStack(Material.DIAMOND_PICKAXE, 1));
            player.getInventory().setItem(7, new ItemStack(Material.DIAMOND_AXE, 1));
            player.getInventory().setItem(8, new ItemStack(Material.WOOD, 64));

            player.getInventory().setItem(9, new ItemStack(Material.ARROW, 64));
            player.getInventory().setItem(10, new ItemStack(Material.COBBLESTONE, 64));
            player.getInventory().setItem(11, new ItemStack(Material.WATER_BUCKET, 1));
            player.getInventory().setItem(12, new ItemStack(Material.WATER_BUCKET, 1));
            player.getInventory().setItem(13, new ItemStack(Material.LAVA_BUCKET, 1));
            player.getInventory().setItem(14, new ItemStack(Material.LAVA_BUCKET, 1));

            player.getInventory().setHelmet(Utils.createItem(Material.DIAMOND_HELMET, 0, null, null,
                    Ench.n(Enchantment.PROTECTION_PROJECTILE, 2), Ench.n(Enchantment.DURABILITY, 3)));
            player.getInventory().setChestplate(Utils.createItem(Material.DIAMOND_CHESTPLATE, 0, null, null,
                    Ench.n(Enchantment.PROTECTION_ENVIRONMENTAL, 2), Ench.n(Enchantment.DURABILITY, 3)));
            player.getInventory().setLeggings(Utils.createItem(Material.DIAMOND_LEGGINGS, 0, null, null,
                    Ench.n(Enchantment.PROTECTION_ENVIRONMENTAL, 2), Ench.n(Enchantment.DURABILITY, 3)));
            player.getInventory().setBoots(Utils.createItem(Material.DIAMOND_BOOTS, 0, null, null,
                    Ench.n(Enchantment.PROTECTION_PROJECTILE, 2), Ench.n(Enchantment.DURABILITY, 3)));
        } else if (equals(SUMO)) {

        }

        player.updateInventory();
    }
}
