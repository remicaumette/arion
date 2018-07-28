package com.arionmc.arioncore.display;

import com.arionmc.arioncore.ArionCore;
import com.arionmc.arioncore.api.display.*;
import com.arionmc.arioncore.api.player.ArionPlayer;

public class ArionCoreDisplayManager implements DisplayManager {
    private ArionCore plugin;
    private ScoreboardFormatter scoreboardFormatter;
    private NametagFormatter nametagFormatter;
    private ChatFormatter chatFormatter;

    public ArionCoreDisplayManager(ArionCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void setScoreboardFormatter(ScoreboardFormatter scoreboardFormatter) {
        this.scoreboardFormatter = scoreboardFormatter;
    }

    @Override
    public void setNametagFormatter(NametagFormatter nametagFormatter) {
        this.nametagFormatter = nametagFormatter;
    }

    @Override
    public void setChatFormatter(ChatFormatter chatFormatter) {
        this.chatFormatter = chatFormatter;
    }

    public void onTick() {
        if (scoreboardFormatter != null) {
            plugin.getPlayerManager().getPlayers()
                    .forEach(player -> scoreboardFormatter.formatScoreboard(player, (Scoreboard) player.getData().get("scoreboard")));
        }
    }

    public void onLoad(ArionPlayer player) {
        player.getData().put("scoreboard", new ArionCoreScoreboard(plugin, player));
    }

    public void onQuit(ArionPlayer player) {
        ArionCoreScoreboard scoreboard = (ArionCoreScoreboard) player.getData().get("scoreboard");
        scoreboard.delete();
    }

    public void onChat(ArionPlayer sender, String message) {
        if (chatFormatter != null) {
            plugin.getLogger().info("Player " + sender.getName() + " says " + message);
            plugin.getPlayerManager()
                    .getPlayers()
                    .forEach(receiver -> receiver.sendRawMessage(chatFormatter.formatChat(sender, receiver, message)));
        } else {
            plugin.getLogger().warning("The chat formatter is not defined");
        }
    }
}
