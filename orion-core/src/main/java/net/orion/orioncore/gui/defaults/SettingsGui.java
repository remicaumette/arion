package net.orion.orioncore.gui.defaults;

import net.orion.orioncore.api.gui.OrionGui;
import net.orion.orioncore.api.item.Head;
import net.orion.orioncore.api.item.HeadBuilder;
import net.orion.orioncore.api.player.OrionPlayer;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

public class SettingsGui extends OrionGui {
    @Override
    public String getName(OrionPlayer player) {
        return player.getLang().tl("settings.name");
    }

    @Override
    public int getSize(OrionPlayer player) {
        return 18;
    }

    @Override
    public void onCreate(OrionPlayer player, Inventory inventory) {
        inventory.setItem(2, new HeadBuilder(Head.QUESTION)
                .withName("Une question ?")
                .withLore("C'est normal", "clique")
                .build());
    }

    @Override
    public void onClick(OrionPlayer player, Inventory inventory, int slot, ClickType type) {
        player.sendRawMessage("slot: " + slot);
    }
}
