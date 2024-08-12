package com.kian.dragdropflutter_back.Repository;
import com.kian.dragdropflutter_back.data.Json.JsonCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JsonRepository extends JpaRepository<JsonCode, Long> {
}