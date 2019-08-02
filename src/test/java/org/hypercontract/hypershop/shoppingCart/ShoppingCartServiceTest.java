package org.hypercontract.hypershop.shoppingCart;

import org.hypercontract.hypershop.product.mock.ProductTestData;
import org.hypercontract.hypershop.resource.Id;
import org.hypercontract.hypershop.shoppingCart.mock.ShoppingCartItemTestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartServiceTest {

    @InjectMocks
    private ShoppingCartService shoppingCartService;

    @Mock
    private ShoppingCartItemRepository shoppingCartItemRepository;

    private ShoppingCartItemTestData shoppingCartItemTestData = ShoppingCartItemTestData.getInstance();
    private ProductTestData productTestData = ProductTestData.getInstance();

    @Test
    public void getReturnsTheShoppingCart() {
        var expectedShoppingCartItems = shoppingCartItemTestData.getShoppingCartItems();
        when(shoppingCartItemRepository.findAll()).thenReturn(expectedShoppingCartItems.stream());

        var shoppingCart = shoppingCartService.get();

        assertEquals(shoppingCart.getItems(), expectedShoppingCartItems);
    }

    @Test
    public void getItemByIdReturnsAShoppingCartItem() {
        var expectedShoppingCartItem = shoppingCartItemTestData.getShoppingCartItem();
        var expectedId = expectedShoppingCartItem.getId();
        when(shoppingCartItemRepository.findById(expectedId)).thenReturn(Optional.of(expectedShoppingCartItem));

        var shoppingCartItem = shoppingCartService.getItemById(expectedId);

        assertSame(shoppingCartItem, expectedShoppingCartItem);
    }

    @Test(expected = EntityNotFoundException.class)
    public void getItemByIdThrowsIfIdIsInvalid() {
        when(shoppingCartItemRepository.findById(any(Id.class))).thenReturn(Optional.empty());

        shoppingCartService.getItemById(new Id<>());
    }

    @Test
    public void addItemCreatesANewShoppingCartItemIfProductHasNotBeenAddedYet() {
        var product = productTestData.getProduct();
        var quantity = 1;
        var productId = product.getId();
        var additionToShoppingCart = AdditionToShoppingCart.builder()
            .product(productId)
            .quantity(quantity)
            .build();
        var shoppingCartItemCaptor = ArgumentCaptor.forClass(ShoppingCartItem.class);
        when(shoppingCartItemRepository.findByProduct(productId)).thenReturn(Optional.empty());

        shoppingCartService.addItem(additionToShoppingCart, product);

        verify(shoppingCartItemRepository).save(shoppingCartItemCaptor.capture());

        var shoppingCartItem = shoppingCartItemCaptor.getValue();
        assertEquals(shoppingCartItem.getName(), product.getName());
        assertEquals(shoppingCartItem.getDescription(), product.getDescription());
        assertEquals(shoppingCartItem.getProduct(), productId);
        assertEquals(shoppingCartItem.getPrice(), product.getPrice());
        assertEquals(shoppingCartItem.getQuantity(), quantity);
    }
    @Test
    public void addItemIncreasesTheQuantityIfProductHasAlreadyBeenAddedBefore() {
        var existingShoppingCartItem = shoppingCartItemTestData.getShoppingCartItem();
        var currentQuantity = existingShoppingCartItem.getQuantity();
        var product = productTestData.getProducts().stream()
            .filter(p -> p.getId().equals(existingShoppingCartItem.getProduct()))
            .findAny()
            .get();
        var quantity = 1;
        var productId = product.getId();
        var additionToShoppingCart = AdditionToShoppingCart.builder()
            .product(productId)
            .quantity(quantity)
            .build();
        when(shoppingCartItemRepository.findByProduct(productId)).thenReturn(Optional.of(existingShoppingCartItem));

        shoppingCartService.addItem(additionToShoppingCart, product);

        verify(shoppingCartItemRepository, never()).save(any());

        assertEquals(existingShoppingCartItem.getQuantity(), currentQuantity + quantity);
    }

    @Test
    public void updateItemQuantitySetsTheGivenQuantity() {
        var shoppingCartItem = shoppingCartItemTestData.getShoppingCartItem();
        var expectedId = shoppingCartItem.getId();
        var expectedQuantity = 2;
        var quantityUpdate = QuantityUpdate.builder()
            .quantity(expectedQuantity)
            .build();
        when(shoppingCartItemRepository.findById(expectedId)).thenReturn(Optional.of(shoppingCartItem));

        shoppingCartService.updateItemQuantity(expectedId, quantityUpdate);

        assertEquals(shoppingCartItem.getQuantity(), expectedQuantity);
    }

    @Test(expected = EntityNotFoundException.class)
    public void updateItemQuantityThrowsIfIdIsInvalid() {
        var quantityUpdate = QuantityUpdate.builder().build();
        when(shoppingCartItemRepository.findById(any(Id.class))).thenReturn(Optional.empty());

        shoppingCartService.updateItemQuantity(new Id<>(), quantityUpdate);
    }

    @Test
    public void removeItem() {

    }

    @Test
    public void emptyShoppingCartDeletesAllShoppingCartItems() {
        shoppingCartService.emptyShoppingCart();

        verify(shoppingCartItemRepository).deleteAll();
    }


}
