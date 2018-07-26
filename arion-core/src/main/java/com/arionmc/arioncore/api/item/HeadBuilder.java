package com.arionmc.arioncore.api.item;


import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class HeadBuilder {
    private ItemStack item;

    /**
     * @param head La tête à utiliser.
     */
    public HeadBuilder(Head head) {
        this(head.getName(), 1);
    }

    /**
     * @param name Le nom de la tête à utiliser.
     */
    public HeadBuilder(String name) {
        this(name, 1);
    }

    /**
     * @param head   La tête à utiliser.
     * @param amount La quantité.
     */
    public HeadBuilder(Head head, int amount) {
        this(head.getName(), amount);
    }

    /**
     * @param name   Le nom de la tête à utiliser.
     * @param amount La quantité.
     */
    public HeadBuilder(String name, int amount) {
        this.item = new ItemStack(Material.SKULL_ITEM, amount, (short) SkullType.PLAYER.ordinal());
        ((SkullMeta) item.getItemMeta()).setOwner(name);
    }

    /**
     * Défini la quantité de l'objet.
     *
     * @param amount La quantité.
     * @return L'instance actuellement utilisé.
     */
    public HeadBuilder withAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    /**
     * Défini le nom de l'objet.
     *
     * @param name Le nom de l'objet.
     * @return L'instance actuellement utilisé.
     */
    public HeadBuilder withName(String name) {
        item.getItemMeta().setDisplayName(name);
        return this;
    }

    /**
     * Défini la description de l'objet.
     *
     * @param lore La description de l'objet.
     * @return L'instance actuellement utilisé.
     */
    public HeadBuilder withLore(String... lore) {
        item.getItemMeta().setLore(Arrays.asList(lore));
        return this;
    }

    /**
     * Ajoute un enchantement à l'objet.
     *
     * @param enchant L'enchantement à appliquer.
     * @param level   Le niveau d'enchantement.
     * @return L'instance actuellement utilisé.
     */
    public HeadBuilder withEnchant(Enchantment enchant, int level) {
        item.addEnchantment(enchant, level);
        return this;
    }

    /**
     * Ajoute un "flag" à l'objet.
     *
     * @param flag Le "flag" à ajouter.
     * @return L'instance actuellement utilisé.
     */
    public HeadBuilder withFlag(ItemFlag flag) {
        item.getItemMeta().addItemFlags(flag);
        return this;
    }

    /**
     * @return Créé l'objet item.
     */
    public ItemStack build() {
        return item;
    }
}
