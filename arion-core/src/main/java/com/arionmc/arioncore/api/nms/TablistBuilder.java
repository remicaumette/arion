package com.arionmc.arioncore.api.nms;

public class TablistBuilder {
    private String header;
    private String footer;

    public TablistBuilder() {
    }

    /**
     * Défini le texte en haut de la tablist.
     *
     * @param header Le texte en haut de la tablist.
     * @return Le builder.
     */
    public TablistBuilder withHeader(String header) {
        this.header = header;
        return this;
    }

    /**
     * Défini texte en bas de la tablist.
     *
     * @param footer Le texte en bas de la tablist.
     * @return Le builder.
     */
    public TablistBuilder withFooter(String footer) {
        this.footer = footer;
        return this;
    }

    /**
     * @return La tablist.
     */
    public Tablist build() {
        if (header == null && footer == null) {
            throw new IllegalArgumentException("header or footer can't be empty!");
        }

        return new Tablist() {
            @Override
            public String getHeader() {
                return header;
            }

            @Override
            public String getFooter() {
                return footer;
            }
        };
    }
}
