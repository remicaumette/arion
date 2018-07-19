package net.orion.orioncore.api.player;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

public interface OrionPlayerManager {
    /**
     * @return Tout les joueurs connectés.
     */
    Collection<? extends OrionPlayer> getPlayers();

    /**
     * Permet de récupérer les données d'un joueur.
     *
     * @param player Le joueur "bukkit".
     * @return Les données du joueur.
     */
    OrionPlayer getPlayer(Player player);

    /**
     * Permet de récupérer les données d'un joueur.
     *
     * @param uniqueId L'UUID du joueur.
     * @return Les données du joueur.
     */
    OrionPlayer getPlayer(UUID uniqueId);
}
