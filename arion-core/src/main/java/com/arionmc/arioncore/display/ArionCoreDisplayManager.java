package com.arionmc.arioncore.display;

import com.arionmc.arioncore.api.display.ChatFormatter;
import com.arionmc.arioncore.api.display.DisplayManager;
import com.arionmc.arioncore.api.display.NametagFormatter;
import com.arionmc.arioncore.api.display.Scoreboard;

public class ArionCoreDisplayManager implements DisplayManager {
    private Scoreboard scoreboard;
    private NametagFormatter nametagFormatter;
    private ChatFormatter chatFormatter;

    public ArionCoreDisplayManager() {
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    @Override
    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public NametagFormatter getNametagFormatter() {
        return nametagFormatter;
    }

    @Override
    public void setNametagFormatter(NametagFormatter nametagFormatter) {
        this.nametagFormatter = nametagFormatter;
    }

    public ChatFormatter getChatFormatter() {
        return chatFormatter;
    }

    @Override
    public void setChatFormatter(ChatFormatter chatFormatter) {
        this.chatFormatter = chatFormatter;
    }
}
