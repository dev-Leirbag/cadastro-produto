package produto.api.adapters.in.dto.clients;

import produto.api.application.infra.clients.UsuarioRole;

import java.util.UUID;

public record UsuarioResponseDTO(UUID id, String email, String senha, UsuarioRole role) {
}
