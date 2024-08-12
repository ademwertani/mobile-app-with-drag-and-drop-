package com.kian.dragdropflutter_back.data.components;
import com.kian.dragdropflutter_back.data.Widget;
import com.kian.dragdropflutter_back.data.widgetUtils.Style;
import com.kian.dragdropflutter_back.data.widgetUtils.StyleButton;
import com.kian.dragdropflutter_back.data.widgets.ElevatedButton;
import com.kian.dragdropflutter_back.data.widgets.Text;
import com.kian.dragdropflutter_back.data.widgets.TextField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class TextFieldComponent extends Widget{

    private final String labelText;
    private final String hintText;
    ComponentSettings componentSettings;

    public TextFieldComponent(Map<String, Object> json, Map<String, Object> componentSettings) throws Exception,  IllegalArgumentException {
        this.labelText = (String) json.get("label");
        this.hintText= (String) json.get("placeholder_text");
        this.componentSettings = new ComponentSettings(componentSettings);
    }


    @Override
    public String toDartAsString() throws Exception {
        TextField textField = new TextField();

        textField.setLabelText(labelText);
        textField.setHintText(hintText);



        componentSettings.setChild(textField);
        System.out.println(componentSettings);
        return componentSettings.toDartAsString();
    }
}
