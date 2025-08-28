package produto.api.adapters.in.mapper;

import org.mapstruct.Mapper;
import produto.api.adapters.in.dto.ProdutoDtoRequest;
import produto.api.adapters.out.entities.ProdutoEntity;
import produto.api.application.domain.ProdutoDomain;

@Mapper(componentModel = "spring")
public interface Converter {

    //ProdutoDomain
    ProdutoDomain dtoRequestParaDomain(ProdutoDtoRequest request);
    ProdutoDomain entityParaDomain(ProdutoEntity entity);

    //ProdutoEntity
    ProdutoEntity domainParaEntity(ProdutoDomain domain);

    //ProdutoDtoRequest
    ProdutoDtoRequest domainParaDtoRequest(ProdutoDomain domain);
}
