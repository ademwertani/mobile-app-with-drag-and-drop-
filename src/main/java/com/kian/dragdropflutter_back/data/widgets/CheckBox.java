package com.kian.dragdropflutter_back.data.widgets;

import com.kian.dragdropflutter_back.data.Widget;
import com.kian.dragdropflutter_back.data.components.ComponentSettings;
import com.kian.dragdropflutter_back.services.InjectService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CheckBox extends Widget{



    private String text;
    private int x;
    public CheckBox() {

    }

    public CheckBox(Map<String, Object> json, Map<String, Object> componentSettings,int x) throws Exception,  IllegalArgumentException  {

        this.text = (String) json.get("label");
    }

    @Override
    public String toDartAsString() {


        StringBuilder stringBuilder = new StringBuilder("CheckboxListTile(");

        stringBuilder.append("title: Text('");
        stringBuilder.append(text);
        stringBuilder.append("')");
        stringBuilder.append(',');


            stringBuilder.append("value: ");
            stringBuilder.append("isChecked").append(x);
            stringBuilder.append(',');
            //stringBuilder.append('\n');


        stringBuilder.append("onChanged: (bool? value) {\n" +
                "      setState(() {\n" +
                "        isChecked").append(x).append ("= value!;\n" +
                "      });\n" +
                "    },),");
        return stringBuilder.toString();
    }
}
