package com.arionmc.arioncore.api.display.defaults;

import com.arionmc.arioncore.api.display.ArionChatFormatter;
import com.arionmc.arioncore.api.player.ArionPlayer;

public class RankChatFormatter implements ArionChatFormatter {
    @Override
    public String formatChat(ArionPlayer player, String message) {
        return player.getRank().getName() +" "+ player.getName() + ": "+ message;
    }
}
