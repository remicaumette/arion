package com.arionmc.arioncore.display;

import com.arionmc.arioncore.api.display.ArionChatFormatter;
import com.arionmc.arioncore.api.display.ArionDisplayManager;
import com.arionmc.arioncore.api.display.ArionNametagFormatter;
import com.arionmc.arioncore.api.display.ArionScoreboard;

public class ArionCoreDisplayManager implements ArionDisplayManager {
    private ArionScoreboard scoreboard;
    private ArionNametagFormatter nametagFormatter;
    private ArionChatFormatter chatFormatter;

    public ArionCoreDisplayManager() {
    }

    public ArionScoreboard getScoreboard() {
        return scoreboard;
    }

    @Override
    public void setScoreboard(ArionScoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public ArionNametagFormatter getNametagFormatter() {
        return nametagFormatter;
    }

    @Override
    public void setNametagFormatter(ArionNametagFormatter nametagFormatter) {
        this.nametagFormatter = nametagFormatter;
    }

    public ArionChatFormatter getChatFormatter() {
        return chatFormatter;
    }

    @Override
    public void setChatFormatter(ArionChatFormatter chatFormatter) {
        this.chatFormatter = chatFormatter;
    }
}
