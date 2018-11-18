package org.hypercontract.hypershop.product;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.product.Product.ProductId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("{productId}")
    public Product getById(
        @PathVariable() ProductId productId
    ) {
        return productService.getById(productId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping()
    public List<Product> getByQuery(
        @RequestParam(required = false) String query
    ) {
        if (query == null) {
            return productService.findAll().collect(Collectors.toList());
        }

        return productService.findByQuery(query).collect(Collectors.toList());
    }
}
