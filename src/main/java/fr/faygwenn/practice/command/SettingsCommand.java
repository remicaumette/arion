package fr.faygwenn.practice.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SettingsCommand implements CommandExecutor {

    private final Inventory inventory;

    public SettingsCommand() {

        //Menu Settings

        this.inventory = Bukkit.createInventory(null, 3 * 9, "§bSettings");
        this.inventory.setItem(26, new ItemStack(Material.RED_ROSE));
        this.inventory.setItem(10, new ItemStack(Material.SIGN));
        this.inventory.setItem(11, new ItemStack(Material.ITEM_FRAME));
        this.inventory.setItem(12, new ItemStack(Material.PAPER));
        this.inventory.setItem(13, new ItemStack(Material.NETHER_STAR));
        this.inventory.setItem(14, new ItemStack(Material.DIAMOND_SWORD));
        this.inventory.setItem(15, new ItemStack(Material.COMPASS));
        this.inventory.setItem(16, new ItemStack(Material.GOLD_CHESTPLATE));

    }

    //Commande /settings
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (label.equalsIgnoreCase("settings")) {
            if (sender instanceof Player) {

                final Player player = (Player) sender;

                player.openInventory(inventory);
                return true;
            }

        } else {
            sender.sendMessage("You must be a player to execute this command !");
        }
        return false;
    }
}
