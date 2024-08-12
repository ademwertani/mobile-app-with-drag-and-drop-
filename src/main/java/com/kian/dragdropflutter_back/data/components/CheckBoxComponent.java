package com.kian.dragdropflutter_back.data.components;
import com.kian.dragdropflutter_back.data.Widget;
import com.kian.dragdropflutter_back.data.widgetUtils.Style;
import com.kian.dragdropflutter_back.data.widgets.CheckBox;
import com.kian.dragdropflutter_back.data.widgets.Text;
import com.kian.dragdropflutter_back.services.InjectService;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class CheckBoxComponent extends Widget{

    private String text;

    ComponentSettings componentSettings;
    private int x;
    public CheckBoxComponent(Map<String, Object> json, Map<String, Object> componentSettings,int x) throws Exception,  IllegalArgumentException {
        this.text = (String) json.get("label");
        this.componentSettings = new ComponentSettings(componentSettings);
        this.x = x;
    }


    @Override
    public String toDartAsString() throws Exception {
        CheckBox checkBox = new CheckBox();
        checkBox.setX(x);
        checkBox.setText(text);
        componentSettings.setChild(checkBox);

        //componentSettings.setChild(elevatedButton);
        System.out.println(componentSettings);
        return componentSettings.toDartAsString();
    }
}
