package com.arionmc.arioncore.api.display.defaults;

import com.arionmc.arioncore.api.display.ChatFormatter;
import com.arionmc.arioncore.api.lang.Lang;
import com.arionmc.arioncore.api.player.ArionPlayer;

public class RankChatFormatter implements ChatFormatter {
    @Override
    public String formatChat(ArionPlayer sender, ArionPlayer receiver, String message) {
        Lang lang = receiver.getLang();

        return sender.getRank().getColor() + lang.tl("ranks." + sender.getRank().name().toLowerCase()) + " "
                + sender.getName() + " » " + message;
    }
}
