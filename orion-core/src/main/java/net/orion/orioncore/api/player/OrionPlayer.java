package net.orion.orioncore.api.player;

import net.orion.orioncore.api.lang.Lang;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public interface OrionPlayer {
    /**
     * @return L'UUID du joueur.
     */
    UUID getUniqueId();

    /**
     * @return Le pseudo du joueur.
     */
    String getName();

    /**
     * @return La langue du joueur.
     */
    Lang getLang();

    /**
     * Permet de changer la langue du joueur.
     *
     * @param lang La nouvelle langue du joueur.
     */
    void setLang(Lang lang);

    /**
     * @return Le rang du joueur.
     */
    OrionPlayerRank getRank();

    /**
     * Permet de changer le rang du joueur.
     *
     * @param rank Le nouveau rang du joueur.
     */
    void setRank(OrionPlayerRank rank);

    /**
     * @return Quand le joueur s'est connecté.
     */
    Date getLastConnection();

    /**
     * @return Quand le joueur a rejoint pour la première fois le serveur.
     */
    Date getFirstConnection();

    /**
     * @return Contient les données temporaire du joueur.
     */
    Map<String, Object> getData();

    /**
     * @return Le joueur "bukkit".
     */
    Player getBukkitPlayer();

    /**
     * Permet d'envoyer un message à un joueur.
     *
     * @param message Le message à envoyer.
     */
    void sendRawMessage(String message);

    /**
     * Permet d'envoyer un message à un joueur mais traduit.
     *
     * @param key    La clef du message dans le fichier de traduction.
     * @param values Les éléments à remplacer.
     */
    void sendMessage(String key, String... values);
}
