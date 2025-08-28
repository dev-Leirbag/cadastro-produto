package produto.api.adapters.in.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import produto.api.adapters.in.dto.ProdutoDtoRequest;
import produto.api.adapters.in.service.ProdutoService;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoDtoRequest> criaProduto(@RequestBody ProdutoDtoRequest data){
        return ResponseEntity.ok(service.criaProduto(data));
    }
}
