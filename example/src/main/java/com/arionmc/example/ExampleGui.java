package com.arionmc.example;

import com.arionmc.arioncore.api.gui.Gui;
import com.arionmc.arioncore.api.item.ItemBuilder;
import com.arionmc.arioncore.api.player.ArionPlayer;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

public class ExampleGui extends Gui {
    @Override
    public int getSize(ArionPlayer player) {
        return 18;
    }

    @Override
    public String getName(ArionPlayer player) {
        return player.getLang().tl("hello");
    }

    @Override
    public void onCreate(ArionPlayer player, Inventory inventory) {
        inventory.setItem(0, new ItemBuilder(Material.DIAMOND)
                .withName("Un diamant !")
                .withLore("C bo")
                .withFlag(ItemFlag.HIDE_ENCHANTS)
                .withEnchant(Enchantment.KNOCKBACK, 5)
                .build());
    }

    @Override
    public void onClick(ArionPlayer player, Inventory inventory, int slot, ClickType click) {
        if (slot == 0) {
            switch (click) {
                case LEFT:
                    player.sendRawMessage("§aClic gauche !");
                    break;
                case RIGHT:
                    player.sendRawMessage("§aClic droit !");
                    break;
            }
        }
    }

    @Override
    public void onDestroy(ArionPlayer player) {
        player.sendRawMessage("§c++");
    }
}
