package com.kian.dragdropflutter_back.services;

import com.kian.dragdropflutter_back.Repository.JsonRepository;
import com.kian.dragdropflutter_back.data.Json.JsonCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class JsonServiceTest {

    @Mock
    private JsonRepository jsonRepository;

    @InjectMocks
    private JsonService jsonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveJsonCode() {
        JsonCode jsonCode = new JsonCode(null, "code", "name");
        when(jsonRepository.save(any(JsonCode.class))).thenReturn(new JsonCode(1L, "code", "name"));

        JsonCode savedJsonCode = jsonService.saveJsonCode("code", "name");

        assertNotNull(savedJsonCode.getId());
        assertEquals("code", savedJsonCode.getCode());
        assertEquals("name", savedJsonCode.getName());
        verify(jsonRepository, times(1)).save(any(JsonCode.class));
    }

    @Test
    void getAllJsonCodes() {
        List<JsonCode> jsonCodes = Arrays.asList(
                new JsonCode(1L, "code1", "name1"),
                new JsonCode(2L, "code2", "name2")
        );
        when(jsonRepository.findAll()).thenReturn(jsonCodes);

        List<JsonCode> result = jsonService.getAllJsonCodes();

        assertEquals(2, result.size());
        assertEquals("code1", result.get(0).getCode());
        assertEquals("name1", result.get(0).getName());
        verify(jsonRepository, times(1)).findAll();
    }

    @Test
    void getJsonCodeById() {
        JsonCode jsonCode = new JsonCode(1L, "code", "name");
        when(jsonRepository.findById(1L)).thenReturn(Optional.of(jsonCode));

        String result = jsonService.getJsonCodeById(1L);

        assertEquals("code", result);
        verify(jsonRepository, times(1)).findById(1L);
    }

    @Test
    void updateJsonCode() {
        JsonCode jsonCode = new JsonCode(1L, "oldCode", "oldName");
        when(jsonRepository.findById(1L)).thenReturn(Optional.of(jsonCode));
        when(jsonRepository.save(any(JsonCode.class))).thenReturn(new JsonCode(1L, "newCode", "newName"));

        JsonCode updatedJsonCode = jsonService.updateJsonCode(1L, "newCode", "newName");

        assertEquals("newCode", updatedJsonCode.getCode());
        assertEquals("newName", updatedJsonCode.getName());
        verify(jsonRepository, times(1)).findById(1L);
        verify(jsonRepository, times(1)).save(any(JsonCode.class));
    }

    @Test
    void deleteJsonCode() {
        doNothing().when(jsonRepository).deleteById(1L);

        jsonService.deleteJsonCode(1L);

        verify(jsonRepository, times(1)).deleteById(1L);
    }
}
