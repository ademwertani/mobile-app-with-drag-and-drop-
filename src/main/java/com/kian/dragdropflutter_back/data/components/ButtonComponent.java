package com.kian.dragdropflutter_back.data.components;


import com.kian.dragdropflutter_back.data.Widget;
import com.kian.dragdropflutter_back.data.widgetUtils.Style;
import com.kian.dragdropflutter_back.data.widgetUtils.StyleButton;
import com.kian.dragdropflutter_back.data.widgets.ElevatedButton;
import com.kian.dragdropflutter_back.data.widgets.Text;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class ButtonComponent extends Widget {

    String content;

    String link_to;

    String button_color;

    String text_color;

    Double font_size;


    ComponentSettings componentSettings;

    public ButtonComponent(Map<String, Object> json, Map<String, Object> componentSettings) throws Exception,  IllegalArgumentException {
        this.content = (String) json.get("content");
        this.link_to = (String) json.get("link_to");
        this.button_color = (String) json.get("button_color");
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

        prepareText.setText(content);

        ElevatedButton elevatedButton = new ElevatedButton();
        StyleButton styleButton = new StyleButton();

        styleButton.setBackgroundColor(button_color);

        elevatedButton.setChild(prepareText);
        elevatedButton.setStyleButton(styleButton);
        elevatedButton.setOnPressed(link_to);

        prepareText.setStyle(textStyle);

        componentSettings.setChild(elevatedButton);
        System.out.println(componentSettings);
        return componentSettings.toDartAsString();
    }
}
