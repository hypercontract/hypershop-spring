package org.hypercontract.hypershop.product;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.resource.Id;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public List<Product> getByQuery(
        @RequestParam() Optional<String> query
    ) {
        return query
            .map(queryString -> productService.findByQuery(queryString))
            .orElseGet(productService::findAll);
    }
}
