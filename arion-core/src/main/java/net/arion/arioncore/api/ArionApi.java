package net.arion.arioncore.api;

import net.arion.arioncore.api.command.ArionCommandManager;
import net.arion.arioncore.api.player.ArionPlayerManager;

import java.util.logging.Logger;

public class ArionApi {
    private static Impl impl;

    public static Logger getLogger() {
        return impl.getLogger();
    }

    public static ArionPlayerManager getPlayerManager() {
        return impl.getPlayerManager();
    }

    public static ArionCommandManager getCommandManager() {
        return impl.getCommandManager();
    }

    public static void setImpl(Impl impl) {
        ArionApi.impl = impl;
    }

    public interface Impl {
        Logger getLogger();

        ArionPlayerManager getPlayerManager();

        ArionCommandManager getCommandManager();
    }
}
