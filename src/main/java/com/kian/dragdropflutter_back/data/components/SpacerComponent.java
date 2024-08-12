package com.kian.dragdropflutter_back.data.components;

import com.kian.dragdropflutter_back.data.Widget;
import com.kian.dragdropflutter_back.data.widgets.Image;
import com.kian.dragdropflutter_back.data.widgets.Spacer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class SpacerComponent extends Widget {
    private int height;

    ComponentSettings componentSettings;
    public SpacerComponent(Map<String, Object> json, Map<String, Object> componentSettings) throws Exception,  IllegalArgumentException {
        this.height = (int) json.get("height");
        this.componentSettings = new ComponentSettings(componentSettings);
    }


    @Override
    public String toDartAsString() throws Exception {
        Spacer spacer = new Spacer();
        spacer.setHeight(height);
        componentSettings.setChild(spacer);
        return componentSettings.toDartAsString();
    }
}
