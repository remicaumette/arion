package fr.faygwenn.practice.api;

import fr.faygwenn.practice.api.command.PracticeCommandManager;
import fr.faygwenn.practice.api.player.PracticePlayerManager;

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
