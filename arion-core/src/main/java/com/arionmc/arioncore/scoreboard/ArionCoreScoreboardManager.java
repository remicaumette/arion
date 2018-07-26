package com.arionmc.arioncore.scoreboard;

import com.arionmc.arioncore.ArionCore;
import com.arionmc.arioncore.api.scoreboard.ArionScoreboard;
import com.arionmc.arioncore.api.scoreboard.ArionScoreboardManager;

public class ArionCoreScoreboardManager implements ArionScoreboardManager {
    private ArionCore plugin;

    public ArionCoreScoreboardManager(ArionCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void setScoreboard(ArionScoreboard scoreboard) {

    }
}
