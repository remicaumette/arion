package net.arion.arioncore.api.gui;

import net.arion.arioncore.api.player.ArionPlayer;

public interface ArionGuiManager {
    void registerGui(ArionGui gui);

    void openGui(Class<? extends ArionGui> gui, ArionPlayer player);
}
