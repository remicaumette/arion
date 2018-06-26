package fr.faygwenn.practice.search;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.kits.Kit;
import fr.faygwenn.practice.objects.Fight;
import fr.faygwenn.practice.objects.LookingFor;
import fr.faygwenn.practice.utils.LangMessages;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class OpponentSearch {
    public static void start(final Player player, final Kit kit, final LookingFor.Type type) {
        BukkitTask task = new BukkitRunnable() {
            private int seconds = 5;

            @Override
            public void run() {
                // Message

                seconds++;

                if (seconds >= 5) {
                    seconds = 0;
                    LangMessages.SEARCHING_PLAYER.getFor(player).replace("{mode}", type.toString().toLowerCase() + " (" + kit.name + ")").send(player);
                }

                // Player

                for (final LookingFor lFor : Practice.i.manager.lookingFor) {
                    if (lFor.player.equals(player))
                        continue;

                    if (!lFor.type.equals(type))
                        continue;

                    if (!lFor.kit.equals(kit))
                        continue;

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            LookingFor playerLFor = null;

                            for (LookingFor lf : Practice.i.manager.lookingFor) {
                                if (lf.player.equals(player)) {
                                    playerLFor = lf;
                                    break;
                                }
                            }

                            // Cancel looking for

                            lFor.task.cancel();
                            Practice.i.manager.lookingFor.remove(lFor);

                            if (playerLFor != null) {
                                playerLFor.task.cancel();
                                Practice.i.manager.lookingFor.remove(playerLFor);
                            }

                            // Message found

                            LangMessages.PLAYER_FOUND.getFor(player).replace("{player}", lFor.player.getName()).send(player);
                            LangMessages.PLAYER_FOUND.getFor(lFor.player).replace("{player}", player.getName()).send(lFor.player);

                            // Start search arena

                            Fight fight = new Fight(player, lFor.player, type.equals(LookingFor.Type.RANKED), lFor.kit);
                            fight.searchArena();

                            Practice.i.manager.fights.add(fight);
                        }
                    }.runTask(Practice.i);

                    cancel();
                    return;
                }
            }
        }.runTaskTimerAsynchronously(Practice.i, 20L, 20L);

        Practice.i.manager.lookingFor.add(new LookingFor(player, kit, type, task));
    }
}
