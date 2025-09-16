package produto.api.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import produto.api.adapters.in.dto.ProdutoReservaDto;
import produto.api.adapters.in.dto.ReservaRequestDto;
import produto.api.adapters.in.dto.ReservaResponseDto;
import produto.api.adapters.in.mapper.Converter;
import produto.api.adapters.in.service.ReservaService;
import produto.api.adapters.out.entities.ReservaItemEntity;
import produto.api.adapters.out.entities.ReservaProdutoEntity;
import produto.api.application.domain.ProdutoDomain;
import produto.api.application.domain.ReservaProdutoDomain;
import produto.api.application.infra.config.TokenService;
import produto.api.application.infra.controller.exceptions.ProdutoNotFoundException;
import produto.api.application.infra.controller.exceptions.QuantidadeEstoqueInvalidException;
import produto.api.out.ProdutoRepository;
import produto.api.out.ReservaRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {

    private final TokenService tokenService;
    private final Converter converter;
    private final ReservaRepository reservaRepository;
    private final ProdutoRepository produtoRepository;


    @Override
    @Transactional
    public ReservaResponseDto reservaProduto(ReservaRequestDto request, String token) {
        String email = tokenService.extractUsername(token);

        if(email.isBlank()){
            throw new UsernameNotFoundException("Usuario não encontrado: " + email);
        }

        ReservaProdutoEntity reservaEntity = new ReservaProdutoEntity();
        reservaEntity.setEmail_usuario(email);
        reservaEntity.setValor_total(BigDecimal.ZERO);
        reservaEntity.setItens(new ArrayList<>());

        for(var item : request.produtos()){
            ProdutoDomain produtoEncontrado = produtoRepository.findById(item.getProdutoId()).orElseThrow(
                    () -> new ProdutoNotFoundException("Esse produto não foi localizado"));

            if(produtoEncontrado.getQuantidadeEstoque() < item.getQuantidade()){
                throw new QuantidadeEstoqueInvalidException("Esse item não possui estoque suficiente: " + produtoEncontrado.getNomeProduto());
            }

            var valorTotalItem = produtoEncontrado.getPreco().multiply(new BigDecimal(item.getQuantidade()));

            ReservaItemEntity itemEntity = new ReservaItemEntity();
            itemEntity.setProduto_id(produtoEncontrado.getId());
            itemEntity.setNome_produto(produtoEncontrado.getNomeProduto());
            itemEntity.setTipo_produto(produtoEncontrado.getTipoProduto());
            itemEntity.setQuantidade(item.getQuantidade());
            itemEntity.setPreco_unitario(produtoEncontrado.getPreco());
            itemEntity.setValor_total_item(valorTotalItem);

            itemEntity.setReserva(reservaEntity);
            reservaEntity.getItens().add(itemEntity);

            reservaEntity.setValor_total(reservaEntity.getValor_total().add(valorTotalItem));

            var novaQuantidade = produtoEncontrado.getQuantidadeEstoque() - item.getQuantidade();
            produtoEncontrado.setQuantidadeEstoque(novaQuantidade);
            produtoRepository.atualizaProduto(produtoEncontrado);

        }

        ReservaProdutoEntity entitySalvo = reservaRepository.salvaReserva(reservaEntity);

        List<ProdutoReservaDto> produtosDto = entitySalvo.getItens().stream()
                .map(i -> new ProdutoReservaDto(i.getProduto_id(), i.getNome_produto(), i.getTipo_produto()))
                .toList();

        return new ReservaResponseDto(
                entitySalvo.getEmail_usuario(),
                entitySalvo.getReserva_id(),
                produtosDto,
                entitySalvo.getValor_total()
        );
    }
}
