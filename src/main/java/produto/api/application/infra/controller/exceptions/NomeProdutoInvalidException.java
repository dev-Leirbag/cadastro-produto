package produto.api.application.infra.controller.exceptions;

public class NomeProdutoInvalidException extends RuntimeException {

    public NomeProdutoInvalidException(String message) {
        super(message);
    }
}
