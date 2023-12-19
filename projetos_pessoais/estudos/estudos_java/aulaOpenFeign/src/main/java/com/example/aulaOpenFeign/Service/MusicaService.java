package com.example.aulaOpenFeign.Service;

import com.example.aulaOpenFeign.dto.MusicaCriarDto;
import com.example.aulaOpenFeign.dto.MusicaDto;
import com.example.aulaOpenFeign.integration.MusicaIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicaService {

    private final MusicaIntegration musicaIntegration;

    public List<MusicaDto> cosulta(){

        return musicaIntegration.consulta();
    }

    public MusicaDto criar(MusicaCriarDto musicaCriarDto) {

        ResponseEntity<MusicaDto> musicaCriada = musicaIntegration.criar(musicaCriarDto);

        if(musicaCriada.getStatusCode().equals(HttpStatus.BAD_REQUEST)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return musicaCriada.getBody();
    }
}
