package fr.faygwenn.practice.kits;

import fr.faygwenn.practice.Practice;
import fr.faygwenn.practice.objects.LookingFor.Type;
import fr.faygwenn.practice.utils.LangMessages;
import fr.faygwenn.practice.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionType;

import java.util.Arrays;

public class KitSelector {
    public final Player player;
    public final Inventory inv;

    public KitSelector(Player player, boolean ranked) {
        this.player = player;
        this.inv = Bukkit.createInventory(player, 9, LangMessages.KITS_INVENTORY_NAME.getFor(player).getLines().get(0));

        Type type = ranked ? Type.RANKED : Type.UNRANKED;
        int nodebuffQueue = Practice.i.manager.getLookingForIn(type, Kit.NODEBUFF);
        int nodebuffFight = Practice.i.manager.getFightsIn(ranked, Kit.NODEBUFF);
        int debuffQueue = Practice.i.manager.getLookingForIn(type, Kit.DEBUFF);
        int debuffFight = Practice.i.manager.getFightsIn(ranked, Kit.DEBUFF);
        int gappleQueue = Practice.i.manager.getLookingForIn(type, Kit.G_APPLE);
        int gappleFight = Practice.i.manager.getFightsIn(ranked, Kit.G_APPLE);
        int builduhcQueue = Practice.i.manager.getLookingForIn(type, Kit.BUILD_UHC);
        int builduhcFight = Practice.i.manager.getFightsIn(ranked, Kit.BUILD_UHC);
        int sumoqueue = Practice.i.manager.getLookingForIn(type, Kit.SUMO);
        int sumofight = Practice.i.manager.getFightsIn(ranked, Kit.SUMO);


        inv.setItem(0, Utils.createItem(PotionType.INSTANT_HEAL, 2, true, false, "§aNodebuff", Arrays.asList("§eQueue : " + nodebuffQueue, "§eMatch : " + nodebuffFight)));
        inv.setItem(1, Utils.createItem(PotionType.POISON, 1, true, true, "§aDebuff", Arrays.asList("§eQueue : " + debuffQueue, "§eMatch : " + debuffFight)));
        inv.setItem(2, Utils.createItem(Material.GOLDEN_APPLE, 1, "§aGApple", Arrays.asList("§eQueue : " + gappleQueue, "§eMatch : " + gappleFight)));
        inv.setItem(3, Utils.createItem(Material.LAVA_BUCKET, 0, "§aBuildUHC", Arrays.asList("§eQueue : " + builduhcQueue, "§eMatch : " + builduhcFight)));
        inv.setItem(4, Utils.createItem(Material.FENCE, 0, "§aSumo", Arrays.asList("§eQueue : " + sumoqueue, "§eMatch : " + sumofight)));
    }

    public void open() {
        player.openInventory(inv);
    }
}
