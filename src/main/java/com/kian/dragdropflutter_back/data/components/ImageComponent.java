package com.kian.dragdropflutter_back.data.components;

import com.kian.dragdropflutter_back.data.Widget;
import com.kian.dragdropflutter_back.data.components.ComponentSettings;
import com.kian.dragdropflutter_back.data.widgetUtils.Style;
import com.kian.dragdropflutter_back.data.widgets.Image;
import com.kian.dragdropflutter_back.data.widgets.Text;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class ImageComponent extends Widget {

    private  String url;
    private int width;

    ComponentSettings componentSettings;
    public ImageComponent(Map<String, Object> json, Map<String, Object> componentSettings) throws Exception,  IllegalArgumentException {
        this.url = (String) json.get("image_link");
        this.width = (int) json.get("width");
        this.componentSettings = new ComponentSettings(componentSettings);
    }


    @Override
    public String toDartAsString() throws Exception {
        Image image = new Image();
        image.setUrl(url);
        image.setWidth(width);
        componentSettings.setChild(image);

        //componentSettings.setChild(elevatedButton);
        System.out.println(componentSettings);
        return componentSettings.toDartAsString();
    }
}
