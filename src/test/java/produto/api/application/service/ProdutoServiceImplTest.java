package produto.api.application.service;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import produto.api.adapters.in.dto.ProdutoDtoRequest;
import produto.api.adapters.in.mapper.Converter;
import produto.api.adapters.out.entities.ProdutoEntity;
import produto.api.application.domain.ProdutoDomain;
import produto.api.application.infra.controller.exceptions.ProdutoExistsException;
import produto.api.out.ProdutoRepository;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    @Mock
    private Converter converter;

    @Mock
    private ProdutoRepository repository;

    @Autowired
    @InjectMocks
    private ProdutoServiceImpl service;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Deve criar o produto com sucesso")
    void criaProdutoCase1() {
        //Preparação
        ProdutoDtoRequest produtoDtoRequest = new ProdutoDtoRequest("Nome do Produto", "Tipo do Produto", new BigDecimal(10), 10);
        ProdutoDomain produtoDomain = new ProdutoDomain(1L,"Nome do Produto", "Tipo do Produto", new BigDecimal(10), 10);

        when(converter.dtoRequestParaDomain(produtoDtoRequest)).thenReturn(produtoDomain);
        when(repository.existisByProduto(produtoDomain.getNomeProduto())).thenReturn(false);
        when(repository.salvaProduto(any(ProdutoDomain.class))).thenReturn(produtoDomain);
        when(converter.domainParaDtoRequest(produtoDomain)).thenReturn(produtoDtoRequest);

        //Execução
        var result = service.criaProduto(produtoDtoRequest);

        //Verificação
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(produtoDtoRequest);

        verify(converter, times(1)).dtoRequestParaDomain(produtoDtoRequest);
        verify(repository, times(1)).existisByProduto(produtoDtoRequest.getNomeProduto());
        verify(repository, times(1)).salvaProduto(any(ProdutoDomain.class));
        verify(converter, times(1)).domainParaDtoRequest(produtoDomain);
    }

    @Test
    @DisplayName("Não deve criar o produto com sucesso caso o produto já exista")
    void criaProdutoCase2(){
        ProdutoDtoRequest produtoDtoRequest = new ProdutoDtoRequest("Nome do Produto", "Tipo do Produto", new BigDecimal(10), 10);
        ProdutoDomain produtoDomain = new ProdutoDomain(1L,"Nome do Produto", "Tipo do Produto", new BigDecimal(10), 10);

        when(converter.dtoRequestParaDomain(produtoDtoRequest)).thenReturn(produtoDomain);
        when(repository.existisByProduto(produtoDomain.getNomeProduto())).thenReturn(true);

        ProdutoExistsException exception = Assertions.assertThrows(ProdutoExistsException.class, () -> {
            service.criaProduto(produtoDtoRequest);
        });

        assertThat(exception.getMessage()).isEqualTo("Esse produto já existe");

        verify(converter, times(1)).dtoRequestParaDomain(produtoDtoRequest);
        verify(repository, times(1)).existisByProduto(produtoDtoRequest.getNomeProduto());
        verify(repository, never()).salvaProduto(any(ProdutoDomain.class));

    }
}