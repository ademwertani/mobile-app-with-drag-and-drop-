package com.kian.dragdropflutter_back.data.components;

import com.kian.dragdropflutter_back.data.Widget;
import com.kian.dragdropflutter_back.data.widgetUtils.Style;
import com.kian.dragdropflutter_back.data.widgets.Text;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class TextComponent extends Widget {

    String content;

    String text_color;

    Double font_size;

    String text_align;
    ComponentSettings componentSettings;
    public TextComponent(Map<String, Object> json, Map<String, Object> componentSettings) throws Exception,  IllegalArgumentException {
        this.content = (String) json.get("content");
        this.text_color = (String) json.get("text_color");
        this.font_size = json.get("font_size") != null ? ((Integer) json.get("font_size")).doubleValue() : null;
        this.componentSettings = new ComponentSettings(componentSettings);
    }


    @Override
    public String toDartAsString() throws Exception {
        Text prepareText = new Text();
        Style textStyle = new Style();

        textStyle.setColor(text_color);
        textStyle.setFontSize(font_size);

        prepareText.setStyle(textStyle);
        prepareText.setText(content);
        componentSettings.setChild(prepareText);

        //componentSettings.setChild(elevatedButton);
        System.out.println(componentSettings);
        return componentSettings.toDartAsString();
    }
}
