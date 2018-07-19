package net.orion.orioncore.scoreboard;

import net.orion.orioncore.OrionCore;
import net.orion.orioncore.api.scoreboard.OrionScoreboard;
import net.orion.orioncore.api.scoreboard.OrionScoreboardManager;

public class OrionCoreScoreboardManager implements OrionScoreboardManager {
    private OrionCore plugin;

    public OrionCoreScoreboardManager(OrionCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void setScoreboard(OrionScoreboard scoreboard) {

    }
}
