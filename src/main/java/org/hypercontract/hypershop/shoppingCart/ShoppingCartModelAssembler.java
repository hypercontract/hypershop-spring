package org.hypercontract.hypershop.shoppingCart;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hypercontract.hypershop.product.Product;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
class ShoppingCartModelAssembler implements RepresentationModelAssembler<ShoppingCart, EntityModel<ShoppingCart>> {

	@Override
	public EntityModel<ShoppingCart> toModel(ShoppingCart entity) {
		var resource = new EntityModel<>(entity);

		var shoppingCartLink = linkTo(methodOn(ShoppingCartController.class).get());

		resource.add(shoppingCartLink.withSelfRel());

		return resource;
	}
}