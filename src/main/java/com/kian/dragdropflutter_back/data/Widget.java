package com.kian.dragdropflutter_back.data;


import com.kian.dragdropflutter_back.data.components.*;
import com.kian.dragdropflutter_back.data.widgets.*;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Data
@Embeddable
public abstract class  Widget {

    protected Widget() {}
     static int x=0;
    public static Widget fromJson(Map<String, Object> widget) throws Exception {

        if (widget == null) {
            return null;
        }
        if (widget.size() == 1) {
            Map.Entry<String, Object> firstEntry = widget.entrySet().iterator().next();
            String widgetName = firstEntry.getKey();
            HashMap<String, Object> widgetConfigAsObject = objectToLinkedHashMapOfObject(firstEntry.getValue());

            if (Objects.equals(widgetName, "Container")) {
                return new Container(widgetConfigAsObject);
            } else if (Objects.equals(widgetName, "Image")) {
                return new Image(widgetConfigAsObject);
            } else if (Objects.equals(widgetName, "AppBar")) {
                return new AppBar(widgetConfigAsObject);
            } else if (Objects.equals(widgetName, "Text")) {
                return new Text(widgetConfigAsObject);
            } else if (Objects.equals(widgetName, "ElevatedButton")) {
                return new ElevatedButton(widgetConfigAsObject);
            } else if (Objects.equals(widgetName, "Row")) {
                return new Row(widgetConfigAsObject);
            } else if (Objects.equals(widgetName, "Column")) {
                return new Column(widgetConfigAsObject);
            } else if (Objects.equals(widgetName, "TextField")) {
                return new TextField(widgetConfigAsObject);
            }
            throw new Exception("Unimplemented Widget:[" + widgetName + "]");
        }
        else if (widget.size() > 1) {
            String componentName = (String) widget.get("name");

            if (componentName == null) {
                throw new Exception("Bad Request");
            }

            if (Objects.equals(componentName, "Button")) {
                Object objectElement = widget.get("element");
                HashMap<String, Object> widgetConfigAsObject = objectToLinkedHashMapOfObject(objectElement);

                Object objectComponentSettings = widget.get("componentSettings");
                HashMap<String, Object> componentSettings = objectToLinkedHashMapOfObject(objectComponentSettings);

                return new ButtonComponent(widgetConfigAsObject, componentSettings);
            }
            if (Objects.equals(componentName, "Spacer")) {
                Object objectElement = widget.get("element");
                HashMap<String, Object> widgetConfigAsObject = objectToLinkedHashMapOfObject(objectElement);

                Object objectComponentSettings = widget.get("componentSettings");
                HashMap<String, Object> componentSettings = objectToLinkedHashMapOfObject(objectComponentSettings);

                return new SpacerComponent(widgetConfigAsObject, componentSettings);
            }
            if (Objects.equals(componentName, "Text")) {
                Object objectElement = widget.get("element");
                HashMap<String, Object> widgetConfigAsObject = objectToLinkedHashMapOfObject(objectElement);

                Object objectComponentSettings = widget.get("componentSettings");
                HashMap<String, Object> componentSettings = objectToLinkedHashMapOfObject(objectComponentSettings);

                return new TextComponent(widgetConfigAsObject, componentSettings);
            }
            if (Objects.equals(componentName, "Textarea")) {
                Object objectElement = widget.get("element");
                HashMap<String, Object> widgetConfigAsObject = objectToLinkedHashMapOfObject(objectElement);

                Object objectComponentSettings = widget.get("componentSettings");
                HashMap<String, Object> componentSettings = objectToLinkedHashMapOfObject(objectComponentSettings);

                return new TextFieldComponent(widgetConfigAsObject, componentSettings);
            }
            if (Objects.equals(componentName, "Picture")) {
                Object objectElement = widget.get("element");
                HashMap<String, Object> widgetConfigAsObject = objectToLinkedHashMapOfObject(objectElement);

                Object objectComponentSettings = widget.get("componentSettings");
                HashMap<String, Object> componentSettings = objectToLinkedHashMapOfObject(objectComponentSettings);

                return new ImageComponent(widgetConfigAsObject, componentSettings);
            }
            if (Objects.equals(componentName, "Checkbox")) {
                x++;
                Object objectElement = widget.get("element");
                HashMap<String, Object> widgetConfigAsObject = objectToLinkedHashMapOfObject(objectElement);
                Object objectComponentSettings = widget.get("componentSettings");
                HashMap<String, Object> componentSettings = objectToLinkedHashMapOfObject(objectComponentSettings);

                return new CheckBoxComponent(widgetConfigAsObject, componentSettings,x);
            }
            if (Objects.equals(componentName, "Radio buttons")) {
                Object objectElement = widget.get("element");
                HashMap<String, Object> widgetConfigAsObject = objectToLinkedHashMapOfObject(objectElement);
                List<Map<String, Object>> options = (List<Map<String, Object>>) widgetConfigAsObject.get("options");

                Object objectComponentSettings = widget.get("componentSettings");
                HashMap<String, Object> componentSettings = objectToLinkedHashMapOfObject(objectComponentSettings);
                return new RadioButtonComponent(widgetConfigAsObject,componentSettings, options);


                //return new RadioButtonComponent(widgetConfigAsObject, componentSettings);
            }
            if (Objects.equals(componentName, "AppBar")) {
                Object objectElement = widget.get("element");
                HashMap<String, Object> widgetConfigAsObject = objectToLinkedHashMapOfObject(objectElement);

                Object objectComponentSettings = widget.get("componentSettings");
                HashMap<String, Object> componentSettings = objectToLinkedHashMapOfObject(objectComponentSettings);

                return new AppBarComponent(widgetConfigAsObject, componentSettings);
            }


            if (Objects.equals(componentName, "columns")) {
                ArrayList<Object> objectElement = (ArrayList<Object>) widget.get("addedComponents");
                String type = (String) widget.get("column");
                Map<String, Object> jsonMap = new HashMap<>();
                jsonMap.put("children", objectElement);
                return new Roww(jsonMap, type);
            }



            //HashMap<String, Object> widgetConfigAsObject = objectToLinkedHashMapOfObject(objectElement);
                //return new Roww(widgetConfigAsObject);
            }

        throw new Exception("Unimplemented Component");
    }


    @SuppressWarnings("unchecked")
    protected static <T> HashMap<String, T> objectToLinkedHashMapOfObject(Object value) throws IllegalArgumentException {
        if (value instanceof HashMap<?, ?> potentialMap) {
            if (isLinkedHashMapWithAllStringKeys(potentialMap)) {
                return (HashMap<String, T>) potentialMap;
            } else {
                throw new IllegalArgumentException("Expected LinkedHashMap with String keys");
            }
        } else {
            throw new IllegalArgumentException("Expected an object of type LinkedHashMap");
        }
    }

    @SuppressWarnings("unchecked")
    protected static <T> List<T> objectToListOfObject(Object value) throws IllegalArgumentException {
        if (value instanceof List<?> potentialList) {
            return (List<T>) potentialList;
        } else {
            throw new IllegalArgumentException("Expected an object of type List");
        }
    }

    private static boolean isLinkedHashMapWithAllStringKeys(HashMap<?, ?> map) {
        for (Object key : map.keySet()) {
            if (!(key instanceof String)) {
                return false;
            }
        }
        return true;
    }

    public abstract String toDartAsString() throws Exception;
}
