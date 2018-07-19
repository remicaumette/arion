package net.orion.orioncore.api.item;


import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ItemBuilder {
    private ItemStack item;

    /**
     * @param material Le type.
     */
    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
    }

    /**
     * @param material Le type.
     * @param amount   La quantité.
     */
    public ItemBuilder(Material material, int amount) {
        this.item = new ItemStack(material, amount);
    }

    /**
     * @param material Le type.
     * @param amount   La quantité.
     * @param data     La "data".
     */
    public ItemBuilder(Material material, int amount, short data) {
        this.item = new ItemStack(material, amount, data);
    }

    /**
     * Défini le type de l'objet.
     *
     * @param material Le type.
     * @return L'instance actuellement utilisé.
     */
    public ItemBuilder withType(Material material) {
        item.setType(material);
        return this;
    }

    /**
     * Défini la "data" de l'objet. Pour par exemple faire varier sa couleur.
     *
     * @param data La "data".
     * @return L'instance actuellement utilisé.
     */
    public ItemBuilder withData(short data) {
        item.setDurability(data);
        return this;
    }

    /**
     * Défini la quantité de l'objet.
     *
     * @param amount La quantité.
     * @return L'instance actuellement utilisé.
     */
    public ItemBuilder withAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    /**
     * Défini le nom de l'objet.
     *
     * @param name Le nom de l'objet.
     * @return L'instance actuellement utilisé.
     */
    public ItemBuilder withName(String name) {
        item.getItemMeta().setDisplayName(name);
        return this;
    }

    /**
     * Défini la description de l'objet.
     *
     * @param lore La description de l'objet.
     * @return L'instance actuellement utilisé.
     */
    public ItemBuilder withLore(String... lore) {
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
    public ItemBuilder withEnchant(Enchantment enchant, int level) {
        item.addEnchantment(enchant, level);
        return this;
    }

    /**
     * Ajoute un "flag" à l'objet.
     *
     * @param flag Le "flag" à ajouter.
     * @return L'instance actuellement utilisé.
     */
    public ItemBuilder withFlag(ItemFlag flag) {
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
