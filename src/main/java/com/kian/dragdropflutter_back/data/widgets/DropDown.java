package com.kian.dragdropflutter_back.data.widgets;

import com.kian.dragdropflutter_back.data.Widget;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class DropDown extends Widget {

    private String text;
    private String value;

    public DropDown() {

    }

    public DropDown(Map<String, Object> json, Map<String, Object> componentSettings) throws Exception, IllegalArgumentException {
        this.text = (String) json.get("text");
        this.value = (String) json.get("value");
    }

    @Override
    public String toDartAsString() {

        StringBuilder stringBuilder = new StringBuilder("ListTile(");

        stringBuilder.append("title: const Text('");
        stringBuilder.append(text);
        stringBuilder.append("')");
        stringBuilder.append(',');

        stringBuilder.append("leading: Radio<String>(");
        stringBuilder.append("value: '");
        stringBuilder.append(value);
        stringBuilder.append("',");
        stringBuilder.append("groupValue: ");
        stringBuilder.append("_selectedValue");
        stringBuilder.append(',');
        stringBuilder.append("onChanged: (String? value) {");
        stringBuilder.append("setState(() {");
        stringBuilder.append("_selectedValue = value!;");
        stringBuilder.append("});");
        stringBuilder.append("},");
        stringBuilder.append("),");
        stringBuilder.append("),");



        return stringBuilder.toString();
    }
}
