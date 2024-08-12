package com.kian.dragdropflutter_back.data.components;

import com.kian.dragdropflutter_back.data.Widget;
import com.kian.dragdropflutter_back.data.widgets.Column;
import com.kian.dragdropflutter_back.data.widgets.SizedBoxShrink;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class Roww extends Widget {

    private List<Widget> children;
    private String type;

    public Roww(Map<String, Object> json, String type) throws Exception {
        this.type = type;

        final List<Widget> children = ((List<Object>) json.get("children")).stream().map(item -> {
            try {
                if(((ArrayList)item).isEmpty()){
                    return new SizedBoxShrink();
                }
                List<Widget> items =((ArrayList)item).stream().map(child -> {
                    try {
                        return  Widget.fromJson(objectToLinkedHashMapOfObject(child));
                    } catch (Exception e) {
                        return null;
                    }
                }).toList();
                return new Column(items);
            } catch (Exception e) {
                return null;
            }
        }).toList();

        if (children != null && children.contains(null)) {
            throw new Exception("Unimplemented Widget in Row's children");
        }
        this.children = children;
    }

    public Roww() {
    }

    @Override
    public String toDartAsString() throws Exception {
        StringBuilder stringBuilder = new StringBuilder("Row(");
        int x=1;
        int y=2;

        if (children != null) {

            stringBuilder.append(" \n" +
                    "  mainAxisAlignment: MainAxisAlignment.spaceEvenly,\n" +
                    "  children: <Widget>[");
            switch (type){
                case "2":
                    for (Widget widget : children) {
                        stringBuilder.append("Expanded(\n" +
                                "  child:");
                        stringBuilder.append(widget.toDartAsString());
                        stringBuilder.append(",),");
                    }
                    break;

                case "3":
                    for (Widget widget : children) {
                        stringBuilder.append("Expanded(\n" +
                                "  child:");
                        stringBuilder.append(widget.toDartAsString());
                        stringBuilder.append(",),");
                    }
                    break;
                case "4":
                    for (Widget widget : children) {
                        stringBuilder.append("Expanded(\n" +
                                "  child:");
                        stringBuilder.append(widget.toDartAsString());
                        stringBuilder.append(",),");
                    }
                    break;
                case "5":
                    for (Widget widget : children) {
                        stringBuilder.append("Expanded(\n" +
                                "  child:");
                        stringBuilder.append(widget.toDartAsString());
                        stringBuilder.append(",),");
                    }
                    break;
                case "2-1-3":
                    for (Widget widget : children) {
                        stringBuilder.append("Expanded(\n" +
                                "flex: " + x + ", child:");
                        stringBuilder.append(widget.toDartAsString());
                        stringBuilder.append(",),");
                        x++;
                    }
                    break;
                case "2-3-1":
                    for (Widget widget : children) {
                        stringBuilder.append("Expanded(\n" +
                                "flex: " + y + ", child:");
                        stringBuilder.append(widget.toDartAsString());
                        stringBuilder.append(",),");
                        y--;
                    }
                    break;
            }

            stringBuilder.append(']');
        }

        stringBuilder.append(")");
        stringBuilder.append('\n');
        return stringBuilder.toString();
    }
}
