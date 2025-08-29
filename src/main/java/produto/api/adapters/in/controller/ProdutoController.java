package produto.api.adapters.in.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import produto.api.adapters.in.dto.ProdutoDtoRequest;
import produto.api.adapters.in.dto.ProdutoDtoResponse;
import produto.api.adapters.in.service.ProdutoService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping("/produto")
    public ResponseEntity<ProdutoDtoRequest> criaProduto(@RequestBody ProdutoDtoRequest data){
        return ResponseEntity.ok(service.criaProduto(data));
    }

    @GetMapping("/produto/all")
    public ResponseEntity<List<ProdutoDtoResponse>> listaProdutos(){
        return ResponseEntity.ok(service.listaProduto());
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<ProdutoDtoResponse> buscaProdutoPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscaProdutoPorId(id));
    }

    @PutMapping("/produto/{id}")
    public ResponseEntity<ProdutoDtoRequest> atualizaProdutoPorId(@RequestBody ProdutoDtoRequest data,@PathVariable Long id){
        return ResponseEntity.ok(service.atualizaProdutoPorId(data, id));
    }

    @DeleteMapping("/produto/{id}")
    public ResponseEntity<Void> deletaProdutoPorId(@PathVariable Long id){
        service.deletaProdutoPorId(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/produto/buscar")
    public ResponseEntity<List<ProdutoDtoResponse>> buscaProduto(@RequestParam(value = "nomeProduto") String nomeProduto){
        return ResponseEntity.ok(service.buscaProduto(nomeProduto));
    }

    @GetMapping("/produto/buscar/tipo")
    public ResponseEntity<List<ProdutoDtoResponse>> buscaPorTipoProduto(@RequestParam(value = "tipoProduto") String tipoProduto){
        return ResponseEntity.ok(service.buscaPorTipoProduto(tipoProduto));
    }

    @GetMapping("/produto/buscar/preco")
    public ResponseEntity<List<ProdutoDtoResponse>> buscaPorPreco(@RequestParam(value = "min")BigDecimal min,@RequestParam(value = "max")BigDecimal max){
        return ResponseEntity.ok(service.buscaPorPreco(min, max));
    }

    @GetMapping("/produto/buscar/filtro")
    public ResponseEntity<List<ProdutoDtoResponse>> buscaAvancada(@RequestParam(value = "nomeProduto", required = false)String nomeProduto,
                                                                  @RequestParam(value = "tipoProduto", required = false)String tipoProduto,
                                                                  @RequestParam(value = "min", required = false)BigDecimal min,
                                                                  @RequestParam(value = "max", required = false)BigDecimal max){
        return ResponseEntity.ok(service.buscaAvancada(nomeProduto, tipoProduto, min, max));
    }

}
