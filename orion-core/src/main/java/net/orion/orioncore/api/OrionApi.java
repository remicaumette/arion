package net.orion.orioncore.api;

import net.orion.orioncore.api.command.OrionCommandManager;
import net.orion.orioncore.api.gui.OrionGuiManager;
import net.orion.orioncore.api.player.OrionPlayerManager;
import net.orion.orioncore.api.scoreboard.OrionScoreboardManager;

import java.util.logging.Logger;

public class OrionApi {
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
    public static OrionPlayerManager getPlayerManager() {
        return impl.getPlayerManager();
    }

    /**
     * @return Le gestionnaire des commandes.
     */
    public static OrionCommandManager getCommandManager() {
        return impl.getCommandManager();
    }

    /**
     * @return Le gestionnaire des interfaces.
     */
    public static OrionGuiManager getGuiManager() {
        return impl.getGuiManager();
    }

    /**
     * @return Le gestionnaire des interfaces.
     */
    public static OrionScoreboardManager getScoreboardManager() {
        return impl.getScoreboardManager();
    }

    /**
     * Permet de définir l'implémentation de l'api.
     *
     * @param impl L'implémentation à utiliser.
     */
    public static void setImpl(Impl impl) {
        OrionApi.impl = impl;
    }

    public interface Impl {
        /**
         * @return Le logger.
         */
        Logger getLogger();

        /**
         * @return Le gestionnaire des joueurs.
         */
        OrionPlayerManager getPlayerManager();

        /**
         * @return Le gestionnaire des commandes.
         */
        OrionCommandManager getCommandManager();

        /**
         * @return Le gestionnaire des interfaces.
         */
        OrionGuiManager getGuiManager();

        /**
         * @return Le gestionnaire des scoreboards.
         */
        OrionScoreboardManager getScoreboardManager();
    }
}
