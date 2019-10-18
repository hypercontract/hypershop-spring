package org.hypercontract.hypershop.product;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {

	private final @NonNull
	EntityLinks entityLinks;

	@Override
	public EntityModel<Product> toModel(Product entity) {
		var resource = new EntityModel<>(entity);

		var typedEntityLinks = entityLinks.forType(Product::getId);

		var productLink = typedEntityLinks.linkForItemResource(entity);

		resource.add(productLink.withSelfRel());

		return resource;
	}
}