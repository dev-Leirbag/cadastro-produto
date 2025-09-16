package produto.api.application.infra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import produto.api.application.infra.controller.exceptions.*;

@RestController
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<RestErrorMessage> mensagemErro(Exception ex, HttpStatus status){
        RestErrorMessage errorMessage = new RestErrorMessage(status, status.value(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, status);
    }

    @ExceptionHandler(NomeProdutoInvalidException.class)
    public ResponseEntity<RestErrorMessage> nomeProdutoInvalido(NomeProdutoInvalidException ex){
        return mensagemErro(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TipoProdutoInvalidException.class)
    public ResponseEntity<RestErrorMessage> tipoProdutoInvalido(TipoProdutoInvalidException ex){
        return mensagemErro(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PrecoInvalidException.class)
    public ResponseEntity<RestErrorMessage> precoInvalido(PrecoInvalidException ex){
        return mensagemErro(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuantidadeEstoqueInvalidException.class)
    public ResponseEntity<RestErrorMessage> quantidadeEstoqueInvalido(QuantidadeEstoqueInvalidException ex){
        return mensagemErro(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProdutoExistsException.class)
    public ResponseEntity<RestErrorMessage> produtoJaExiste(ProdutoExistsException ex){
        return mensagemErro(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProdutoNotFoundException.class)
    public ResponseEntity<RestErrorMessage> produtoNaoEncontrado(ProdutoNotFoundException ex){
        return mensagemErro(ex, HttpStatus.NOT_FOUND);
    }
}
