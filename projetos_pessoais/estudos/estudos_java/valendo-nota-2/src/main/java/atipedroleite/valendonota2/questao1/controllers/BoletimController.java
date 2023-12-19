package atipedroleite.valendonota2.questao1.controllers;

import atipedroleite.valendonota2.questao1.classes.Boletim;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/boletins")
public class BoletimController {

    List<Boletim> lista;
    public BoletimController() {
        this.lista = new ArrayList<>();
        this.lista.add(new Boletim("Reis", 10.0,  8.0));
        this.lista.add(new Boletim("Diego", 7.0,  5.0));
    }

    @GetMapping
    public List<Boletim> listaBoletins(){
        return lista;
    }

    @GetMapping("/{indice}")
    public Boletim boletimPorIndice(@PathVariable int indice){

        if(indice >= 0 && indice < lista.size()){

            return lista.get(indice);
        }else{
            return null;
        }
    }

    @DeleteMapping("/{indice}")
    public String deletarPorIndice(@PathVariable int indice){

        if(indice >= 0 && indice < lista.size()){

            lista.remove(indice);

            return "Excluido!";
        }else{
            return "Deu ruim!";
        }
    }

    @PostMapping
    public Boletim addBoletim(@RequestBody Boletim boletim){

        lista.add(boletim);
        return boletim;
    }

    @GetMapping("/contagem")
    public int getContagem(){

        return lista.size();
    }

    @GetMapping("/melhores")
    public Boletim boletimPorIndice() {

        Boletim melhorBoletim = null;
        double aux = 0.0;
        for (Boletim boletim : lista) {

            if (boletim.getMedia() > aux) {
                aux = boletim.getMedia();
                melhorBoletim = boletim;
            }

        }

        return melhorBoletim;
    }





}
