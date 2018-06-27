package net.arion.arioncore.api.gui;

import net.arion.arioncore.api.player.ArionPlayer;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

public abstract class ArionGui {
    public String getName(ArionPlayer player) {
        return "Default name";
    }

    public int getSize(ArionPlayer player) {
        return 9;
    }

    public void onCreate(ArionPlayer player, Inventory inventory) {
    }

    public void onClick(ArionPlayer player, Inventory inventory, int slot, ClickType click) {
    }

    public void onDestroy(ArionPlayer player) {
    }
}
