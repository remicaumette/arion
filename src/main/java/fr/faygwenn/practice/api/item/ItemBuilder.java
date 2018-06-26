package fr.faygwenn.practice.api.item;


import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ItemBuilder {
    private ItemStack item;

    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
    }

    public ItemBuilder(Material material, int amount) {
        this.item = new ItemStack(material, amount);
    }

    public ItemBuilder(Material material, int amount, short data) {
        this.item = new ItemStack(material, amount, data);
    }

    public ItemBuilder withType(Material material) {
        item.setType(material);
        return this;
    }

    public ItemBuilder withData(short data) {
        item.setDurability(data);
        return this;
    }

    public ItemBuilder withAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder withName(String name) {
        item.getItemMeta().setDisplayName(name);
        return this;
    }

    public ItemBuilder withLore(String... lore) {
        item.getItemMeta().setLore(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder withEnchant(Enchantment enchant, int level) {
        item.addEnchantment(enchant, level);
        return this;
    }

    public ItemBuilder withFlag(ItemFlag flag) {
        item.getItemMeta().addItemFlags(flag);
        return this;
    }

    public ItemStack build() {
        return item;
    }
}
