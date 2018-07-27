package com.arionmc.arioncore.api.display;

public interface ArionDisplayManager {
    /**
     * Défini le scoreboard.
     *
     * @param scoreboard Le scoreboard à utiliser.
     */
    void setScoreboard(ArionScoreboard scoreboard);

    /**
     * Défini le format du nametag.
     *
     * @param nametagFormatter Le format du nametag.
     */
    void setNametagFormatter(ArionNametagFormatter nametagFormatter);

    /**
     * Défini le format du chat.
     *
     * @param chatFormatter Le format du chat.
     */
    void setChatFormatter(ArionChatFormatter chatFormatter);
}
