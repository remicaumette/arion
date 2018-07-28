package com.arionmc.arioncore.api.display;

public interface Scoreboard {
    /**
     * @return Le nom du scoreboard.
     */
    String getName();

    /**
     * Défini le nom du scoreboard.
     *
     * @param name Le nom du scoreboard.
     */
    void setName(String name);

    /**
     * @return Les lignes du scoreboard.
     */
    String[] getLines();

    /**
     * Défini le contenu d'une ligne du scoreboard.
     *
     * @param line  La ligne a définir.
     * @param value Son contenu.
     */
    void setLine(int line, String value);
}
