package com.arionmc.arioncore.api;

import com.arionmc.arioncore.api.gui.ArionGuiManager;
import com.arionmc.arioncore.api.command.ArionCommandManager;
import com.arionmc.arioncore.api.player.ArionPlayerManager;
import com.arionmc.arioncore.api.scoreboard.ArionScoreboardManager;

import java.util.logging.Logger;

public class ArionApi {
    private static Impl impl;

    /**
     * @return Le logger.
     */
    public static Logger getLogger() {
        return impl.getLogger();
    }

    /**
     * @return Le gestionnaire des joueurs.
     */
    public static ArionPlayerManager getPlayerManager() {
        return impl.getPlayerManager();
    }

    /**
     * @return Le gestionnaire des commandes.
     */
    public static ArionCommandManager getCommandManager() {
        return impl.getCommandManager();
    }

    /**
     * @return Le gestionnaire des interfaces.
     */
    public static ArionGuiManager getGuiManager() {
        return impl.getGuiManager();
    }

    /**
     * @return Le gestionnaire des interfaces.
     */
    public static ArionScoreboardManager getScoreboardManager() {
        return impl.getScoreboardManager();
    }

    /**
     * Permet de définir l'implémentation de l'api.
     *
     * @param impl L'implémentation à utiliser.
     */
    public static void setImpl(Impl impl) {
        ArionApi.impl = impl;
    }

    public interface Impl {
        /**
         * @return Le logger.
         */
        Logger getLogger();

        /**
         * @return Le gestionnaire des joueurs.
         */
        ArionPlayerManager getPlayerManager();

        /**
         * @return Le gestionnaire des commandes.
         */
        ArionCommandManager getCommandManager();

        /**
         * @return Le gestionnaire des interfaces.
         */
        ArionGuiManager getGuiManager();

        /**
         * @return Le gestionnaire des scoreboards.
         */
        ArionScoreboardManager getScoreboardManager();
    }
}
