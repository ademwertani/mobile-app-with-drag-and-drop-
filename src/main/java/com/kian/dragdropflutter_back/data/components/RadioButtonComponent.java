package com.kian.dragdropflutter_back.data.components;

import com.kian.dragdropflutter_back.data.Widget;
import com.kian.dragdropflutter_back.data.widgetUtils.Style;
import com.kian.dragdropflutter_back.data.widgets.RadioButton;
import com.kian.dragdropflutter_back.services.InjectService;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class RadioButtonComponent extends Widget {

    private List<RadioButton> radioButtons = new ArrayList<>();
    ComponentSettings componentSettings;

    public RadioButtonComponent(Map<String, Object> json, Map<String, Object> componentSettings, List<Map<String, Object>> options) throws Exception, IllegalArgumentException {
        for (Map<String, Object> option : options) {
            String text = (String) option.get("text");
            String value = (String) option.get("value");

            RadioButton radioButton = new RadioButton();
            radioButton.setText(text);
            radioButton.setValue(value);

            radioButtons.add(radioButton);
        }

        this.componentSettings = new ComponentSettings(componentSettings);
    }

    @Override
    public String toDartAsString() throws Exception {
        StringBuilder dartCode = new StringBuilder();
        dartCode.append("Column(children: [");

        // Generate Dart code for each RadioButton and append it to dartCode
        for (RadioButton radioButton : radioButtons) {
            dartCode.append(radioButton.toDartAsString()).append("\n");
        }

        // Set the generated Dart code as a child of componentSettings
        componentSettings.setChild(new Widget() {
            @Override
            public String toDartAsString() {
                return dartCode.toString();
            }
        });
        dartCode.append("]),");

        // Return the Dart representation of componentSettings
        return componentSettings.toDartAsString();
    }
}
