package org.hypercontract.hypershop.product;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.resource.Id;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("{productId}")
    public Product getById(
        @PathVariable() Id<Product> productId
    ) {
        return productService.getById(productId);
    }

    @GetMapping()
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping(params = { "query" })
    public List<Product> getByQuery(
        @RequestParam() String query
    ) {
        return productService.findByQuery(query);
    }
}
