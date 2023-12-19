package com.example.aulaOpenFeign.Controller;

import com.example.aulaOpenFeign.Service.MusicaService;
import com.example.aulaOpenFeign.dto.MusicaCriarDto;
import com.example.aulaOpenFeign.dto.MusicaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musicas")
@RequiredArgsConstructor  //
public class MusicaController {

    private final MusicaService musicaService;

    @GetMapping
    public ResponseEntity <List<MusicaDto>> consulta(){

        List<MusicaDto> musicas = musicaService.cosulta();

        if (musicas.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(musicas);
    }

    @PostMapping
    public ResponseEntity<MusicaDto> criar(@RequestBody MusicaCriarDto musicaCriarDto){

        return ResponseEntity.ok(musicaService.criar(musicaCriarDto));

    }

}
