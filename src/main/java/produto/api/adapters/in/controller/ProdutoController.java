package produto.api.adapters.in.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import produto.api.adapters.in.dto.ProdutoDtoRequest;
import produto.api.adapters.in.dto.ProdutoDtoResponse;
import produto.api.adapters.in.service.ProdutoService;
import produto.api.application.infra.config.SecurityConfig;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Produto", description = "Cadastra produtos")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping("/produto")
    @Operation(summary = "Cria um produto", description = "Cria e salva um produto")
    @ApiResponse(responseCode = "200", description = "Produto criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Existem campos vazios ou inválidos")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ProdutoDtoRequest> criaProduto(@RequestBody ProdutoDtoRequest data){
        return ResponseEntity.ok(service.criaProduto(data));
    }

    @GetMapping("/produto/all")
    @Operation(summary = "Lista todos os produtos", description = "Lista todos os produtos existentes")
    @ApiResponse(responseCode = "200", description = "Lista encontrada com sucesso")
    @ApiResponse(responseCode = "404", description = "Lista não encontrada ou vazia")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<ProdutoDtoResponse>> listaProdutos(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10")int size){
        return ResponseEntity.ok(service.listaProduto(page, size));
    }

    @GetMapping("/produto/{id}")
    @Operation(summary = "Busca produto por Id", description = "Busca produto por Id")
    @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ProdutoDtoResponse> buscaProdutoPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscaProdutoPorId(id));
    }

    @PutMapping("/produto/{id}")
    @Operation(summary = "Atualiza produto por Id", description = "Atualiza produto por Id")
    @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ProdutoDtoRequest> atualizaProdutoPorId(@RequestBody ProdutoDtoRequest data,@PathVariable Long id){
        return ResponseEntity.ok(service.atualizaProdutoPorId(data, id));
    }

    @DeleteMapping("/produto/{id}")
    @Operation(summary = "Deleta produto por Id", description = "Deleta produto por Id")
    @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaProdutoPorId(@PathVariable Long id){
        service.deletaProdutoPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/produto/buscar")
    @Operation(summary = "Busca produto pelo nome", description = "Busca produto pelo nome")
    @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<ProdutoDtoResponse>> buscaProdutoPorNome(@RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "10") int size,
                                                                        @RequestParam(value = "nomeProduto") String nomeProduto){
        return ResponseEntity.ok(service.buscaProduto(page,size,nomeProduto));
    }

    @GetMapping("/produto/buscar/tipo")
    @Operation(summary = "Busca pelo tipo do produto", description = "Busca pelo tipo do produto")
    @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<ProdutoDtoResponse>> buscaPorTipoProduto(@RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "10") int size,
                                                                        @RequestParam(value = "tipoProduto") String tipoProduto){
        return ResponseEntity.ok(service.buscaPorTipoProduto(page,size,tipoProduto));
    }

    @GetMapping("/produto/buscar/preco")
    @Operation(summary = "Busca pelo preço do produto", description = "Busca pelo preço do produto")
    @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<ProdutoDtoResponse>> buscaProdutoPorPreco(@RequestParam(value = "min")BigDecimal min,@RequestParam(value = "max")BigDecimal max){
        return ResponseEntity.ok(service.buscaPorPreco(min, max));
    }

    @GetMapping("/produto/buscar/filtro")
    @Operation(summary = "Busca pelo preço, tipo, ou nome do produto", description = "Busca pelo preço, tipo, ou nome do produto")
    @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<ProdutoDtoResponse>> buscaAvancada(@RequestParam(value = "nomeProduto", required = false)String nomeProduto,
                                                                  @RequestParam(value = "tipoProduto", required = false)String tipoProduto,
                                                                  @RequestParam(value = "min", required = false)BigDecimal min,
                                                                  @RequestParam(value = "max", required = false)BigDecimal max){
        return ResponseEntity.ok(service.buscaAvancada(nomeProduto, tipoProduto, min, max));
    }
}
