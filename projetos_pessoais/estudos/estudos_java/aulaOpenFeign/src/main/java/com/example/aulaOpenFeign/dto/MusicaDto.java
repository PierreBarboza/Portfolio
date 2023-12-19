package com.example.aulaOpenFeign.dto;

import lombok.Data;

@Data //Ele engloba get/set/ construtor --> Não precisamos mais fazer eles na mão Podemos usar notações como @Getter | @setter e por ai vai para decretar apenas oq queremos. Pois o @Data controi tudo
public class MusicaDto {

    private String id;
    private String nome;
    private String artista;
    private String genero;
    private String dataLancamento;

}
