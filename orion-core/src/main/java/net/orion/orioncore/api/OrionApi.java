package net.orion.orioncore.api;

import net.orion.orioncore.api.command.OrionCommandManager;
import net.orion.orioncore.api.gui.OrionGuiManager;
import net.orion.orioncore.api.player.OrionPlayerManager;

import java.util.logging.Logger;

public class OrionApi {
    private static Impl impl;

    public static Logger getLogger() {
        return impl.getLogger();
    }

    public static OrionPlayerManager getPlayerManager() {
        return impl.getPlayerManager();
    }

    public static OrionCommandManager getCommandManager() {
        return impl.getCommandManager();
    }

    public static OrionGuiManager getGuiManager() {
        return impl.getGuiManager();
    }

    public static void setImpl(Impl impl) {
        OrionApi.impl = impl;
    }

    public interface Impl {
        Logger getLogger();

        OrionPlayerManager getPlayerManager();

        OrionCommandManager getCommandManager();

        OrionGuiManager getGuiManager();
    }
}
