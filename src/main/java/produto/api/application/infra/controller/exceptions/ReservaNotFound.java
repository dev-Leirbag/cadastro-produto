package produto.api.application.infra.controller.exceptions;

public class ReservaNotFound extends RuntimeException {
    public ReservaNotFound(String message) {
        super(message);
    }
}
