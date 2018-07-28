package com.arionmc.arioncore.api.nms;

import com.arionmc.arioncore.api.player.ArionPlayer;

public interface NmsWrapper {
    /**
     * Envoi la tablist a un joueur.
     *
     * @param player  Joueur.
     * @param tablist Tablist.
     */
    void sendTablist(ArionPlayer player, Tablist tablist);

    /**
     * Envoi le title a un joueur.
     *
     * @param player Le joueur
     * @param title  Title.
     */
    void sendTitle(ArionPlayer player, Title title);

    /**
     * Envoi un message dans l'actionbar du joueur.
     *
     * @param player  Le joueur.
     * @param message Le message a envoyer.
     */
    void sendActionbar(ArionPlayer player, String message);

    /**
     * Envoi un packet objectif au joueur.
     *
     * @param player      Le joueur.
     * @param action      L'action a effectuer.
     * @param name        Le nom de l'objectif.
     * @param displayName Le nom a afficher.
     */
    void sendObjectivePacket(ArionPlayer player, ObjectiveAction action, String name, String displayName);

    /**
     * Envoi un packet pour définir le slot de l'objectif au joueur.
     *
     * @param player Le joueur.
     * @param name   Le nom de l'objectif.
     * @param slot   Le slot.
     */
    void sendObjectiveSlotPacket(ArionPlayer player, String name, int slot);

    /**
     * Envoi un packet pour définir le score du scoreboard au joueur.
     *
     * @param player Le joueur.
     * @param name   Le nom de l'objectif.
     * @param action L'action.
     * @param score  Le score.
     * @param line   La ligne.
     */
    void sendScoreboardScorePacket(ArionPlayer player, String name, ScoreboardScoreAction action, int score, String line);

    enum ObjectiveAction {
        CREATE,
        REMOVE,
        UPDATE
    }

    enum ScoreboardScoreAction {
        CHANGE,
        REMOVE
    }
}
