package produto.api.application.infra.controller.exceptions;

public class TipoProdutoInvalidException extends RuntimeException {
    public TipoProdutoInvalidException(String message) {
        super(message);
    }
}
