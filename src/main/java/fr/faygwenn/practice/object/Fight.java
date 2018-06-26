package fr.faygwenn.practice.object;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.kit.Kit;
import fr.faygwenn.practice.util.MessageSpecial;
import fr.faygwenn.practice.util.Messages;
import fr.faygwenn.practice.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Fight {
    public Player player1;
    public Player player2;
    private boolean player1ready;
    private boolean player2ready;

    private BukkitTask task;
    private String arena;
    public boolean invincibility;
    public boolean ranked;
    public Kit kit;
    public int minutes;

    @SuppressWarnings("deprecation")
    public Fight(Player player1, Player player2, boolean ranked, Kit kit) {
        this.player1 = player1;
        this.player2 = player2;
        this.task = null;
        this.arena = null;
        this.invincibility = true;
        this.ranked = ranked;
        this.kit = kit;
        this.minutes = 0;

        for (Player pl : Bukkit.getOnlinePlayers()) {
            pl.hidePlayer(player1);
            pl.hidePlayer(player2);
            player1.hidePlayer(pl);
            player2.hidePlayer(pl);
        }

        player1.showPlayer(player2);
        player2.showPlayer(player1);
    }

    public Player getWinner(Player died) {
        return player1.equals(died) ? player2 : player1;
    }

    public void searchArena() {
        this.task = new BukkitRunnable() {
            private int seconds = 5;

            @Override
            public void run() {
                // Message

                seconds++;

                if (seconds >= 5) {
                    seconds = 0;
                    LangMessages.SEARCHING_ARENA.getFor(player1).send(player1);
                    LangMessages.SEARCHING_ARENA.getFor(player2).send(player2);
                }

                // Arena

                String freeArena = Practice.i.manager.getArena();

                if (freeArena == null)
                    return;

                arena = freeArena;

                start();
                cancel();
            }
        }.runTaskTimerAsynchronously(Practice.i, 0L, 20L);
    }

    private void start() {
        LangMessages.ARENA_FOUND.getFor(player1).send(player1);
        LangMessages.ARENA_FOUND.getFor(player2).send(player2);

        // Start task

        Location loc1 = Utils.getArenaLocation(arena, 1).add(0.0D, 0.5D, 0.0D);
        Location loc2 = Utils.getArenaLocation(arena, 2).add(0.0D, 0.5D, 0.0D);

        player1.teleport(loc1);
        player1.getInventory().clear();
        player1.getInventory().setItem(0, Practice.i.itemKitNormal);
        player1.getInventory().setItem(3, Practice.i.itemKitEdited);
        player1.updateInventory();

        player2.teleport(loc2);
        player2.getInventory().clear();
        player2.getInventory().setItem(0, Practice.i.itemKitNormal);
        player2.getInventory().setItem(3, Practice.i.itemKitEdited);
        player2.updateInventory();
    }

    public void setReady(Player player, boolean editedKit) {
        if (player.equals(player1) && !player1ready) {
            Practice.i.manager.kits.get(player1).give(player1, editedKit);
            player1ready = true;
        } else if (player.equals(player2) && !player2ready) {
            Practice.i.manager.kits.get(player2).give(player2, editedKit);
            player2ready = true;
        }

        if (player1ready && player2ready) {
            // Invincibility task

            task = new BukkitRunnable() {
                private int invincibilityTime = 5;

                @Override
                public void run() {
                    if (invincibilityTime <= 0) {
                        invincibility = false;

                        LangMessages.START.getFor(player1).send(player1);
                        LangMessages.START.getFor(player2).send(player2);

                        // Scoreboard task

                        task = new BukkitRunnable() {
                            @Override
                            public void run() {
                                minutes++;

                            }
                        }.runTaskTimerAsynchronously(Practice.i, 20L * 60L, 20L * 60L);

                        // Cancel older

                        cancel();
                        return;
                    }

                    LangMessages.START_COUNTDOWN.getFor(player1).replace("{time}", invincibilityTime).replace("{plural}", invincibilityTime > 1 ? "s" : "").send(player1);
                    LangMessages.START_COUNTDOWN.getFor(player2).replace("{time}", invincibilityTime).replace("{plural}", invincibilityTime > 1 ? "s" : "").send(player2);
                    invincibilityTime--;
                }
            }.runTaskTimer(Practice.i, 0L, 20L);
        }
    }

    public void stop(final Player winner) {
        if (task != null)
            task.cancel();

        if (winner != null) {
            new BukkitRunnable() {
                @SuppressWarnings("deprecation")
                @Override
                public void run() {
                    Practice.i.spawnInventory(player1);
                    Practice.i.spawnInventory(player2);

                    player1.teleport(Utils.getSpawnLocation().add(0.0D, 0.5D, 0.0D));
                    player2.teleport(Utils.getSpawnLocation().add(0.0D, 0.5D, 0.0D));

                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        pl.showPlayer(player1);
                        pl.showPlayer(player2);
                        player1.showPlayer(pl);
                        player2.showPlayer(pl);
                    }

                    Practice.i.specInv.remove(player1);
                    Practice.i.specInv.remove(player2);
                }
            }.runTaskLater(Practice.i, 20L * 2L);
        }

        if (winner == null) {
            Messages.UNKNOWN_ERROR_CANCEL_FIGHT.get().send(player1);
            Messages.UNKNOWN_ERROR_CANCEL_FIGHT.get().send(player2);
        } else {
            LangMessages.COMBAT_END.getFor(player1).replace("{winner}", winner.getDisplayName()).send(player1);
            LangMessages.COMBAT_END.getFor(player2).replace("{winner}", winner.getDisplayName()).send(player2);
        }

        if (player1 != null && player2 != null) {
            MessageSpecial msg = new MessageSpecial();
            msg.newJComp("§fVoir l'inventaire de §a" + player1.getDisplayName()).addHoverText("§fClique pour voir son inventaire")
                    .addCommandExecutor("/endfightseeinv " + player1.getName()).build(msg);
            msg.send(player2);

            msg = new MessageSpecial();
            msg.newJComp("§fVoir l'inventaire de §a" + player2.getDisplayName()).addHoverText("§fClique pour voir son inventaire")
                    .addCommandExecutor("/endfightseeinv " + player2.getName()).build(msg);
            msg.send(player1);
        }

        Practice.i.manager.fights.remove(this);

        Practice.i.heal(player1);
        Practice.i.heal(player2);

        for (PotionEffect pe : player1.getActivePotionEffects())
            player1.removePotionEffect(pe.getType());

        for (PotionEffect pe : player2.getActivePotionEffects())
            player2.removePotionEffect(pe.getType());

        // Ranked

        if (ranked && winner != null) {
            int winnerElos = Utils.getElos(winner, kit);
            int playerElos = Utils.getElos(winner.equals(player1) ? player2 : player1, kit);
            int difference = Math.max(winnerElos, playerElos) - Math.min(winnerElos, playerElos);
            int elos;

            if (difference < 100)
                elos = 15;
            else if (difference < 200)
                elos = 10;
            else if (difference < 250)
                elos = 5;
            else
                elos = 3;

            Utils.addElos(winner, elos, kit);
            LangMessages.ELOS_WIN.getFor(winner).replace("{elos}", elos).send(winner);
        }
    }
}
