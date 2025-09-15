package produto.api.application.infra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import produto.api.adapters.in.dto.clients.UsuarioResponseDTO;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("usuario/buscar/{email}")
    UsuarioResponseDTO buscaUsuarioPorEmail(@PathVariable("email") String email);

}
