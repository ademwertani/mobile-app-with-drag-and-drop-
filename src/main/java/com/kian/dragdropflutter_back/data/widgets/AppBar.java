package com.kian.dragdropflutter_back.data.widgets;

import com.kian.dragdropflutter_back.data.Widget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;


@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@AllArgsConstructor
public class AppBar extends Widget {

    private  String title;
    private  String coleur;
    private String textColor;

    public AppBar(Map<String, Object> json) throws Exception {
        this.title = (String) json.get("title");
        this.coleur = (String) json.get("background_color");
        this.textColor = (String) json.get("text_color");
    }

    public AppBar() {

    }

    @Override
    public String toDartAsString() throws Exception {
        StringBuilder stringBuilder = new StringBuilder("AppBar(");

        if (title != null) {
            stringBuilder.append("title: Text('");
            stringBuilder.append(title);
            stringBuilder.append("',");
        }
        if (textColor != null) {
            stringBuilder.append("style: TextStyle(color: Color(0xFF");
            stringBuilder.append(textColor.replace("#", ""));
            stringBuilder.append(")),");
            stringBuilder.append('\n');
            stringBuilder.append("),");

        }


        stringBuilder.append('\n');
        if (coleur != null) {
            stringBuilder.append("backgroundColor: Color(0xFF");
            stringBuilder.append(coleur.replace("#", ""));
            stringBuilder.append("),centerTitle: true");
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
