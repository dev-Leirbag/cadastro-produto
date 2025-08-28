package produto.api.adapters.in.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import produto.api.adapters.in.dto.ProdutoDtoRequest;
import produto.api.adapters.in.dto.ProdutoDtoResponse;
import produto.api.adapters.in.service.ProdutoService;

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
}
