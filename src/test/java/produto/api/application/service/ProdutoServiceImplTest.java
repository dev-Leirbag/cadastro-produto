package produto.api.application.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import produto.api.adapters.in.dto.ProdutoDtoRequest;
import produto.api.adapters.in.dto.ProdutoDtoResponse;
import produto.api.adapters.in.mapper.Converter;
import produto.api.adapters.in.mapper.UpdateConverter;
import produto.api.adapters.out.entities.ProdutoEntity;
import produto.api.application.domain.ProdutoDomain;
import produto.api.application.infra.controller.exceptions.*;
import produto.api.out.ProdutoRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceImplTest {

    @Mock
    private Converter converter;

    @Mock
    private UpdateConverter updateConverter;

    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private ProdutoServiceImpl service;

    private ProdutoDtoRequest produtoDtoRequest;
    private ProdutoDomain produtoDomain;
    private ProdutoDtoResponse produtoDtoResponse;

    @BeforeEach
    void setUp(){
        produtoDtoRequest = new ProdutoDtoRequest("Nome do Produto", "Tipo do Produto", new BigDecimal(10), 10);
        produtoDomain = new ProdutoDomain(1L,"Nome do Produto", "Tipo do Produto", new BigDecimal(10), 10);
        produtoDtoResponse = new ProdutoDtoResponse(1L,"Nome do Produto", "Tipo do Produto", new BigDecimal(10),10);
    }

    @Test
    @DisplayName("Deve criar o produto com sucesso")
    void criaProdutoCase1() {
        //Preparação
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
        when(converter.dtoRequestParaDomain(produtoDtoRequest)).thenReturn(produtoDomain);
        when(repository.existisByProduto(produtoDomain.getNomeProduto())).thenReturn(true);

        ProdutoExistsException exception = Assertions.assertThrows(ProdutoExistsException.class, () -> service.criaProduto(produtoDtoRequest));

        assertThat(exception.getMessage()).isEqualTo("Esse produto já existe");

        verify(converter, times(1)).dtoRequestParaDomain(produtoDtoRequest);
        verify(repository, times(1)).existisByProduto(produtoDtoRequest.getNomeProduto());
        verify(repository, never()).salvaProduto(any(ProdutoDomain.class));
    }

    @Test
    @DisplayName("Não deve criar o produto caso o tipo do produto esteja vazio")
    void criaProdutoCase3(){
        ProdutoDtoRequest produtoDtoRequestVazio = new ProdutoDtoRequest("Nome do Produto", "", new BigDecimal(10), 10);
        ProdutoDomain produtoDomainVazio = new ProdutoDomain(1L,"Nome do Produto", "", new BigDecimal(10), 10);

        when(converter.dtoRequestParaDomain(produtoDtoRequestVazio)).thenReturn(produtoDomainVazio);
        when(repository.existisByProduto(produtoDomainVazio.getNomeProduto())).thenReturn(false);

        TipoProdutoInvalidException exception = Assertions.assertThrows(TipoProdutoInvalidException.class, () -> service.criaProduto(produtoDtoRequestVazio));

        assertThat(exception.getMessage()).isEqualTo("O tipo do produto não pode estar vazio!");

        verify(converter, times(1)).dtoRequestParaDomain(produtoDtoRequestVazio);
        verify(repository, times(1)).existisByProduto(produtoDtoRequestVazio.getNomeProduto());
        verify(repository, never()).salvaProduto(any(ProdutoDomain.class));

    }

    @Test
    @DisplayName("Não deve criar o produto caso o preço seja null ou menor que ZERO")
    void criaProdutoCase4(){
        ProdutoDtoRequest produtoDtoRequestPrecoInvalido = new ProdutoDtoRequest("Nome do Produto", "Tipo do Produto", new BigDecimal(0), 10);
        ProdutoDomain produtoDomainPrecoInvalido = new ProdutoDomain(1L,"Nome do Produto", "Tipo do Produto", new BigDecimal(0), 10);

        when(converter.dtoRequestParaDomain(produtoDtoRequestPrecoInvalido)).thenReturn(produtoDomainPrecoInvalido);
        when(repository.existisByProduto(produtoDomainPrecoInvalido.getNomeProduto())).thenReturn(false);

        PrecoInvalidException exception = Assertions.assertThrows(PrecoInvalidException.class, () -> service.criaProduto(produtoDtoRequestPrecoInvalido));

        assertThat(exception.getMessage()).isEqualTo("O preço não pode ser nulo ou abaixo de zero");

        verify(converter, times(1)).dtoRequestParaDomain(produtoDtoRequestPrecoInvalido);
        verify(repository, times(1)).existisByProduto(produtoDtoRequestPrecoInvalido.getNomeProduto());
        verify(repository, never()).salvaProduto(any(ProdutoDomain.class));
    }

    @Test
    @DisplayName("Não deve criar o produto caso a quantidade de estoque seja null ou menor que ZERO")
    void criaProdutoCase5(){
        ProdutoDtoRequest produtoDtoRequestQtdeInvalida = new ProdutoDtoRequest("Nome do Produto", "Tipo do Produto", new BigDecimal(10), 0);
        ProdutoDomain produtoDomainQtdeInvalida = new ProdutoDomain(1L,"Nome do Produto", "Tipo do Produto", new BigDecimal(10), 0);

        when(converter.dtoRequestParaDomain(produtoDtoRequestQtdeInvalida)).thenReturn(produtoDomainQtdeInvalida);
        when(repository.existisByProduto(produtoDomainQtdeInvalida.getNomeProduto())).thenReturn(false);

        QuantidadeEstoqueInvalidException exception = Assertions.assertThrows(QuantidadeEstoqueInvalidException.class, () -> service.criaProduto(produtoDtoRequestQtdeInvalida));

        assertThat(exception.getMessage()).isEqualTo("A quantidade não pode ser nula ou abaixo de zero");

        verify(converter, times(1)).dtoRequestParaDomain(produtoDtoRequestQtdeInvalida);
        verify(repository, times(1)).existisByProduto(produtoDomainQtdeInvalida.getNomeProduto());
        verify(repository, never()).salvaProduto(any(ProdutoDomain.class));
    }

    @Test
    @DisplayName("Deve retornar uma lista de produtos com sucesso")
    void listaProdutoCase1(){
        int page = 0;
        int size = 1;
        Pageable pageable = PageRequest.of(page, size);
        Page<ProdutoDomain> domainPage = new PageImpl<>(List.of(produtoDomain));

        when(repository.listaProduto(pageable)).thenReturn(domainPage);
        when(converter.domainParaDtoResponse(produtoDomain)).thenReturn(produtoDtoResponse);

        var result = service.listaProduto(page, size);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(produtoDtoResponse);

        verify(repository, times(1)).listaProduto(pageable);
        verify(converter, times(1)).domainParaDtoResponse(produtoDomain);
    }

    @Test
    @DisplayName("Não deve retornar uma lista caso esteja vazio")
    void listaProdutoCase2(){
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<ProdutoDomain> emptyPage = Page.empty();

        when(repository.listaProduto(pageable)).thenReturn(emptyPage);

        ProdutoNotFoundException exception = Assertions.assertThrows(ProdutoNotFoundException.class, () -> service.listaProduto(page, size));

        assertThat(exception.getMessage()).isEqualTo("Nenhum produto encontrado");

        verify(repository, times(1)).listaProduto(pageable);
        verify(converter, never()).domainParaDtoResponse(any(ProdutoDomain.class));
    }

    @Test
    @DisplayName("Deve encontrar um produto pelo id com sucesso")
    void buscaProdutoPorIdCase1(){
        when(repository.findById(produtoDtoResponse.id())).thenReturn(Optional.of(produtoDomain));
        when(converter.domainParaDtoResponse(produtoDomain)).thenReturn(produtoDtoResponse);

        var result = service.buscaProdutoPorId(produtoDtoResponse.id());

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(produtoDtoResponse);

        verify(repository, times(1)).findById(produtoDtoResponse.id());
        verify(converter, times(1)).domainParaDtoResponse(produtoDomain);
    }

    @Test
    @DisplayName("Deve retornar uma exceção caso o produto não seja encontrado pelo id")
    void buscaProdutoPorIdCase2(){
        Long id = 2L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        ProdutoNotFoundException exception = Assertions.assertThrows(ProdutoNotFoundException.class, () -> service.buscaProdutoPorId(id));

        assertThat(exception.getMessage()).isEqualTo("Produto com esse id não foi encontrado");

        verify(repository, times(1)).findById(id);
        verify(converter, never()).domainParaDtoResponse(any(ProdutoDomain.class));
    }

    @Test
    @DisplayName("Deve atualizar os dados do produto caso todos os dados sejam alterados")
    void atualizaProdutoPorIdCase1(){
        ProdutoDtoRequest produtoDtoRequestAtualizado = new ProdutoDtoRequest("Produto 2", "Tipo 2", new BigDecimal(20), 20);
        ProdutoEntity produtoEntity = new ProdutoEntity(produtoDomain.getId(), produtoDomain.getNomeProduto(), produtoDomain.getTipoProduto(), produtoDomain.getPreco(), produtoDomain.getQuantidadeEstoque());
        ProdutoDomain produtoAtualizado = new ProdutoDomain(produtoEntity.getId(), produtoDtoRequestAtualizado.getNomeProduto(), produtoDtoRequestAtualizado.getTipoProduto(), produtoDtoRequestAtualizado.getPreco(), produtoDtoRequestAtualizado.getQuantidadeEstoque());

        when(repository.findById(produtoDomain.getId())).thenReturn(Optional.of(produtoDomain));
        when(converter.domainParaEntity(produtoDomain)).thenReturn(produtoEntity);
        doNothing().when(updateConverter).updateConverter(produtoDtoRequestAtualizado, produtoEntity);
        when(converter.entityParaDomain(produtoEntity)).thenReturn(produtoAtualizado);
        when(repository.atualizaProduto(any(ProdutoDomain.class))).thenReturn(produtoAtualizado);
        when(converter.domainParaDtoRequest(produtoAtualizado)).thenReturn(produtoDtoRequestAtualizado);

        var result = service.atualizaProdutoPorId(produtoDtoRequestAtualizado, produtoDomain.getId());

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(produtoDtoRequestAtualizado);

        verify(repository, times(1)).findById(produtoDomain.getId());
        verify(converter, times(1)).domainParaEntity(produtoDomain);
        verify(updateConverter, times(1)).updateConverter(produtoDtoRequestAtualizado, produtoEntity);
        verify(converter, times(1)).entityParaDomain(produtoEntity);
        verify(repository, times(1)).atualizaProduto(any(ProdutoDomain.class));
        verify(converter, times(1)).domainParaDtoRequest(produtoAtualizado);
    }

    @Test
    @DisplayName("Deve retornar um erro caso não seja encontrado o ID do produto e não é atualizado")
    void atualizaProdutoPorIdCase2(){
        when(repository.findById(produtoDomain.getId())).thenReturn(Optional.empty());

        ProdutoNotFoundException exception = Assertions.assertThrows(ProdutoNotFoundException.class, () -> service.atualizaProdutoPorId(produtoDtoRequest,produtoDomain.getId()));

        assertThat(exception.getMessage()).isEqualTo("Produto com esse id não foi encontrado");

        verify(repository, times(1)).findById(produtoDomain.getId());
        verify(converter, never()).domainParaEntity(any(ProdutoDomain.class));
        verify(updateConverter, never()).updateConverter(any(ProdutoDtoRequest.class), any(ProdutoEntity.class));
        verify(converter, never()).entityParaDomain(any(ProdutoEntity.class));
        verify(repository, never()).atualizaProduto(any(ProdutoDomain.class));
        verify(converter, never()).domainParaDtoRequest(any(ProdutoDomain.class));
    }

    @Test
    @DisplayName("Deve deletar um produto pelo ID com sucesso")
    void deletaProdutoPorIdCase1(){
        when(repository.findById(produtoDomain.getId())).thenReturn(Optional.of(produtoDomain));
        doNothing().when(repository).deletaProduto(produtoDomain);

        service.deletaProdutoPorId(produtoDomain.getId());

        verify(repository, times(1)).findById(produtoDomain.getId());
        verify(repository, times(1)).deletaProduto(produtoDomain);
    }
    @Test
    @DisplayName("Deve retornar um erro ao não encontro o produto pelo ID e não ser deletado")
    void deletaProdutoPorIdCase2(){
        when(repository.findById(produtoDomain.getId())).thenReturn(Optional.empty());

        ProdutoNotFoundException exception = Assertions.assertThrows(ProdutoNotFoundException.class, () -> service.deletaProdutoPorId(produtoDomain.getId()));

        assertThat(exception.getMessage()).isEqualTo("Produto com esse id não foi encontrado");

        verify(repository, times(1)).findById(produtoDomain.getId());
        verify(repository, never()).deletaProduto(produtoDomain);
    }

    @Test
    @DisplayName("Deve retornar uma lista de produtos ao buscar por nome")
    void buscaProdutoCase1() {
        int page = 0;
        int size = 1;
        String nomeProduto = "Produto";
        Pageable pageable = PageRequest.of(page, size);
        Page<ProdutoDomain> domainPage = new PageImpl<>(List.of(produtoDomain));

        when(repository.buscarProdutoPorNome(pageable, nomeProduto)).thenReturn(domainPage);
        when(converter.domainParaDtoResponse(produtoDomain)).thenReturn(produtoDtoResponse);

        var result = service.buscaProduto(page, size, nomeProduto);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(produtoDtoResponse);

        verify(repository, times(1)).buscarProdutoPorNome(pageable, nomeProduto);
        verify(converter, times(1)).domainParaDtoResponse(produtoDomain);
    }

    @Test
    @DisplayName("Deve retornar uma lista de produtos ao buscar por tipo")
    void buscaPorTipoProdutoCase1() {
        int page = 0;
        int size = 1;
        String tipoProduto = "Tipo";
        Pageable pageable = PageRequest.of(page, size);
        Page<ProdutoDomain> domainPage = new PageImpl<>(List.of(produtoDomain));

        when(repository.buscaProdutoPorTipo(pageable, tipoProduto)).thenReturn(domainPage);
        when(converter.domainParaDtoResponse(produtoDomain)).thenReturn(produtoDtoResponse);

        var result = service.buscaPorTipoProduto(page, size, tipoProduto);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(produtoDtoResponse);

        verify(repository, times(1)).buscaProdutoPorTipo(pageable, tipoProduto);
        verify(converter, times(1)).domainParaDtoResponse(produtoDomain);
    }

    @Test
    @DisplayName("Deve retornar uma lista de produtos ao buscar por faixa de preço")
    void buscaPorPrecoCase1() {
        int page = 0;
        int size = 1;
        BigDecimal min = new BigDecimal(5);
        BigDecimal max = new BigDecimal(15);
        Pageable pageable = PageRequest.of(page, size);
        Page<ProdutoDomain> domainPage = new PageImpl<>(List.of(produtoDomain));

        when(repository.buscaPorPreco(pageable, min, max)).thenReturn(domainPage);
        when(converter.domainParaDtoResponse(produtoDomain)).thenReturn(produtoDtoResponse);

        var result = service.buscaPorPreco(page, size, min, max);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(produtoDtoResponse);

        verify(repository, times(1)).buscaPorPreco(pageable, min, max);
        verify(converter, times(1)).domainParaDtoResponse(produtoDomain);
    }

    @Test
    @DisplayName("Deve retornar uma lista de produtos ao fazer busca avançada")
    void buscaAvancadaCase1() {
        int page = 0;
        int size = 1;
        String nomeProduto = "Produto";
        String tipoProduto = "Tipo";
        BigDecimal min = new BigDecimal(5);
        BigDecimal max = new BigDecimal(15);
        Pageable pageable = PageRequest.of(page, size);
        Page<ProdutoDomain> domainPage = new PageImpl<>(List.of(produtoDomain));

        when(repository.buscaAvancada(pageable, nomeProduto, tipoProduto, min, max)).thenReturn(domainPage);
        when(converter.domainParaDtoResponse(produtoDomain)).thenReturn(produtoDtoResponse);

        var result = service.buscaAvancada(page, size, nomeProduto, tipoProduto, min, max);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(produtoDtoResponse);

        verify(repository, times(1)).buscaAvancada(pageable, nomeProduto, tipoProduto, min, max);
        verify(converter, times(1)).domainParaDtoResponse(produtoDomain);
    }
}
