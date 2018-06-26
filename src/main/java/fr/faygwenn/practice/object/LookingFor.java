package fr.faygwenn.practice.object;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.kit.Kit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class LookingFor {
    public enum Type {
        RANKED, UNRANKED;
    }

    public final Player player;
    public final Kit kit;
    public final Type type;
    public final BukkitTask task;
    private final BukkitTask timeTask;
    public int seconds;

    public LookingFor(final Player player, Kit kit, Type type, BukkitTask task) {
        this.player = player;
        this.kit = kit;
        this.type = type;
        this.task = task;
        this.seconds = 0;
        this.timeTask = new BukkitRunnable() {
            @Override
            public void run() {
                seconds++;
            }
        }.runTaskTimerAsynchronously(Practice.i, 0L, 20L);
    }

    public void cancel() {
        if (task != null)
            task.cancel();

        if (timeTask != null)
            timeTask.cancel();
    }
}
