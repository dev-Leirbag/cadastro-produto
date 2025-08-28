package produto.api.adapters.in.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import produto.api.adapters.in.dto.ProdutoDtoRequest;
import produto.api.adapters.out.entities.ProdutoEntity;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UpdateConverter {

    void updateConverter(ProdutoDtoRequest request, @MappingTarget ProdutoEntity entity);
}
