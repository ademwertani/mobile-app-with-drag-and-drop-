package com.kian.dragdropflutter_back.data.widgetUtils;

public enum CrossAxisAlignment {
    start,
    end,
    center;

    public String toDartAsString() {
        return "CrossAxisAlignment." + this.name();
    }
}
