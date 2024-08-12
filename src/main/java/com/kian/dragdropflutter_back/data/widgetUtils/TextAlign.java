package com.kian.dragdropflutter_back.data.widgetUtils;

public enum TextAlign {
    start,
    end,
    center,
    justify;

    public String toDartAsString() {
        return "TextAlign." + this.name();
    }

}
