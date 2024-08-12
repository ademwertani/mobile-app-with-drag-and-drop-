package com.kian.dragdropflutter_back.data.widgets;
import com.kian.dragdropflutter_back.data.Widget;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class TextField extends Widget {

    private  String labelText;
    private  String hintText;
    //private  String border;

    public TextField() {

    }
    public TextField(Map<String, Object> json) {
        this.labelText = (String) json.get("labelText");
        this.hintText = (String) json.get("hintText");
        //this.border = (String) json.get("border");
    }

    @Override
    public String toDartAsString() {
        StringBuilder stringBuilder = new StringBuilder("TextField(decoration: InputDecoration(");

        if (labelText != null) {
            stringBuilder.append("labelText: '");
            stringBuilder.append(labelText);
            stringBuilder.append("',");
            stringBuilder.append('\n');
        }
        if (hintText != null) {
            stringBuilder.append("hintText: '");
            stringBuilder.append(hintText);
            stringBuilder.append("',");
            stringBuilder.append('\n');
        }
        /*
        if (border != null) {
            stringBuilder.append("border: ");
            stringBuilder.append(border);
            stringBuilder.append("(),");

        }
*/
        stringBuilder.append("),)");
        stringBuilder.append('\n');
        return stringBuilder.toString();
    }
}
