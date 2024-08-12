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

public class Image extends Widget {

    private  String url;
    private int width;
    public Image() {

    }

    public Image(Map<String, Object> json)  throws Exception{
        if (json.get("url") == null) {
            throw new Exception("text is mandatory");
        }
        this.url = (String) json.get("url");
        this.width = (int) json.get("url");
    }

    @Override
    public String toDartAsString() {
        StringBuilder stringBuilder = new StringBuilder("Image.network(");

        if (url != null) {
            stringBuilder.append("'");
            stringBuilder.append(url);
            stringBuilder.append("',");
        }

            stringBuilder.append("width:");
            stringBuilder.append(width*3);
            stringBuilder.append(",");

            stringBuilder.append("height:");
            stringBuilder.append(width*3);
            stringBuilder.append(",");


        stringBuilder.append(")");
        stringBuilder.append('\n');
        return stringBuilder.toString();
    }
}
