package fr.faygwenn.practice;

import fr.faygwenn.practice.kit.Kit;
import fr.faygwenn.practice.object.Fight;
import fr.faygwenn.practice.object.LookingFor;
import fr.faygwenn.practice.object.SelectKitFor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Manager {
    // Looking for a game

    public ArrayList<LookingFor> lookingFor = new ArrayList<LookingFor>();

    public void cancelPreviousSearch(Player player) {
        for (SelectKitFor select : selectKitFor) {
            if (select.player.equals(player)) {
                selectKitFor.remove(select);
                break;
            }
        }

        for (LookingFor lFor : lookingFor) {
            if (lFor.player.equals(player)) {
                lFor.cancel();
                lookingFor.remove(lFor);
                LangMessages.CANCEL_SEARCH.getFor(player).send(player);

                break;
            }
        }

        for (Fight fight : fights) {
            if (fight.player1.equals(player) || fight.player2.equals(player))
                fight.stop(null);
        }

        if (editingPlayers.containsKey(player))
            Bukkit.dispatchCommand(player, "kit1save");

    }

    public int getLookingForIn(LookingFor.Type type, Kit kit) {
        int in = 0;

        for (LookingFor lFor : lookingFor) {
            if (type.equals(lFor.type) && kit.equals(lFor.kit))
                in++;
        }

        return in;
    }

    // Kit editor

    public HashMap<Player, Kit> editingPlayers = new HashMap<Player, Kit>();

    // Select kit

    public HashMap<Player, Kit> kits = new HashMap<Player, Kit>();
    public ArrayList<SelectKitFor> selectKitFor = new ArrayList<SelectKitFor>();

    // Arenas

    public String getArena() {
        ConfigurationSection sec = Practice.i.database.getConfigurationSection("arenas");

        return sec.getKeys(false).toArray(new String[sec.getKeys(false).size()])[new Random().nextInt(sec.getKeys(false).size())];
    }

    // Fights

    public ArrayList<Fight> fights = new ArrayList<Fight>();

    public Fight getFight(Player player) {
        Fight fight = null;

        for (Fight f : fights) {
            if (f.player1.equals(player) || f.player2.equals(player)) {
                fight = f;
                break;
            }
        }

        return fight;
    }

    public boolean hasFight(Player player) {
        return getFight(player) != null;
    }

    public int getFightsIn(boolean ranked, Kit kit) {
        int in = 0;

        for (Fight fight : fights) {
            if (ranked == fight.ranked && kit.equals(fight.kit))
                in++;
        }

        return in;
    }

    // Requests

    private HashMap<Player, Player> duelRequests = new HashMap<Player, Player>();

    public boolean hasDuelRequestFrom(Player requested, Player player) {
        return duelRequests.containsKey(requested) && duelRequests.get(requested).equals(player);
    }

    public void sendDuelRequest(final Player player, final Player target) {
        duelRequests.put(target, player);
        LangMessages.REQUEST.getFor(target).replace("{player}", player.getDisplayName()).send(target);
        LangMessages.REQUEST_SELF.getFor(player).replace("{player}", target.getDisplayName()).send(player);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (target != null && target.isOnline() && duelRequests.get(target).equals(player))
                    duelRequests.remove(target);
            }
        }.runTaskLater(Practice.i, 20L * 30);
    }

    public Player getDuelRequest(Player player) {
        return duelRequests.get(player);
    }

    public void acceptDuelRequest(Player target) {
        Player requested = duelRequests.get(target);
        cancelPreviousSearch(target);

        LangMessages.INVITATION_ACCEPTED.getFor(requested).replace("{player}", target.getDisplayName()).send(requested);
        LangMessages.INVITATION_ACCEPTED_SELF.getFor(target).replace("{player}", requested.getDisplayName()).send(target);

        Fight fight = new Fight(target, requested, false, null);
        fight.searchArena();

        fights.add(fight);
    }
}
