package com.kian.dragdropflutter_back.data.widgets;

import com.kian.dragdropflutter_back.data.Widget;
import com.kian.dragdropflutter_back.data.widgetUtils.StyleButton;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class ElevatedButton extends Widget {

    private StyleButton styleButton;
    private String onPressed; // TODO onPressed as String must be changed to protect against xss attack

    private Widget child;

    public ElevatedButton () {}

    public ElevatedButton(Map<String, Object> json) throws Exception {
        if (json.get("onPressed") == null) {
            throw new Exception("onPressed is mandatory");
        }
        if (json.get("child") == null) {
            throw new Exception("child is mandatory");
        }
        this. onPressed = (String) json.get("onPressed");
        this.styleButton = json.get("StyleButton") != null ? StyleButton.fromJson(objectToLinkedHashMapOfObject(json.get("StyleButton"))) : null;
        this.child = Widget.fromJson(objectToLinkedHashMapOfObject(json.get("child")));
    }

    @Override
    public String toDartAsString() throws Exception {
        StringBuilder stringBuilder = new StringBuilder("ElevatedButton(");

        stringBuilder.append("onPressed: () => navigateTo('");
        stringBuilder.append(onPressed);
        stringBuilder.append("'),");
        stringBuilder.append('\n');
        if (styleButton != null) {

            stringBuilder.append(styleButton.toDartAsString());
            stringBuilder.append(',');
            stringBuilder.append('\n');
        }
        stringBuilder.append("child:");
        stringBuilder.append(child.toDartAsString());

        stringBuilder.append(")");
        stringBuilder.append('\n');
        return stringBuilder.toString();
    }
}
