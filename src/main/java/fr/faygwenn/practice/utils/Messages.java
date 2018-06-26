package fr.faygwenn.practice.utils;

public enum Messages {
    DEFINE_SPAWN("&aThe spawn location has been defined !"),
    DEFINE_ARENA("&aThe arena {arena} &ahas been defined !"),
    REMOVE_ARENA("&cThe arena {arena} point {point} &chas been removed !"),
    LIST_ARENAS("&aArenas :"),
    NO_ARENAS("&cThere are no arenas defined !"),
    NO_PERMISSION("&cYou don't have the permission to do that ! :("),
    UNKNOWN_ERROR_CANCEL_FIGHT("&cUne erreur inattendue est survenue, aucun gagnant n'a pu être désigné.");

    private Message message;

    private Messages(String text) {
        this.message = new Message(text);
    }

    public Message get() {
        return message;
    }
}
