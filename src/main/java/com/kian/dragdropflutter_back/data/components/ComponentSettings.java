package com.kian.dragdropflutter_back.data.components;

import com.kian.dragdropflutter_back.data.Widget;
import com.kian.dragdropflutter_back.data.widgets.Container;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
@Setter
@Getter
public class ComponentSettings extends Widget {

    String background_color;

    Double padding_top;

    Double padding_right;

    Double padding_bottom;

    Double padding_left;

    Widget child;

    public ComponentSettings(Map<String, Object> json) throws Exception {
        this.background_color = (String) json.get("background_color");
        this.padding_top = json.get("padding_top") != null ? ((Integer) json.get("padding_top")).doubleValue() : null;
        this.padding_right = json.get("padding_right") != null ? ((Integer) json.get("padding_right")).doubleValue() : null;
        this.padding_bottom = json.get("padding_bottom") != null ? ((Integer) json.get("padding_bottom")).doubleValue() : null;
        this.padding_left = json.get("padding_left") != null ? ((Integer) json.get("padding_left")).doubleValue() : null;
        this.child = json.get("child") != null ? Widget.fromJson(objectToLinkedHashMapOfObject(json.get("child"))) : null;
    }

    @Override
    public String toDartAsString() throws Exception {
        Map<String, Object> prepareContainer = new HashMap<>();

        if (background_color != null) {
            prepareContainer.put("color", background_color);
        }

        if (padding_top != null && padding_right != null && padding_bottom != null && padding_left != null) {
            Map<String, Object> preparePadding = new HashMap<>();
            preparePadding.put("start", padding_left);
            preparePadding.put("end", padding_right);
            preparePadding.put("top", padding_top);
            preparePadding.put("bottom", padding_bottom);

            prepareContainer.put("padding", preparePadding);
        }

        Container containerPrepared = new Container(prepareContainer);

        if (child != null) {
            containerPrepared.setChild(child);
        }

        return containerPrepared.toDartAsString();
    }
}
