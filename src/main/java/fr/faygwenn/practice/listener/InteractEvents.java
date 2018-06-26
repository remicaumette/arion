package fr.faygwenn.practice.listener;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.object.SelectKitFor;
import fr.faygwenn.practice.util.LangMessages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractEvents implements Listener {
    @EventHandler
    public void execute(PlayerInteractEvent event) {
        if (event.getAction().toString().contains("RIGHT_CLICK")) {
            ItemStack item = event.getItem();

            if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("§6Unranked")) {
                event.setCancelled(true);

                Player player = event.getPlayer();
                Practice.i.manager.cancelPreviousSearch(player);
                Practice.i.manager.selectKitFor.add(new SelectKitFor(player, null, SelectKitFor.Type.UNRANKED));
            } else if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("§6Ranked")) {
                event.setCancelled(true);

                Player player = event.getPlayer();

                if (Bukkit.getOfflinePlayers().length < Practice.i.config.getInt("min-players-ranked")) {
                    LangMessages.NOT_ENOUGTH_RANKED_PLAYERS.getFor(player).send(player);
                    return;
                }

                Practice.i.manager.cancelPreviousSearch(player);
                Practice.i.manager.selectKitFor.add(new SelectKitFor(player, null, SelectKitFor.Type.RANKED));
            } else if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("§aEdit kit")) {
                event.setCancelled(true);

                Player player = event.getPlayer();
                Practice.i.manager.cancelPreviousSearch(player);
                Practice.i.manager.selectKitFor.add(new SelectKitFor(player, null, SelectKitFor.Type.EDITING));
            } else if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("§aKit-Default")) {
                event.setCancelled(true);

                Player player = event.getPlayer();
                Practice.i.manager.getFight(player).setReady(player, false);
            } else if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("§aKit-Edited")) {
                event.setCancelled(true);

                Player player = event.getPlayer();
                Practice.i.manager.getFight(player).setReady(player, true);
            }
        }
    }
}
