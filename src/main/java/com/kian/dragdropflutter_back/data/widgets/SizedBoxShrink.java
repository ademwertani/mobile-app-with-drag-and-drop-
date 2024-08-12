package com.kian.dragdropflutter_back.data.widgets;

import com.kian.dragdropflutter_back.data.Widget;
import com.kian.dragdropflutter_back.data.widgetUtils.EdgeInsetsDirectional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class SizedBoxShrink extends Widget {



    private Widget child;

    public SizedBoxShrink()  {

    }
    public SizedBoxShrink(Map<String, Object> json) throws Exception {
        this.child = json.get("child") != null ? Widget.fromJson(objectToLinkedHashMapOfObject(json.get("child"))) : null;
    }

    @Override
    public String toDartAsString() throws Exception {
        StringBuilder stringBuilder = new StringBuilder("SizedBox.shrink(");

        if (child != null) {
            stringBuilder.append("child:");
            stringBuilder.append(child.toDartAsString());
        }

        stringBuilder.append(")");
        stringBuilder.append('\n');
        return stringBuilder.toString();
    }
}
