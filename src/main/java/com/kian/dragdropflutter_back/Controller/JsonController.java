package com.kian.dragdropflutter_back.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kian.dragdropflutter_back.data.Json.JsonCode;
import com.kian.dragdropflutter_back.services.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/json")
public class JsonController {

    @Autowired
    private JsonService jsonService;

    @PostMapping("/POST")
    public JsonCode saveJsonCode(@RequestBody Map<String, Object> jsonCode) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{  \"homePageRoute\": \"/home\",\n" +
                "  \"loginRoute\": \"/login\",\n" +
                "  \"signupRoute\": \"/signup\",\n" +
                "  \"pages\": [\n" +
                "    {\n" +
                "      \"route\": \"/home\",\n" +
                "      \"added_components\": [],\n" +
                "      \"form_design\": {\n" +
                "        \"fullWidth\": false,\n" +
                "        \"width\": 500,\n" +
                "        \"page_alignment\": \"top\",\n" +
                "        \"background_color\": \"#090D2B\",\n" +
                "        \"container_background_color\": \"#FFFFFF\",\n" +
                "        \"text_color\": \"#000000\",\n" +
                "        \"font_family\": \"'Helvetica Neue', Helvetica, sans-serif\",\n" +
                "        \"font_size\": 16,\n" +
                "        \"rounded_corners\": 8,\n" +
                "        \"page_paddings\": 15,\n" +
                "        \"form_paddings\": 0,\n" +
                "        \"field_rounded_corners\": 5,\n" +
                "        \"form_border_width\": 1,\n" +
                "        \"form_border_color\": \"#CED4DA\",\n" +
                "        \"field_background_color\": \"#FFFFFF\",\n" +
                "        \"field_border_width\": 1,\n" +
                "        \"field_border_color\": \"#CED4DA\",\n" +
                "        \"label_font_color\": \"#000000\",\n" +
                "        \"label_font_size\": 14,\n" +
                "        \"label_font_bold\": false,\n" +
                "        \"label_font_italic\": false,\n" +
                "        \"field_size\": \"m\",\n" +
                "        \"height\": 600\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        String name = jsonCode.get("name").toString();
        return jsonService.saveJsonCode(jsonString, name);
    }

    @GetMapping("/GET")
    public List<JsonCode> getAllJsonCodes() {
        return jsonService.getAllJsonCodes();
    }

    @GetMapping("/{id}")
    public String getJsonCodeById(@PathVariable Long id) {
        return jsonService.getJsonCodeById(id);
    }



    @PutMapping("/{id}")
    public JsonCode updateJsonCode(@PathVariable Long id, @RequestBody Map<String, Object> jsonCode) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(jsonCode);

        if (jsonCode.containsKey("name") && jsonCode.get("name") != null) {
            String name = jsonCode.get("name").toString();
            return jsonService.updateJsonCode(id, jsonString, name);
        } else {
            return jsonService.updateJsonCode2(id, jsonString);
        }
    }



    @DeleteMapping("/{id}")
    public void deleteJsonCode(@PathVariable Long id) {
        jsonService.deleteJsonCode(id);
    }
}