package org.hypercontract.hypershop.product;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.resource.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@ExposesResourceFor(Product.class)
public class ProductController {

	private final ProductService productService;

	@Autowired
	private ProductModelAssembler resourceProcessor;

	@GetMapping("{productId}")
	public EntityModel<Product> getById(
			@PathVariable() Id<Product> productId
	) {
		return resourceProcessor.toModel(productService.getById(productId));
	}

	@GetMapping()
	public ResponseEntity<CollectionModel<EntityModel<Product>>> getAll() {
		return ResponseEntity.ok(resourceProcessor.toCollectionModel(productService.findAll()));
	}

	@GetMapping(params = { "query" })
	public ResponseEntity<CollectionModel<EntityModel<Product>>> getByQuery(
			@RequestParam() String query
	) {
		return ResponseEntity.ok(resourceProcessor.toCollectionModel(productService.findByQuery(query)));
	}
}
