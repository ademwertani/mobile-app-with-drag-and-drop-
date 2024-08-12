package com.kian.dragdropflutter_back.data.widgets;

import com.kian.dragdropflutter_back.data.Widget;
import com.kian.dragdropflutter_back.data.widgetUtils.Style;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class Text extends Widget {

    private Style style;

    private String text;

    private String text_align;
    //private TextAlign textAlign;

    public Text() {

    }

    public Text(Map<String, Object> json) throws Exception {
        if (json.get("text") == null) {
            throw new Exception("text is mandatory");
        }
        this.text = (String) json.get("text");
        //this.textAlign = json.get("textAlign") != null ? TextAlign.valueOf((String) json.get("textAlign")) : null;
        this.style = json.get("Style") != null ? Style.fromJson(objectToLinkedHashMapOfObject(json.get("Style"))) : null;
        this.text_align = (String) json.get("alignment");
        this.text = (String) json.get("text");


    }

    @Override
    public String toDartAsString() {
        StringBuilder stringBuilder = new StringBuilder("Text(");

        stringBuilder.append('"');
        stringBuilder.append(text);
        stringBuilder.append('"');
        stringBuilder.append(',');

        if (text_align != null) {
            stringBuilder.append("textAlign: TextAlign. ");
            stringBuilder.append(text_align);
            stringBuilder.append(',');
            //stringBuilder.append('\n');
        }
        if (style != null) {
            stringBuilder.append(style.toDartAsString());
        }
        stringBuilder.append(")");
        stringBuilder.append('\n');
        return stringBuilder.toString();
    }
}
