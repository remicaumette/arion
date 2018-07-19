package net.orion.orioncore.api.command;

import net.orion.orioncore.api.command.exception.CommandException;
import net.orion.orioncore.api.player.OrionPlayer;

import java.util.List;

public interface OrionCommandArguments {
    /**
     * @return Les arguments de la commande.
     */
    List<String> getArguments();

    /**
     * Permet de récupérer un argument.
     *
     * @param index La position de l'argument.
     * @return Sa valeur.
     * @throws CommandException Si l'argument n'existe pas.
     */
    String getString(int index) throws CommandException;

    /**
     * Permet de récupérer un joueur depuis un argument.
     *
     * @param index La position de l'argument.
     * @return Le joueur.
     * @throws CommandException Si le joueur est introuvable.
     */
    OrionPlayer getPlayer(int index) throws CommandException;
}
