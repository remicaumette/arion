package com.arionmc.arioncore.api.nms;

public interface Title {
    /**
     * @return Le titre.
     */
    String getTitle();

    /**
     * @return Le sous-titre.
     */
    String getSubtitle();

    /**
     * @return La durée de la transition d'entrée.
     */
    int getFadeIn();

    /**
     * @return La durée de l'affichage.
     */
    int getStay();

    /**
     * @return La durée de la transition de sortie.
     */
    int getFadeOut();
}
