package fr.faygwenn.practice.utils;

import fr.faygwenn.practice.Practice;
import org.bukkit.entity.Player;

public enum LangMessages {
    LANG_CHANGE("lang-change"),
    UNKNOWN_ARGUMENTS("unknown-arguments"),
    UNKNOWN_PLAYER("unknown-player"),
    IN_COMBAT("in-fight"),
    IN_COMBAT_SELF("in-fight-self"),
    REQUEST("request"),
    REQUEST_SELF("request-self"),
    NO_REQUEST("no-request"),
    INVITATION_ACCEPTED("invitation-accepted"),
    INVITATION_ACCEPTED_SELF("invitation-accepted-self"),
    SEARCHING_ARENA("searching-arena"),
    ARENA_FOUND("arena-found"),
    SEARCHING_PLAYER("searching-player"),
    PLAYER_FOUND("player-found"),
    START_COUNTDOWN("start-countdown"),
    START("start"),
    COMBAT_END("fight-end"),
    INVITE_SELF("invite-self"),
    SELECT_KIT("select-kit"),
    NO_EDITING("no-editing"),
    ELOS_WIN("elos-win"),
    ELOS("elos"),
    ELOS_OTHER("elos-other"),
    ENDERPEARL_DELAY("enderpearl-delay"),
    ENDERPEARL_READY("enderpearl-ready"),
    NOT_ENOUGTH_RANKED_PLAYERS("not-enougth-ranked-players"),
    CANCEL_SEARCH("cancel-search"),
    CANT_ENTER_BUILDUHC("cant-enter-builduhc"),

    KITS_INVENTORY_NAME("kits-inventory.name");

    private Message messageEN;
    private Message messageFR;

    private LangMessages(String path) {
        this.messageEN = Message.fromConfig(Practice.i.config.get(), "msg." + path + ".en");
        this.messageFR = Message.fromConfig(Practice.i.config.get(), "msg." + path + ".fr");
    }

    public Message getFor(Player player) {
        if (Practice.i.database.get().getString("lang." + player.getUniqueId().toString()).equals("fr"))
            return messageFR;
        else
            return messageEN;
    }
}
