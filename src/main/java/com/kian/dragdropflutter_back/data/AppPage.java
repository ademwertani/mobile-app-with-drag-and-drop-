package com.kian.dragdropflutter_back.data;

import com.kian.dragdropflutter_back.data.widgets.Column;
import com.kian.dragdropflutter_back.data.widgets.Scaffold;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = false)
@Slf4j
@Data
@Getter
public class AppPage {

    @NotNull(message = "route is mandatory")
    private final String route;

    @NotNull(message = "appComponents is mandatory")
    private final Scaffold appComponents;

    public AppPage(@NotNull(message = "route is mandatory") String route, @NotNull(message = "scaffold is mandatory") Object added_components) throws Exception {
        this.route = route;
        Map<String, Object> prepareColumn = new HashMap<>();
        prepareColumn.put("children", added_components);
        this.appComponents = new Scaffold(null,new Column(prepareColumn));
    }

    public String generateCodeToInject() throws Exception {

        return "body = " + appComponents.toDartAsString() +
                ";";
    }
}