package com.arionmc.arioncore.api.player;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

public interface ArionPlayerManager {
    /**
     * @return Tout les joueurs connectés.
     */
    Collection<? extends ArionPlayer> getPlayers();

    /**
     * Permet de récupérer les données d'un joueur.
     *
     * @param player Le joueur "bukkit".
     * @return Les données du joueur.
     */
    ArionPlayer getPlayer(Player player);

    /**
     * Permet de récupérer les données d'un joueur.
     *
     * @param uniqueId L'UUID du joueur.
     * @return Les données du joueur.
     */
    ArionPlayer getPlayer(UUID uniqueId);
}
