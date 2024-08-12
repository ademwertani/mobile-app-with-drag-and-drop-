package com.kian.dragdropflutter_back.data.widgetUtils;

public enum MainAxisAlignment {
    start,
    end,
    center;

    public String toDartAsString() {
        return "MainAxisAlignment." + this.name();
    }
}
