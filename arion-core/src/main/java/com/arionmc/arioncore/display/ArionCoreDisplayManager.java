package com.arionmc.arioncore.display;

import com.arionmc.arioncore.ArionCore;
import com.arionmc.arioncore.api.display.ArionScoreboard;
import com.arionmc.arioncore.api.display.ArionDisplayManager;

public class ArionCoreDisplayManager implements ArionDisplayManager {
    private ArionCore plugin;

    public ArionCoreDisplayManager(ArionCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void setScoreboard(ArionScoreboard scoreboard) {

    }
}
