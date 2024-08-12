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
public class EdgeInsetsDirectional {

    private final Double start;

    private final Double top;

    private final Double end;

    private final Double bottom;

    public static EdgeInsetsDirectional fromJson(Map<String, Double> json){
        return new EdgeInsetsDirectional(
                json.get("start") != null ? json.get("start") : null,
                json.get("top") != null ? json.get("top") : null,
                json.get("end") != null ? json.get("end") : null,
                json.get("bottom") != null ? json.get("bottom") : null
        );
    }

    public String toDartAsString() {
        StringBuilder edgeInsetsDirectionalBuilder = new StringBuilder("EdgeInsetsDirectional.only(");

        if (start != null) {
            edgeInsetsDirectionalBuilder.append("start: ");
            edgeInsetsDirectionalBuilder.append(start);
            edgeInsetsDirectionalBuilder.append(",");
        }

        if (top != null) {
            edgeInsetsDirectionalBuilder.append("top: ");
            edgeInsetsDirectionalBuilder.append(top);
            edgeInsetsDirectionalBuilder.append(",");
        }

        if (end != null) {
            edgeInsetsDirectionalBuilder.append("end: ");
            edgeInsetsDirectionalBuilder.append(end);
            edgeInsetsDirectionalBuilder.append(",");
        }

        if (bottom != null) {
            edgeInsetsDirectionalBuilder.append("bottom: ");
            edgeInsetsDirectionalBuilder.append(bottom);
            edgeInsetsDirectionalBuilder.append(",");
        }

        edgeInsetsDirectionalBuilder.append(")");
        return edgeInsetsDirectionalBuilder.toString();
    }
}
