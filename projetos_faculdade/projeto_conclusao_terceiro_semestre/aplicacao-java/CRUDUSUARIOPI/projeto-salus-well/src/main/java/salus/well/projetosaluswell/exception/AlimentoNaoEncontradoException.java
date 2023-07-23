package salus.well.projetosaluswell.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Alimento não encontrada")
public class AlimentoNaoEncontradoException extends RuntimeException{


}
