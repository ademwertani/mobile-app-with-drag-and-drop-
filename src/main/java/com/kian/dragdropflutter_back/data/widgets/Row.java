package com.kian.dragdropflutter_back.data.widgets;

import com.kian.dragdropflutter_back.data.Widget;
import com.kian.dragdropflutter_back.data.widgetUtils.CrossAxisAlignment;
import com.kian.dragdropflutter_back.data.widgetUtils.MainAxisAlignment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class Row extends Widget {

    private   MainAxisAlignment mainAxisAlignment;

    private  CrossAxisAlignment crossAxisAlignment;

    private  List<Widget> children;


    public Row(Map<String, Object> json) throws Exception {
        final List<Widget> children = json.get("addedComponents") != null ? objectToListOfObject(json.get("addedComponents")).stream().map(item -> {
            try {
                return  Widget.fromJson(objectToLinkedHashMapOfObject(item));
            } catch (Exception e) {
                return null;
            }
        }).toList() : null;

        if (children != null && children.contains(null)) {
            throw new Exception("Unimplemented Widget in Row's children");
        }

        this.mainAxisAlignment = json.get("mainAxisAlignment") != null ? MainAxisAlignment.valueOf((String) json.get("mainAxisAlignment")) : null;
        this.crossAxisAlignment = json.get("crossAxisAlignment") != null ? CrossAxisAlignment.valueOf((String) json.get("crossAxisAlignment")) : null;
        this.children = children;
    }

    public Row() {

    }

    @Override
    public String toDartAsString() throws Exception {
        StringBuilder stringBuilder = new StringBuilder("Row(");

        if (mainAxisAlignment != null) {
            stringBuilder.append("mainAxisAlignment: ");
            stringBuilder.append(mainAxisAlignment.toDartAsString());
            stringBuilder.append(',');
            stringBuilder.append('\n');
        }

        if (crossAxisAlignment != null) {
            stringBuilder.append("crossAxisAlignment: ");
            stringBuilder.append(crossAxisAlignment.toDartAsString());
            stringBuilder.append(',');
            stringBuilder.append('\n');
        }

        if (children != null) {
            stringBuilder.append("children: [");

            for (Widget widget : children) {
                stringBuilder.append(widget.toDartAsString());
                stringBuilder.append(',');
            }
            stringBuilder.append(']');
        }

        stringBuilder.append(")");
        stringBuilder.append('\n');
        return stringBuilder.toString();
    }
}
