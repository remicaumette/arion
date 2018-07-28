package com.arionmc.arioncore.api.nms;

public class TitleBuilder {
    private String title;
    private String subtitle;
    private int fadeIn;
    private int stay;
    private int fadeOut;

    public TitleBuilder() {
    }

    /**
     * Défini le titre.
     *
     * @param title Le titre.
     * @return Le builder.
     */
    public TitleBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Défini le sous-titre.
     *
     * @param subtitle Le sous-titre.
     * @return Le builder.
     */
    public TitleBuilder withSubtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    /**
     * Défini la durée de la transition d'entrée.
     *
     * @param fadeIn La durée de la transition d'entrée.
     * @return Le builder.
     */
    public TitleBuilder withFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
        return this;
    }

    /**
     * Défini la durée de l'affichage.
     *
     * @param stay La durée de l'affichage.
     * @return Le builder.
     */
    public TitleBuilder withStay(int stay) {
        this.stay = stay;
        return this;
    }

    /**
     * Défini la durée de la transition de sortie.
     *
     * @param fadeOut La durée de la transition de sortie.
     * @return Le builder.
     */
    public TitleBuilder withFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
        return this;
    }

    /**
     * @return Le title.
     */
    public Title build() {
        if (title == null && subtitle == null) {
            throw new IllegalArgumentException("title or subtitle can't be empty!");
        }

        return new Title() {
            @Override
            public String getTitle() {
                return title;
            }

            @Override
            public String getSubtitle() {
                return subtitle;
            }

            @Override
            public int getFadeIn() {
                return fadeIn;
            }

            @Override
            public int getStay() {
                return stay;
            }

            @Override
            public int getFadeOut() {
                return fadeOut;
            }
        };
    }
}
