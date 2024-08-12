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

public class Spacer extends Widget {
    private int height;
    public Spacer() {

    }

    public Spacer(Map<String, Object> json)  throws Exception{
        this.height = (int) json.get("url");
    }

    @Override
    public String toDartAsString() {
        StringBuilder stringBuilder = new StringBuilder("\n SizedBox(height: ");


        stringBuilder.append(height);
        stringBuilder.append("),");
        return stringBuilder.toString();
    }
}
