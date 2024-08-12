package com.kian.dragdropflutter_back.data.components;

import com.kian.dragdropflutter_back.data.Widget;
import com.kian.dragdropflutter_back.data.widgetUtils.CrossAxisAlignment;
import com.kian.dragdropflutter_back.data.widgetUtils.MainAxisAlignment;
import com.kian.dragdropflutter_back.data.widgetUtils.Style;
import com.kian.dragdropflutter_back.data.widgets.Row;
import com.kian.dragdropflutter_back.data.widgets.Text;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class RowComponent extends Widget {

    List<Widget> children;
    ComponentSettings componentSettings;

    public RowComponent(Map<String, Object> json, Map<String, Object> componentSettings) throws Exception,  IllegalArgumentException {
        final List<Widget> children = json.get("columns") != null ? objectToListOfObject(json.get("columns")).stream().map(item -> {
            try {
                return  Widget.fromJson(objectToLinkedHashMapOfObject(item));
            } catch (Exception e) {
                return null;
            }
        }).toList() : null;

        if (children != null && children.contains(null)) {
            throw new Exception("Unimplemented Widget in Row's children");
        }

        //this.mainAxisAlignment = json.get("mainAxisAlignment") != null ? MainAxisAlignment.valueOf((String) json.get("mainAxisAlignment")) : null;
        //this.crossAxisAlignment = json.get("crossAxisAlignment") != null ? CrossAxisAlignment.valueOf((String) json.get("crossAxisAlignment")) : null;
        this.children = children;
        this.componentSettings = new ComponentSettings(componentSettings);
    }


    @Override
    public String toDartAsString() throws Exception {
        Row row = new Row();
        row.setChildren(children);
        componentSettings.setChild(row);

        //componentSettings.setChild(elevatedButton);
        System.out.println(componentSettings);
        return componentSettings.toDartAsString();
    }
}
