package com.kian.dragdropflutter_back.data.widgetUtils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Data
@ToString
public class StyleButton {

    private String backgroundColor;

    public StyleButton() {}

    public static StyleButton fromJson(Map<String, String > json){
        return new StyleButton(
                json.get("backgroundColor") != null ? json.get("backgroundColor") : null

        );

    }

    public String toDartAsString() {
        StringBuilder stringBuilder = new StringBuilder("style: ElevatedButton.styleFrom(");

        if (backgroundColor != null) {
            stringBuilder.append("backgroundColor: Color(0xFF");
            stringBuilder.append(backgroundColor.replace("#", ""));
            stringBuilder.append("),");
            stringBuilder.append("shadowColor: Color(0xFF000000), // Ajoutez la couleur de l'ombre ici\n" +
                    "              shape: RoundedRectangleBorder(\n" +
                    "                borderRadius: BorderRadius.circular(0.0), // Définissez le rayon de bordure ici\n" +
                    "              ),\n");
        }


        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
