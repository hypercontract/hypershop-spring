package org.hypercontract.hypershop.product.jpa;

import org.hypercontract.hypershop.product.Product;
import org.hypercontract.hypershop.resource.IdMapper;
import org.mapstruct.Mapper;

@Mapper(
    uses = IdMapper.class
)
public interface ProductEntityMapper {

    Product toProduct(ProductEntity productEntity);

    ProductEntity toEntity(Product product);

}
