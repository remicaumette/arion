package com.arionmc.arioncore.api.gui;

import com.arionmc.arioncore.api.player.ArionPlayer;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

public abstract class ArionGui {
    /**
     * Permet de définir le nom de l'inventaire par rapport à un joueur.
     *
     * @param player Le joueur.
     * @return Le nom de l'inventaire.
     */
    public String getName(ArionPlayer player) {
        return "Default name";
    }

    /**
     * Permet de définir la taille de l'inventaire par rapport à un joueur.
     *
     * @param player Le joueur.
     * @return La taille de l'inventaire.
     */
    public int getSize(ArionPlayer player) {
        return 9;
    }

    /**
     * Cette méthode est appelé quand l'inventaire s'ouvre.
     * Elle permet de définir le contenu de l'inventaire par rapport à un joueur.
     *
     * @param player    Le joueur.
     * @param inventory L'inventaire.
     */
    public void onCreate(ArionPlayer player, Inventory inventory) {
    }

    /**
     * Cette méthode est appelé quand un joueur clique dans l'inventaire.
     *
     * @param player    Le joueur.
     * @param inventory L'inventaire.
     * @param slot      La position à laquelle il a cliqué.
     * @param click     Le type de clique.
     */
    public void onClick(ArionPlayer player, Inventory inventory, int slot, ClickType click) {
    }

    /**
     * Cette méthode est appelé quand l'inventaire se ferme.
     *
     * @param player Le joueur.
     */
    public void onDestroy(ArionPlayer player) {
    }
}
