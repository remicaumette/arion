package fr.faygwenn.practice.objects;

import org.bukkit.enchantments.Enchantment;

public class Ench {
    public final Enchantment ench;
    public final int level;

    public Ench(Enchantment ench, int level) {
        this.ench = ench;
        this.level = level;
    }

    public static final Ench n(Enchantment ench, int level) {
        return new Ench(ench, level);
    }
}