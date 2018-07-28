package com.arionmc.arioncore.api.display;

public interface DisplayManager {
    /**
     * Défini le scoreboard.
     *
     * @param scoreboard Le scoreboard à utiliser.
     */
    void setScoreboard(Scoreboard scoreboard);

    /**
     * Défini le format du nametag.
     *
     * @param nametagFormatter Le format du nametag.
     */
    void setNametagFormatter(NametagFormatter nametagFormatter);

    /**
     * Défini le format du chat.
     *
     * @param chatFormatter Le format du chat.
     */
    void setChatFormatter(ChatFormatter chatFormatter);
}
