package produto.api.application.infra.controller.exceptions;

public class ProdutoExistsException extends RuntimeException {
    public ProdutoExistsException(String message) {
        super(message);
    }
}
