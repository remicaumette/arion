package fr.faygwenn.practice.kit;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.util.LangMessages;
import fr.faygwenn.practice.util.Utils;
import org.bukkit.entity.Player;

public class KitEditor {
    public static final void start(Player player, Kit kit) {
        if (Practice.i.manager.editingPlayers.containsKey(player))
            return;

        Practice.i.manager.editingPlayers.put(player, kit);

        player.getInventory().clear();
        kit.give(player, false);
    }

    public static final void stop(Player player) {
        if (!Practice.i.manager.editingPlayers.containsKey(player)) {
            LangMessages.NO_EDITING.getFor(player).send(player);
            return;
        }

        Kit kit = Practice.i.manager.editingPlayers.get(player);
        Practice.i.manager.editingPlayers.remove(player);

        String uuid = player.getUniqueId().toString();
        String serialized = "";

        for (int i = 0; i < 36; i++)
            serialized += Utils.serializeItem(player.getInventory().getItem(i)) + ";";

        Practice.i.database.set("edited-kits." + uuid + "." + kit.name.toLowerCase() + ".content", serialized);
        Practice.i.database.set("edited-kits." + uuid + "." + kit.name.toLowerCase() + ".helmet", Utils.serializeItem(player.getInventory().getHelmet()));
        Practice.i.database.set("edited-kits." + uuid + "." + kit.name.toLowerCase() + ".chestplate", Utils.serializeItem(player.getInventory().getChestplate()));
        Practice.i.database.set("edited-kits." + uuid + "." + kit.name.toLowerCase() + ".leggings", Utils.serializeItem(player.getInventory().getLeggings()));
        Practice.i.database.set("edited-kits." + uuid + "." + kit.name.toLowerCase() + ".boots", Utils.serializeItem(player.getInventory().getBoots()));
        Practice.i.spawnInventory(player);
    }
}
