package com.kian.dragdropflutter_back.data.components;

import com.kian.dragdropflutter_back.data.Widget;
import com.kian.dragdropflutter_back.data.widgets.AppBar;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class AppBarComponent extends Widget {

    String title;
    String backgroundColor;
    String textColor;
    ComponentSettings componentSettings;
    public AppBarComponent(Map<String, Object> json, Map<String, Object> componentSettings) throws Exception,  IllegalArgumentException {
        this.title = (String) json.get("title");
        this.backgroundColor = (String) json.get("background_color");
        this.textColor = (String) json.get("text_color");
        this.componentSettings = new ComponentSettings(componentSettings);
    }


    @Override
    public String toDartAsString() throws Exception {

        AppBar appBar =new AppBar();
        appBar.setTitle(title);
        appBar.setColeur(backgroundColor);
        appBar.setTextColor(textColor);
        componentSettings.setChild(appBar);

        //componentSettings.setChild(elevatedButton);
        System.out.println(componentSettings);
        return componentSettings.toDartAsString();
    }
}
