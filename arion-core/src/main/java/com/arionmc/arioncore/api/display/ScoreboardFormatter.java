package com.arionmc.arioncore.api.display;

import com.arionmc.arioncore.api.player.ArionPlayer;

public interface ScoreboardFormatter {
    /**
     * Formatte le scoreboard du joueur.
     *
     * @param player     Le joueur.
     * @param scoreboard Le scoreboard.
     */
    void formatScoreboard(ArionPlayer player, Scoreboard scoreboard);
}
