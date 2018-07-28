package com.arionmc.arioncore.api;

import com.arionmc.arioncore.api.command.CommandManager;
import com.arionmc.arioncore.api.display.DisplayManager;
import com.arionmc.arioncore.api.gui.GuiManager;
import com.arionmc.arioncore.api.nms.NmsWrapper;
import com.arionmc.arioncore.api.player.ArionPlayerManager;

import javax.sql.DataSource;
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
     * @return La connexion à la base de données.
     */
    public static DataSource getDataSource() {
        return impl.getDataSource();
    }

    /**
     * @return Le wrapper nms.
     */
    public static NmsWrapper getNmsWrapper() {
        return impl.getNmsWrapper();
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
    public static CommandManager getCommandManager() {
        return impl.getCommandManager();
    }

    /**
     * @return Le gestionnaire des interfaces.
     */
    public static GuiManager getGuiManager() {
        return impl.getGuiManager();
    }

    /**
     * @return Le gestionnaire des affichages.
     */
    public static DisplayManager getDisplayManager() {
        return impl.getDisplayManager();
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
         * @return La connexion à la base de données.
         */
        DataSource getDataSource();

        /**
         * @return Le wrapper nms.
         */
        NmsWrapper getNmsWrapper();

        /**
         * @return Le gestionnaire des joueurs.
         */
        ArionPlayerManager getPlayerManager();

        /**
         * @return Le gestionnaire des commandes.
         */
        CommandManager getCommandManager();

        /**
         * @return Le gestionnaire des interfaces.
         */
        GuiManager getGuiManager();

        /**
         * @return Le gestionnaire des affichages.
         */
        DisplayManager getDisplayManager();
    }
}
