package fr.faygwenn.practice.listener;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.object.Fight;
import fr.faygwenn.practice.util.DelayRunnable;
import fr.faygwenn.practice.util.LangMessages;
import fr.faygwenn.practice.util.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Events implements Listener {
    private HashMap<Player, DelayRunnable> enderDelays = new HashMap<Player, DelayRunnable>();

    @EventHandler
    public void execute(PlayerInteractEvent event) {
        if (!event.getAction().toString().contains("RIGHT_CLICK"))
            return;

        ItemStack item = event.getItem();

        if (item == null)
            return;

        if (!item.getType().equals(Material.ENDER_PEARL))
            return;

        Player player = event.getPlayer();

        if (Practice.i.manager.hasFight(player)) {
            if (Practice.i.manager.getFight(player).invincibility) {
                event.setCancelled(true);
                player.updateInventory();
                return;
            }
        }

        if (enderDelays.containsKey(player)) {
            DelayRunnable runnable = enderDelays.get(player);

            if (runnable.delay > 0) {
                LangMessages.ENDERPEARL_DELAY.getFor(player).replace("{time}", runnable.delay).replace("{plural}", runnable.delay > 1 ? "s" : "").send(player);
                event.setCancelled(true);
                player.updateInventory();
                return;
            } else
                newEnderDelay(player);
        } else
            newEnderDelay(player);
    }

    private void newEnderDelay(Player player) {
        DelayRunnable runnable = new DelayRunnable(player) {
            @Override
            public void run() {
                delay--;

                if (delay <= 0) {
                    enderDelays.remove(player);
                    LangMessages.ENDERPEARL_READY.getFor(player).send(player);
                    cancel();
                }
            }
        };

        runnable.runTaskTimerAsynchronously(Practice.i, 20L, 20L);
        enderDelays.put(player, runnable);
    }

    @EventHandler
    public void execute(CreatureSpawnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void execute(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player && !Practice.i.manager.hasFight((Player) event.getEntity()))
            event.setCancelled(true);
    }

    @EventHandler
    public void execute(PlayerItemConsumeEvent event) {
        if (event.getItem().hasItemMeta() && event.getItem().getItemMeta().getDisplayName().equals("§6GolenHead")) {
            int durationAbsorption = 20 * 60 * 2 + 20 * 5;
            int durationRegeneration = 20 * 10 + 20 * 5;

            PotionEffect[] array = event.getPlayer().getActivePotionEffects().toArray(new PotionEffect[event.getPlayer().getActivePotionEffects().size()]);

            for (int i = 0; i < array.length; i++) {
                PotionEffect effect = array[i];

                if (effect.getType().equals(PotionEffectType.ABSORPTION)) {
                    durationAbsorption += effect.getDuration();
                    event.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
                } else if (effect.getType().equals(PotionEffectType.REGENERATION)) {
                    durationRegeneration += effect.getDuration();
                    event.getPlayer().removePotionEffect(PotionEffectType.REGENERATION);
                }
            }

            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, durationAbsorption, 0));
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, durationRegeneration, 1));
        } else if (!Practice.i.manager.hasFight(event.getPlayer()))
            event.setCancelled(true);
    }

    @EventHandler
    public void execute(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Fight fight = Practice.i.manager.getFight(player);

        Practice.i.manager.kits.remove(player);

        if (fight != null)
            fight.stop(fight.getWinner(player));

    }

    @EventHandler
    public void execute(PlayerDropItemEvent event) {
        if (Practice.i.build.contains(event.getPlayer()))
            return;

        if (Practice.i.manager.hasFight(event.getPlayer())) {
            final Item item = event.getItemDrop();

            new BukkitRunnable() {
                @Override
                public void run() {
                    item.remove();
                }
            }.runTaskLater(Practice.i, 20L * 3);
        } else
            event.setCancelled(true);
    }

    @EventHandler
    public void execute(PlayerPickupItemEvent event) {
        if (Practice.i.build.contains(event.getPlayer()))
            return;

        event.setCancelled(true);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void execute(PlayerJoinEvent event) {
        if (!Practice.i.database.contains("lang." + event.getPlayer().getUniqueId().toString()))
            Practice.i.database.set("lang." + event.getPlayer().getUniqueId().toString(), "en");

        if (!Practice.i.database.contains("elos." + event.getPlayer().getUniqueId().toString()))
            Practice.i.database.set("elos." + event.getPlayer().getUniqueId().toString(), 0);

        Practice.i.spawnInventory(event.getPlayer());

        Location loc = Utils.unserializeLocation(Practice.i.database.getString("spawn"));

        if (loc != null)
            event.getPlayer().teleport(loc);


        // TODO Practice.i.hide(event.getPlayer());
    }

    @EventHandler
    public void execute(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if (Practice.i.manager.hasFight(player)) {
            Fight fight = Practice.i.manager.getFight(player);
            Player winner = fight.getWinner(player);
            fight.stop(winner);

            // Practice.i.spawnInventory(player);
            Practice.i.heal(player);

            for (PotionEffect effect : player.getActivePotionEffects())
                player.removePotionEffect(effect.getType());

            event.setKeepInventory(true);
            event.setKeepLevel(true);
            // player.teleport(Utils.getSpawnLocation().add(0.0D, 0.5D, 0.0D));
            player.teleport(player.getLocation().add(0.0D, 0.5D, 0.0D));
        }
    }

    @EventHandler
    public void execute(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (!Practice.i.manager.hasFight((Player) event.getEntity()))
                event.setDamage(0.0D);
        }
    }

    @EventHandler
    public void execute(PlayerRespawnEvent event) {
        Practice.i.heal(event.getPlayer());
        event.setRespawnLocation(Utils.getSpawnLocation().add(0.0D, 0.5D, 0.0D));
    }

    @EventHandler
    public void execute(BlockPlaceEvent event) {
        if (Practice.i.build.contains(event.getPlayer()))
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void execute(BlockBreakEvent event) {
        if (Practice.i.build.contains(event.getPlayer()))
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void execute(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player) || !(event.getDamager() instanceof Player))
            return;

        Player player = (Player) event.getEntity();
        Player killer = (Player) event.getDamager();

        if (!Practice.i.manager.hasFight(player) || !Practice.i.manager.hasFight(killer)) {
            event.setCancelled(true);
            return;
        }

        if (Practice.i.manager.hasFight(player) && Practice.i.manager.hasFight(killer)) {
            Fight playerFight = Practice.i.manager.getFight(player);
            Fight killerFight = Practice.i.manager.getFight(killer);

            if (playerFight.equals(killerFight) && playerFight.invincibility)
                event.setCancelled(true);
        }
    }
}
