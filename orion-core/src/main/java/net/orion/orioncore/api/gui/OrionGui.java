package net.orion.orioncore.api.gui;

import net.orion.orioncore.api.player.OrionPlayer;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

public abstract class OrionGui {
    /**
     * Permet de définir le nom de l'inventaire par rapport à un joueur.
     *
     * @param player Le joueur.
     * @return Le nom de l'inventaire.
     */
    public String getName(OrionPlayer player) {
        return "Default name";
    }

    /**
     * Permet de définir la taille de l'inventaire par rapport à un joueur.
     *
     * @param player Le joueur.
     * @return La taille de l'inventaire.
     */
    public int getSize(OrionPlayer player) {
        return 9;
    }

    /**
     * Cette méthode est appelé quand l'inventaire s'ouvre.
     * Elle permet de définir le contenu de l'inventaire par rapport à un joueur.
     *
     * @param player    Le joueur.
     * @param inventory L'inventaire.
     */
    public void onCreate(OrionPlayer player, Inventory inventory) {
    }

    /**
     * Cette méthode est appelé quand un joueur clique dans l'inventaire.
     *
     * @param player    Le joueur.
     * @param inventory L'inventaire.
     * @param slot      La position à laquelle il a cliqué.
     * @param click     Le type de clique.
     */
    public void onClick(OrionPlayer player, Inventory inventory, int slot, ClickType click) {
    }

    /**
     * Cette méthode est appelé quand l'inventaire se ferme.
     *
     * @param player Le joueur.
     */
    public void onDestroy(OrionPlayer player) {
    }
}
