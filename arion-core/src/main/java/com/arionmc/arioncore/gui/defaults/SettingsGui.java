package com.arionmc.arioncore.gui.defaults;

import com.arionmc.arioncore.api.gui.ArionGui;
import com.arionmc.arioncore.api.item.Head;
import com.arionmc.arioncore.api.item.HeadBuilder;
import com.arionmc.arioncore.api.player.ArionPlayer;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

public class SettingsGui extends ArionGui {
    @Override
    public String getName(ArionPlayer player) {
        return player.getLang().tl("settings.name");
    }

    @Override
    public int getSize(ArionPlayer player) {
        return 18;
    }

    @Override
    public void onCreate(ArionPlayer player, Inventory inventory) {
        inventory.setItem(2, new HeadBuilder(Head.QUESTION)
                .withName("Une question ?")
                .withLore("C'est normal", "clique")
                .build());
    }

    @Override
    public void onClick(ArionPlayer player, Inventory inventory, int slot, ClickType type) {
        player.sendRawMessage("slot: " + slot);
    }
}
