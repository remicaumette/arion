package fr.faygwenn.practice.object;

import fr.faygwenn.practice.kit.KitSelector;
import org.bukkit.entity.Player;

public class SelectKitFor {
    public enum Type {
        EDITING, UNRANKED, RANKED, DUEL, DUEL_ACCEPT;
    }

    public final Player player;
    public final String target;
    public final Type type;

    public SelectKitFor(Player player, String target, Type type) {
        this.player = player;
        this.target = target;
        this.type = type;

        new KitSelector(player, type.equals(Type.RANKED)).open();
    }
}
