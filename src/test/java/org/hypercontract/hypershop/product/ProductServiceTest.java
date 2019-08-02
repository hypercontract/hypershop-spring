package org.hypercontract.hypershop.product;

import org.hypercontract.hypershop.product.mock.ProductTestData;
import org.hypercontract.hypershop.resource.Id;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private final ProductTestData productTestData = ProductTestData.getInstance();

    @Test
    public void getByIdReturnsAProduct() {
        var expectedProduct = productTestData.getProduct();
        var expectedId = expectedProduct.getId();
        when(productRepository.findById(expectedId)).thenReturn(Optional.of(expectedProduct));

        var product = productService.getById(expectedId);

        Assert.assertSame(product, expectedProduct);
    }

    @Test(expected = EntityNotFoundException.class)
    public void getByIdThrowsIfIdIsInvalid() {
        when(productRepository.findById(any(Id.class))).thenReturn(Optional.empty());

        productService.getById(new Id<>());
    }

    @Test
    public void findAllReturnsAllProducts() {
        var expectedProducts = productTestData.getProducts();
        when(productRepository.findAll()).thenReturn(expectedProducts.stream());

        var products = productService.findAll();

        assertEquals(products, expectedProducts);
    }

    @Test
    public void findByQueryReturnsAllProductsWithMatchingNames() {
        var expectedQuery = "some query";
        var expectedProducts = productTestData.getProducts();
        when(productRepository.findByNameContainingIgnoreCase(expectedQuery)).thenReturn(expectedProducts.stream());

        var products = productService.findByQuery(expectedQuery);

        assertEquals(products, expectedProducts);
    }

}
