package sptech.school.nota02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @GetMapping
    public ResponseEntity<List<Carro>> listarCarros(){

        if (this.carroRepository.findAll().isEmpty()){

            return ResponseEntity.status(204).build();
        }else{
            return ResponseEntity.status(200).body(this.carroRepository.findAll());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> getCarroPotId(@PathVariable Long id){

        return ResponseEntity.of(this.carroRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteCarroPorId(@PathVariable Long id){

        if (carroRepository.existsById(id)){

            carroRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }else{

            return ResponseEntity.status(404).build();
        }
    }

}
