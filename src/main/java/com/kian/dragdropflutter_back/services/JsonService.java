package com.kian.dragdropflutter_back.services;


import com.kian.dragdropflutter_back.Repository.JsonRepository;
import com.kian.dragdropflutter_back.data.Json.JsonCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JsonService {

    @Autowired
    private JsonRepository jsonRepository;

    public JsonCode saveJsonCode(String code, String name) {
        JsonCode jsonEntity = new JsonCode();
        jsonEntity.setCode(code);
        jsonEntity.setName(name);
        return jsonRepository.save(jsonEntity);
    }

    public List<JsonCode> getAllJsonCodes() {
        return jsonRepository.findAll();
    }

    public String getJsonCodeById(Long id) {
        Optional<JsonCode> optionalJsonEntity = jsonRepository.findById(id);
        if (optionalJsonEntity.isPresent()) {
            JsonCode jsonEntity = optionalJsonEntity.get();
            String code = jsonEntity.getCode();
            if (code.startsWith("{\"code\":") && code.endsWith("}")) {
                // Extrait la valeur du code sans le {"code": au début et } à la fin
                code = code.substring(8, code.length() - 1);
            }
            return code;
        } else {
            throw new RuntimeException("JSON code not found with id " + id);
        }
    }



    public JsonCode updateJsonCode(Long id, String code, String name) {
        Optional<JsonCode> optionalJsonEntity = jsonRepository.findById(id);
        if (optionalJsonEntity.isPresent()) {
            JsonCode jsonEntity = optionalJsonEntity.get();
            if (code.startsWith("{\"code\":") && code.endsWith("}")) {
                code = code.substring(8, code.length() - 1);
            }
            jsonEntity.setCode(code);
            jsonEntity.setName(name);
            return jsonRepository.save(jsonEntity);
        } else {
            throw new RuntimeException("JSON code not found with id " + id);
        }
    }
    public JsonCode updateJsonCode2(Long id, String code) {
        Optional<JsonCode> optionalJsonEntity = jsonRepository.findById(id);
        if (optionalJsonEntity.isPresent()) {
            JsonCode jsonEntity = optionalJsonEntity.get();
            if (code.startsWith("{\"code\":") && code.endsWith("}")) {
                code = code.substring(8, code.length() - 1);
            }
            jsonEntity.setCode(code);
            return jsonRepository.save(jsonEntity);
        } else {
            throw new RuntimeException("JSON code not found with id " + id);
        }
    }



    public void deleteJsonCode(Long id) {
        jsonRepository.deleteById(id);
    }
}
