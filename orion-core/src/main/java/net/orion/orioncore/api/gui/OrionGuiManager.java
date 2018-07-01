package net.orion.orioncore.api.gui;

import net.orion.orioncore.api.player.OrionPlayer;

public interface OrionGuiManager {
    void registerGui(OrionGui gui);

    void openGui(Class<? extends OrionGui> gui, OrionPlayer player);
}
