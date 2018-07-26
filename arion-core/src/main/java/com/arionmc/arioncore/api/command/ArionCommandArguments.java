package com.arionmc.arioncore.api.command;

import com.arionmc.arioncore.api.command.exception.CommandException;
import com.arionmc.arioncore.api.player.ArionPlayer;

import java.util.List;

public interface ArionCommandArguments {
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
    ArionPlayer getPlayer(int index) throws CommandException;
}
