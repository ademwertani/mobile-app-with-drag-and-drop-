package com.kian.dragdropflutter_back.data.widgetUtils;


import lombok.*;

import java.util.Map;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Data
@ToString
@Setter
public class Style {

    private String color;

    private Double fontSize;



    public Style() {}

    public static Style fromJson(Map<String, Object > json){
        return new Style(
                json.get("color") != null ? (String) json.get("color") : null,
                json.get("size") != null ? ((Integer) json.get("size")).doubleValue() : null
        );

    }

    public String toDartAsString() {

        StringBuilder styleBuilder = new StringBuilder("style: TextStyle(");

        if (fontSize != null) {
            styleBuilder.append("fontSize: ");
            styleBuilder.append(fontSize);
            styleBuilder.append(",");
        }

        if (color != null) {
            styleBuilder.append("color: Color(0xFF");
            styleBuilder.append(color.replace("#", ""));
            styleBuilder.append("),");
        }


        styleBuilder.append(")");
        return styleBuilder.toString();
    }
}
