package com.example.aulaOpenFeign.integration;

import com.example.aulaOpenFeign.dto.MusicaCriarDto;
import com.example.aulaOpenFeign.dto.MusicaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "music-box", url = "https://633607188aa85b7c5d274257.mockapi.io/music-box")
public interface MusicaIntegration {

    @GetMapping
    List<MusicaDto> consulta();

    @PostMapping
    ResponseEntity<MusicaDto> criar(@RequestBody MusicaCriarDto musicaCriarDto);
}
