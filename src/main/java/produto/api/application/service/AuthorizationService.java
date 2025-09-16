package produto.api.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import produto.api.adapters.in.dto.clients.UsuarioResponseDTO;
import produto.api.application.infra.clients.UsuarioClient;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final UsuarioClient client;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. Busca os dados do usuário na outra API
        UsuarioResponseDTO usuario;
        try {
            usuario = client.buscaUsuarioPorEmail(email);
        } catch (Exception e) {
            // Se o Feign client falhar (ex: 404 Not Found), lança a exceção padrão.
            throw new UsernameNotFoundException("Erro ao buscar usuário: " + email, e);
        }

        // 2. Validação Robusta da Resposta
        if (usuario == null || usuario.email() == null || usuario.senha() == null) {
            throw new UsernameNotFoundException("Usuário não encontrado ou dados inválidos: " + email);
        }

        // 3. Cria a lista de permissões (authorities)
        List<SimpleGrantedAuthority> authorities;
        if (usuario.role() != null && usuario.role().getRole() != null && !usuario.role().getRole().isEmpty()) {
            authorities = List.of(new SimpleGrantedAuthority(usuario.role().getRole()));
        } else {
            // Se não houver role, retorna uma lista vazia para não quebrar o construtor do User
            authorities = Collections.emptyList();
        }

        // 4. Cria e retorna o objeto UserDetails que o Spring Security entende
        return new User(usuario.email(), usuario.senha(), authorities);
    }
}
