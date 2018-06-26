package fr.faygwenn.practice.objects;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.kits.Kit;
import fr.faygwenn.practice.kits.KitEditor;
import fr.faygwenn.practice.objects.SelectKitFor.Type;
import fr.faygwenn.practice.search.OpponentSearch;
import fr.faygwenn.practice.utils.LangMessages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AfterSelecting {
    public static void afterSelecting(Player player, Kit kit) {
        SelectKitFor select = null;

        for (SelectKitFor skf : Practice.i.manager.selectKitFor) {
            if (skf.player.equals(player)) {
                select = skf;
                break;
            }
        }

        if (select != null) {
            Practice.i.manager.selectKitFor.remove(select);

            if (select.type.equals(Type.EDITING)) {
                Practice.i.manager.kits.put(player, kit);
                KitEditor.start(player, kit);
            } else if (select.type.equals(Type.UNRANKED)) {
                Practice.i.manager.kits.put(player, kit);
                OpponentSearch.start(player, kit, LookingFor.Type.UNRANKED);
            } else if (select.type.equals(Type.RANKED)) {
                Practice.i.manager.kits.put(player, kit);
                OpponentSearch.start(player, kit, LookingFor.Type.RANKED);
            } else if (select.type.equals(Type.DUEL)) {
                Player target = Bukkit.getPlayer(select.target);

                if (target == null || !target.isOnline()) {
                    LangMessages.UNKNOWN_PLAYER.getFor(player).replace("{player}", select.target).send(player);
                    return;
                }

                if (player.equals(target)) {
                    LangMessages.INVITE_SELF.getFor(player).send(player);
                    return;
                }

                if (Practice.i.manager.hasFight(target)) {
                    LangMessages.IN_COMBAT.getFor(player).replace("{player}", target.getDisplayName()).send(player);
                    return;
                }

                LangMessages.SELECT_KIT.getFor(player).replace("{kit}", kit.name).send(player);
                Practice.i.manager.kits.put(player, kit);
                Practice.i.manager.sendDuelRequest(player, target);
            } else if (select.type.equals(Type.DUEL_ACCEPT)) {
                Player requested = Practice.i.manager.getDuelRequest(player);

                if (requested == null) {
                    LangMessages.NO_REQUEST.getFor(player).send(player);
                    return;
                }

                if (Practice.i.manager.hasFight(player)) {
                    LangMessages.IN_COMBAT_SELF.getFor(player).send(player);
                    return;
                }

                if (Practice.i.manager.hasFight(player)) {
                    LangMessages.IN_COMBAT.getFor(player).replace("{player}", requested.getDisplayName()).send(player);
                    return;
                }

                LangMessages.SELECT_KIT.getFor(player).replace("{kit}", kit.name).send(player);
                Practice.i.manager.kits.put(player, kit);
                Practice.i.manager.acceptDuelRequest(player);
            }
        }
    }
}
