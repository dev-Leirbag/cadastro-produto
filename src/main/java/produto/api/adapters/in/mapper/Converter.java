package produto.api.adapters.in.mapper;

import org.mapstruct.Mapper;
import produto.api.adapters.in.dto.ProdutoDtoRequest;
import produto.api.adapters.in.dto.ProdutoDtoResponse;
import produto.api.adapters.in.dto.ReservaRequestDto;
import produto.api.adapters.in.dto.ReservaResponseDto;
import produto.api.adapters.out.entities.ProdutoEntity;
import produto.api.adapters.out.entities.ReservaItemEntity;
import produto.api.adapters.out.entities.ReservaProdutoEntity;
import produto.api.application.domain.ProdutoDomain;
import produto.api.application.domain.ReservaItemDomain;
import produto.api.application.domain.ReservaProdutoDomain;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Converter {

    //ProdutoDomain
    ProdutoDomain dtoRequestParaDomain(ProdutoDtoRequest request);
    ProdutoDomain entityParaDomain(ProdutoEntity entity);
    ProdutoDomain dtoResponseParaDomain(ProdutoDtoResponse response);
    List<ProdutoDomain> entityParaDomain(List<ProdutoEntity> entities);
    List<ProdutoDomain> dtoResponseParaDomain(List<ProdutoDtoResponse> responses);

    //ProdutoEntity
    ProdutoEntity domainParaEntity(ProdutoDomain domain);
    List<ProdutoEntity> domainParaEntity(List<ProdutoDomain> domains);

    //ProdutoDtoRequest
    ProdutoDtoRequest domainParaDtoRequest(ProdutoDomain domain);

    //ProdutoDtoResponse
    ProdutoDtoResponse domainParaDtoResponse(ProdutoDomain domain);
    List<ProdutoDtoResponse> domainParaDtoResponse(List<ProdutoDomain> domains);

    //ReservaDomain
    ReservaProdutoDomain dtoRequestParaDomain(ReservaRequestDto request);
    ReservaProdutoDomain dtoResponseParaDomain(ReservaResponseDto response);
    ReservaProdutoDomain entityParaDomain(ReservaProdutoEntity entity);

    //ReservaEntity
    ReservaProdutoEntity domainParaEntity(ReservaProdutoDomain domain);

    //ReservaResponse
    ReservaResponseDto domainParaDtoReservaResponse(ReservaProdutoDomain domain);

    //ReservaRequest
    ReservaRequestDto domainParaDtoReservaRequest(ReservaProdutoDomain domain);

    ReservaItemEntity domainParaEntity(ReservaItemDomain domain);
    ReservaItemDomain entityParaDomain(ReservaItemEntity entity);

}
