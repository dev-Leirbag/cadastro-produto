package produto.api.application.infra.controller.exceptions;

public class QuantidadeEstoqueInvalidException extends RuntimeException {
    public QuantidadeEstoqueInvalidException(String message) {
        super(message);
    }
}
