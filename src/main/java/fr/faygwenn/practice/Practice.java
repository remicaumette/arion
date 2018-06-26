package fr.faygwenn.practice;

import fr.faygwenn.practice.command.*;
import fr.faygwenn.practice.event.ClickEvents;
import fr.faygwenn.practice.event.Events;
import fr.faygwenn.practice.event.InteractEvents;
import fr.faygwenn.practice.util.ConfigFile;
import fr.faygwenn.practice.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Practice extends JavaPlugin implements Listener {

    public static Practice i;
    public Manager manager;
    public ArrayList<Player> build;
    public ArrayList<Player> specInv;

    public final ConfigFile config = new ConfigFile(this, "config.yml");
    public final ConfigFile database = new ConfigFile(this, "database.yml");

    @Override
    public void onEnable() {
        i = this;
        manager = new Manager();
        build = new ArrayList<Player>();
        specInv = new ArrayList<Player>();

        getCommand("duel").setExecutor(new DuelCommand());
        getCommand("accept").setExecutor(new AcceptCommand());
        getCommand("lang").setExecutor(new LangCommand());
        getCommand("arena").setExecutor(new ArenaCommand());
        getCommand("spawnset").setExecutor(new SetSpawnCommand());
        getCommand("kit1save").setExecutor(new SaveCommand());
        getCommand("elos").setExecutor(new ElosCommand());
        getCommand("build").setExecutor(this);
        getCommand("endfightseeinv").setExecutor(this);
        getCommand("settings").setExecutor(new SettingsCommand());
        getCommand("day").setExecutor(new TimeCommand());
        getCommand("night").setExecutor(new TimeCommand());

        Bukkit.getPluginManager().registerEvents(new Events(), this);
        Bukkit.getPluginManager().registerEvents(new ClickEvents(), this);
        Bukkit.getPluginManager().registerEvents(new InteractEvents(), this);
        Bukkit.getPluginManager().registerEvents(this, this);

        initializeItems();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cYou must fr a player to execute this command !");
            return false;
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("build")) {
            if (build.contains(player)) {
                build.remove(player);
                player.sendMessage("§cBuild off");
            } else {
                build.add(player);
                player.sendMessage("§aBuild on");
            }
        } else {
            Player target = Bukkit.getPlayer(args[0]);

            if (target == null)
                return false;

            player.openInventory(target.getInventory());

            if (!specInv.contains(player))
                specInv.add(player);
        }

        return true;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (specInv.contains((Player) event.getWhoClicked()))
            event.setCancelled(true);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (specInv.contains((Player) event.getPlayer()))
            specInv.remove((Player) event.getPlayer());
    }

    // On disable

    @SuppressWarnings("deprecation")
    @Override
    public void onDisable() {
        for (Player pl : Bukkit.getOnlinePlayers())
            manager.cancelPreviousSearch(pl);

        Bukkit.getScheduler().cancelTasks(this);
    }

    // Spawn items

    private ItemStack rankedItem;
    private ItemStack TeamsItem;
    private ItemStack unrankedItem;
    private ItemStack editkitItem;
    private ItemStack settingsItem;
    //	private ItemStack rulesItemEN;
    //	private ItemStack rulesItemFR;
    public ItemStack goldenHeadsItem;
    public ItemStack itemKitNormal;
    public ItemStack itemKitEdited;

    private void initializeItems() {
        rankedItem = Utils.createItem(Material.DIAMOND_SWORD, 0, "§3Ranked", null);
        TeamsItem = Utils.createItem(Material.NAME_TAG, 0, "§bTeams", null);
        unrankedItem = Utils.createItem(Material.IRON_SWORD, 0, "§bUnranked", null);
        settingsItem = Utils.createItem(Material.REDSTONE_TORCH_ON, 0, "§bOptions", null);
        editkitItem = Utils.createItem(Material.BOOK, 0, "§bEditKit", null);
        goldenHeadsItem = Utils.createItem(Material.GOLDEN_APPLE, 0, "§6GoldenHead", null);
        goldenHeadsItem.setAmount(3);

        //		ItemStack item = Utils.createItem(Material.WRITTEN_BOOK, 0, Message.format(config.get().getString("msg.items.rules.name.en")), null);
        //		BookMeta meta = (BookMeta) item.getItemMeta();
        //		meta.setTitle(Message.format(config.get().getString("msg.items.rules.name.en")));
        //		item.setItemMeta(meta);
        //		rulesItemEN = item;
        //
        //		item = Utils.createItem(Material.WRITTEN_BOOK, 0, Message.format(config.get().getString("msg.items.rules.name.fr")), null);
        //		meta = (BookMeta) item.getItemMeta();
        //		meta.setTitle(Message.format(config.get().getString("msg.items.rules.name.fr")));
        //		item.setItemMeta(meta);
        //		rulesItemFR = item;

        itemKitNormal = Utils.createItem(Material.BOOK, 0, "§aKit-Default", null);
        itemKitEdited = Utils.createItem(Material.BOOK, 0, "§aKit-Edited", null);
    }

    // Spawn inventory

    public void spawnInventory(Player player) {
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        player.getInventory().clear();
        player.updateInventory();

        //		if (database.get().getString("lang." + player.getUniqueId().toString()).equals("fr"))
        //			player.getInventory().setItem(0, rulesItemFR);
        //		else
        //			player.getInventory().setItem(0, rulesItemEN);

        player.getInventory().setItem(0, unrankedItem);
        unrankedItem.getItemMeta().spigot().setUnbreakable(true);
        player.getInventory().setItem(1, rankedItem);
        unrankedItem.getItemMeta().spigot().setUnbreakable(true);
        player.getInventory().setItem(4, TeamsItem);
        player.getInventory().setItem(7, settingsItem);
        player.getInventory().setItem(8, editkitItem);
        player.updateInventory();
    }

	/*@SuppressWarnings("deprecation")
	public void hide(Player player)
	{
		for (Player pl : Bukkit.getOnlinePlayers())
		{
			player.hidePlayer(pl);
			pl.hidePlayer(player);

			/*((CraftPlayer) pl).getHandle().playerConnection.sendPacket(PacketPlayOutPlayerInfo.addPlayer(((CraftPlayer) player).getHandle()));
			((CraftPlayer) player).getHandle().playerConnection.sendPacket(PacketPlayOutPlayerInfo.addPlayer(((CraftPlayer) pl).getHandle()));*/
	/*}
	}*/

    public void heal(Player player) {
        player.setFireTicks(0);
        player.setHealth(((Damageable) player).getMaxHealth());
    }

    {

    }


    {

        {


        }

        {

        }
    }

    private List<String> cloneAndReplace(List<String> list, Var... vars) {
        List<String> cloned = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);

            for (Var v : vars)
                str = str.replace(v.var, v.repl);

            cloned.add(str);
        }

        return cloned;
    }

    class Var {
        public final String var;
        public final String repl;

        public Var(String var, Object repl) {
            this.var = var;
            this.repl = String.valueOf(repl);
        }
    }
}
