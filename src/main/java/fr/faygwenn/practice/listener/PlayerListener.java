package fr.faygwenn.practice.listener;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.api.player.PracticePlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {
    private Practice plugin;

    public PlayerListener(Practice plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        plugin.getPlayerManager().onJoin(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        plugin.getPlayerManager().onQuit(player);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PracticePlayer practicePlayer = plugin.getPlayerManager().getPlayer(player);
        Action action = event.getAction();
        ItemStack item = event.getItem();

        if (item != null) {
            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                if (item.getType() == Material.ENDER_PEARL) {
                    if (Practice.i.manager.hasFight(player)) {
                        if (Practice.i.manager.getFight(player).invincibility) {
                            event.setCancelled(true);
                            player.updateInventory();
                            return;
                        }
                    }

                    long delay = (System.currentTimeMillis() - practicePlayer.getLastEnderPearl()) / 1000;

                    if (delay < 10) {
                        LangMessages.ENDERPEARL_DELAY.getFor(player)
                                .replace("{time}", delay).replace("{plural}", delay > 1 ? "s" : "")
                                .send(player);
                        event.setCancelled(true);
                        player.updateInventory();
                    } else {
                        practicePlayer.setLastEnderPearl(System.currentTimeMillis());
                    }
                }
            }
        }
    }
}
