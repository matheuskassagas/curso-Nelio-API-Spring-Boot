package resources.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import service.exception.ObjectNotFounfException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
    //metodo padrao do controlleradvice...
    //classe auxiliar que intercpta as exceções
    //metodo recebe a excecao, "ObjectNotFounfException" e informações da requisição "HttpServletRequest"
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFounfException e, HttpServletRequest request){
        StandardError standardError = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

}
