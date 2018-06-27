package net.arion.arioncore.api;

import net.arion.arioncore.api.command.PracticeCommandManager;
import net.arion.arioncore.api.player.PracticePlayerManager;

public class PracticeApi {
    private static Impl impl;

    public PracticePlayerManager getPlayerManager() {
        return impl.getPlayerManager();
    }

    public PracticeCommandManager getCommandManager() {
        return impl.getCommandManager();
    }

    public static void setImpl(Impl impl) {
        PracticeApi.impl = impl;
    }

    public interface Impl {
        PracticePlayerManager getPlayerManager();

        PracticeCommandManager getCommandManager();
    }
}
