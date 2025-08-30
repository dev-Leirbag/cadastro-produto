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
import produto.api.adapters.in.dto.ProdutoDtoResponse;
import produto.api.adapters.in.mapper.Converter;
import produto.api.adapters.out.entities.ProdutoEntity;
import produto.api.application.domain.ProdutoDomain;
import produto.api.application.infra.controller.exceptions.*;
import produto.api.out.ProdutoRepository;

import java.math.BigDecimal;
import java.util.List;

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

    @Test
    @DisplayName("Não deve criar o produto caso o tipo do produto esteja vazio")
    void criaProdutoCase3(){
        ProdutoDtoRequest produtoDtoRequest = new ProdutoDtoRequest("Nome do Produto", "", new BigDecimal(10), 10);
        ProdutoDomain produtoDomain = new ProdutoDomain(1L,"Nome do Produto", "", new BigDecimal(10), 10);

        when(converter.dtoRequestParaDomain(produtoDtoRequest)).thenReturn(produtoDomain);
        when(repository.existisByProduto(produtoDomain.getNomeProduto())).thenReturn(false);

        TipoProdutoInvalidException exception = Assertions.assertThrows(TipoProdutoInvalidException.class, () -> {
            service.criaProduto(produtoDtoRequest);
        });

        assertThat(exception.getMessage()).isEqualTo("O tipo do produto não pode estar vazio!");

        verify(converter, times(1)).dtoRequestParaDomain(produtoDtoRequest);
        verify(repository, times(1)).existisByProduto(produtoDtoRequest.getNomeProduto());
        verify(repository, never()).salvaProduto(any(ProdutoDomain.class));

    }

    @Test
    @DisplayName("Não deve criar o produto caso o preço seja null ou menor que ZERO")
    void criaProdutoCase4(){
        ProdutoDtoRequest produtoDtoRequest = new ProdutoDtoRequest("Nome do Produto", "Tipo do Produto", new BigDecimal(0), 10);
        ProdutoDomain produtoDomain = new ProdutoDomain(1L,"Nome do Produto", "Tipo do Produto", new BigDecimal(0), 10);

        when(converter.dtoRequestParaDomain(produtoDtoRequest)).thenReturn(produtoDomain);
        when(repository.existisByProduto(produtoDomain.getNomeProduto())).thenReturn(false);

        PrecoInvalidException exception = Assertions.assertThrows(PrecoInvalidException.class, () -> {
           service.criaProduto(produtoDtoRequest);
        });

        assertThat(exception.getMessage()).isEqualTo("O preço não pode ser nulo ou abaixo de zero");

        verify(converter, times(1)).dtoRequestParaDomain(produtoDtoRequest);
        verify(repository, times(1)).existisByProduto(produtoDtoRequest.getNomeProduto());
        verify(repository, never()).salvaProduto(any(ProdutoDomain.class));
    }

    @Test
    @DisplayName("Não deve criar o produto caso a quantidade de estoque seja null ou menor que ZERO")
    void criaProdutoCase5(){
        ProdutoDtoRequest produtoDtoRequest = new ProdutoDtoRequest("Nome do Produto", "Tipo do Produto", new BigDecimal(10), 0);
        ProdutoDomain produtoDomain = new ProdutoDomain(1L,"Nome do Produto", "Tipo do Produto", new BigDecimal(10), 0);

        when(converter.dtoRequestParaDomain(produtoDtoRequest)).thenReturn(produtoDomain);
        when(repository.existisByProduto(produtoDomain.getNomeProduto())).thenReturn(false);

        QuantidadeEstoqueInvalidException exception = Assertions.assertThrows(QuantidadeEstoqueInvalidException.class, () ->{
           service.criaProduto(produtoDtoRequest);
        });

        assertThat(exception.getMessage()).isEqualTo("A quantidade não pode ser nula ou abaixo de zero");

        verify(converter, times(1)).dtoRequestParaDomain(produtoDtoRequest);
        verify(repository, times(1)).existisByProduto(produtoDomain.getNomeProduto());
        verify(repository, never()).salvaProduto(any(ProdutoDomain.class));
    }

    @Test
    @DisplayName("Deve retornar uma lista de produtos com sucesso")
    void listaProdutoCase1(){
        List<ProdutoDtoResponse> responseList = List.of(new ProdutoDtoResponse(1L,"Nome do Produto", "Tipo do Produto", new BigDecimal(10),10));
        List<ProdutoDomain> domainList = List.of(new ProdutoDomain(1L,"Nome do Produto", "Tipo do Produto", new BigDecimal(10),10));

        when(repository.listaProduto()).thenReturn(domainList);
        when(converter.domainParaDtoResponse(domainList)).thenReturn(responseList);

        var result = service.listaProduto();

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(responseList);

        verify(repository, times(1)).listaProduto();
        verify(converter, times(1)).domainParaDtoResponse(domainList);
    }

    @Test
    @DisplayName("Não deve retornar uma lista caso esteja vazio")
    void listaProdutoCase2(){
        List<ProdutoDomain> domainList = List.of();

        when(repository.listaProduto()).thenReturn(domainList);

        ProdutoNotFoundException exception = Assertions.assertThrows(ProdutoNotFoundException.class, () -> {
            service.listaProduto();
        });

        assertThat(exception.getMessage()).isEqualTo("Nenhum produto encontrado");

        verify(repository, times(1)).listaProduto();
        verify(converter, never()).domainParaDtoResponse(domainList);
    }

}