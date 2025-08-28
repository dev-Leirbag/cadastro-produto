package produto.api.application.infra.controller.exceptions;

public class PrecoInvalidException extends RuntimeException {
    public PrecoInvalidException(String message) {
        super(message);
    }
}
