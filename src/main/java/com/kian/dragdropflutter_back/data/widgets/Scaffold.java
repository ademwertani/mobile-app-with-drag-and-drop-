package com.kian.dragdropflutter_back.data.widgets;

import com.kian.dragdropflutter_back.data.Widget;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class Scaffold extends Widget {

    private final AppBar appBar;

    private final Widget body;
public  Scaffold(AppBar appBar,Widget body){
    this.appBar=appBar;
    this.body=body;
}
    public Scaffold(Map<String, Object> appBar, @NotNull(message = "body is mandatory") Map<String, Object> body) throws Exception {

        if (body == null) {
            throw new Exception("body is mandatory");
        }

            this.appBar = appBar != null ?new AppBar(objectToLinkedHashMapOfObject(appBar)):null ;

            if (body.size() != 1) {
                throw new Exception("Body require only 1 child");
            }
            this.body = Widget.fromJson(body);
        }

    @Override
    public String toDartAsString() throws Exception {
        StringBuilder stringBuilder = new StringBuilder("Scaffold(\n ");

        if (appBar != null) {
            stringBuilder.append("appbar:");
            stringBuilder.append(appBar.toDartAsString());
            stringBuilder.append(",");
            stringBuilder.append('\n');
        }
        stringBuilder.append("body: SingleChildScrollView(\n" +
                "   child: ");
        stringBuilder.append('\n');
        stringBuilder.append(body.toDartAsString());

        stringBuilder.append("));");
        stringBuilder.append('\n');
        return stringBuilder.toString();
    }
}
