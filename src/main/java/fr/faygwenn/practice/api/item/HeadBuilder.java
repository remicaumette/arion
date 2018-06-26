package fr.faygwenn.practice.api.item;


import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class HeadBuilder {
    private ItemStack item;

    public HeadBuilder(Head head) {
        this(head.getName(), 1);
    }

    public HeadBuilder(String name) {
        this(name, 1);
    }

    public HeadBuilder(Head head, int amount) {
        this(head.getName(), amount);
    }

    public HeadBuilder(String name, int amount) {
        this.item = new ItemStack(Material.SKULL_ITEM, amount, (short) SkullType.PLAYER.ordinal());
    }

    public HeadBuilder withAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public HeadBuilder withName(String name) {
        item.getItemMeta().setDisplayName(name);
        return this;
    }

    public HeadBuilder withLore(String... lore) {
        item.getItemMeta().setLore(Arrays.asList(lore));
        return this;
    }

    public HeadBuilder withEnchant(Enchantment enchant, int level) {
        item.addEnchantment(enchant, level);
        return this;
    }

    public HeadBuilder withFlag(ItemFlag flag) {
        item.getItemMeta().addItemFlags(flag);
        return this;
    }

    public ItemStack build() {
        return item;
    }
}
