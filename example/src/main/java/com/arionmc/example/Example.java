package com.arionmc.example;

import com.arionmc.arioncore.api.ArionApi;
import com.arionmc.arioncore.api.event.ArionPlayerLoadedEvent;
import com.arionmc.arioncore.api.lang.Lang;
import com.arionmc.arioncore.api.nms.*;
import com.arionmc.arioncore.api.player.ArionPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Lang.importTranslationFromPlugin(this);

        getServer().getPluginManager().registerEvents(this, this);

        ArionApi.getGuiManager().registerGui(new ExampleGui());
        ArionApi.getCommandManager().registerCommand(new ExampleCommand());
    }

    @EventHandler
    public void onLoad(ArionPlayerLoadedEvent event) {
        ArionPlayer player = event.getPlayer();
        Tablist tablist = new TablistBuilder()
                .withHeader("Bienvenue " + player.getName() + " !")
                .withFooter("Tu es sur ArionMC!")
                .build();
        Title title = new TitleBuilder()
                .withTitle("Bienvenue sur")
                .withSubtitle("§cDESPACITO 2")
                .withFadeIn(20)
                .withStay(120)
                .withFadeOut(60)
                .build();

        ArionApi.getNmsWrapper().sendTablist(player, tablist);
        ArionApi.getNmsWrapper().sendTitle(player, title);
        ArionApi.getNmsWrapper().sendActionbar(player, "§aHello world");
    }
}
