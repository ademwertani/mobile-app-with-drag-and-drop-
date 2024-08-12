package com.kian.dragdropflutter_back.data.widgets;

import com.kian.dragdropflutter_back.data.Widget;
import com.kian.dragdropflutter_back.data.widgetUtils.EdgeInsetsDirectional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class Container extends Widget {

    private final String color;

    private final Double width;

    private final Double height;

    private Widget child;

    private final EdgeInsetsDirectional padding;

    public Container(Map<String, Object> json) throws Exception {
        this.color = (String) json.get("color");
        this.width = json.get("width") != null ? ((Integer) json.get("width")).doubleValue() : null;
        this.height = json.get("height") != null ? ((Integer) json.get("height")).doubleValue() : null;
        this.child = json.get("child") != null ? Widget.fromJson(objectToLinkedHashMapOfObject(json.get("child"))) : null;
        this.padding = json.get("padding") != null ? EdgeInsetsDirectional.fromJson(objectToLinkedHashMapOfObject(json.get("padding"))) : null;
    }

    @Override
    public String toDartAsString() throws Exception {
        StringBuilder stringBuilder = new StringBuilder("Container(");

        if (color != null) {
            stringBuilder.append("color: Color(0xFF");
            if (color.isEmpty()) {
                stringBuilder.append("FFFFFF");
            }
            else {
                stringBuilder.append(color.replace("#", ""));
            }

            stringBuilder.append("),");
        }

        if (padding != null) {
            stringBuilder.append("padding:");
            stringBuilder.append(padding.toDartAsString());
            stringBuilder.append(",");
            stringBuilder.append('\n');
        }

        if (width != null) {
            stringBuilder.append("width: ");
            stringBuilder.append(width);
            stringBuilder.append(",");
            stringBuilder.append('\n');
        }

        if (height != null) {
            stringBuilder.append("height: ");
            stringBuilder.append(height);
            stringBuilder.append(",");
            stringBuilder.append('\n');
        }

        if (child != null) {
            stringBuilder.append("child:");
            stringBuilder.append(child.toDartAsString());
        }

        stringBuilder.append(")");
        stringBuilder.append('\n');
        return stringBuilder.toString();
    }
}
