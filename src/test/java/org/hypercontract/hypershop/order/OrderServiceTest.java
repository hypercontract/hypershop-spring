package org.hypercontract.hypershop.order;

import org.hypercontract.hypershop.order.mock.OrderTestData;
import org.hypercontract.hypershop.resource.Id;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartItem;
import org.hypercontract.hypershop.shoppingCart.mock.ShoppingCartItemTestData;
import org.hypercontract.hypershop.userProfile.Address;
import org.hypercontract.hypershop.userProfile.PaymentOption;
import org.hypercontract.hypershop.userProfile.mock.AddressTestData;
import org.hypercontract.hypershop.userProfile.mock.PaymentOptionTestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;

import javax.persistence.EntityNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hypercontract.hypershop.order.OrderStatus.CANCELLED;
import static org.hypercontract.hypershop.order.OrderStatus.RETURNED;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    private final OrderTestData orderTestData = OrderTestData.getInstance();
    private final ShoppingCartItemTestData shoppingCartItemTestData = ShoppingCartItemTestData.getInstance();
    private final AddressTestData addressTestData = AddressTestData.getInstance();
    private final PaymentOptionTestData paymentOptionTestData = PaymentOptionTestData.getInstance();

    @Test
    public void getByIdReturnsAnOrder() {
        var expectedOrder = orderTestData.getOrder();
        var expectedId = expectedOrder.getId();
        when(orderRepository.findById(expectedId)).thenReturn(Optional.of(expectedOrder));

        var order = orderService.getById(expectedId);

        assertSame(order, expectedOrder);
    }

    @Test(expected = EntityNotFoundException.class)
    public void getByIdThrowsIfIdIsInvalid() {
        when(orderRepository.findById(any(Id.class))).thenReturn(Optional.empty());

        orderService.getById(new Id<>());
    }

    @Test
    public void findAllReturnsAllOrders() {
        var expectedOrders = orderTestData.getOrders();
        when(orderRepository.findAll()).thenReturn(expectedOrders.stream());

        var orders = orderService.findAll();

        assertEquals(orders, expectedOrders);
    }

    @Test
    public void placeOrderCreatesANewOrder() {
        var shoppingCartItems = shoppingCartItemTestData.getShoppingCartItems();
        var billingAddress = addressTestData.getAddress();
        var shippingAddress = addressTestData.getAnotherAddress();
        var paymentOption = paymentOptionTestData.getPaymentOption();
        var orderCaptor = ArgumentCaptor.forClass(Order.class);

        orderService.placeOrder(
            shoppingCartItems,
            billingAddress,
            shippingAddress,
            paymentOption
        );

        verify(orderRepository).save(orderCaptor.capture());

        var savedOrder = orderCaptor.getValue();
        assertTrue(Duration.between(savedOrder.getDate(), LocalDateTime.now()).toMinutes() < 1);
        assertSame(savedOrder.getStatus(), OrderStatus.PAYMENT_DUE);
        assertEqualsAddress(savedOrder.getBillingAddress(), billingAddress);
        assertEqualsAddress(savedOrder.getShippingAddress(), shippingAddress);
        assertEqualsPaymentOption(savedOrder.getPayment(), paymentOption);
        assertEqualsShoppingCartItems(savedOrder.getItems(), shoppingCartItems);
    }

    @Test
    public void placeOrderPublishesAnOrderPlacedEvent() {
        orderService.placeOrder(
            shoppingCartItemTestData.getShoppingCartItems(),
            addressTestData.getAddress(),
            addressTestData.getAnotherAddress(),
            paymentOptionTestData.getPaymentOption()
        );

        verify(eventPublisher).publishEvent(any(OrderPlaced.class));
    }

    @Test
    public void cancelOrder() {
        var order = orderTestData.getOrder();
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));

        orderService.cancelOrder(order.getId());

        verify(orderRepository).save(order);
        assertEquals(order.getStatus(), CANCELLED);
    }

    @Test
    public void returnOrder() {
        var order = orderTestData.getOrder();
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));

        orderService.returnOrder(order.getId());

        verify(orderRepository).save(order);
        assertEquals(order.getStatus(), RETURNED);
    }

    private void assertEqualsAddress(OrderAddress orderAddress, Address address) {
        assertEquals(address.getName(), orderAddress.getName());
        assertEquals(address.getCity(), orderAddress.getCity());
        assertEquals(address.getZipCode(), orderAddress.getZipCode());
        assertEquals(address.getCity(), orderAddress.getCity());
        assertEquals(address.getCountry(), orderAddress.getCountry());
    }

    private void assertEqualsPaymentOption(OrderPayment orderPayment, PaymentOption paymentOption) {
        assertEquals(paymentOption.getAccountOwner(), orderPayment.getAccountOwner());
        assertEquals(paymentOption.getIban(), orderPayment.getIban());
        assertEquals(paymentOption.getBic(), orderPayment.getBic());
    }

    private void assertEqualsShoppingCartItems(List<OrderItem> orderItems, List<ShoppingCartItem> shoppingCartItems) {
        assertEquals(orderItems.size(), shoppingCartItems.size());

        shoppingCartItems.forEach(shoppingCartItem -> {
            var orderItem = orderItems.stream()
                .filter(item -> item.getProduct().equals(shoppingCartItem.getProduct()))
                .findAny();

            orderItem.ifPresent(savedOrderItem -> {
                assertEquals(savedOrderItem.getDescription(), shoppingCartItem.getDescription());
                assertEquals(savedOrderItem.getName(), shoppingCartItem.getName());
                assertEquals(savedOrderItem.getPrice(), shoppingCartItem.getPrice());
                assertEquals(savedOrderItem.getQuantity(), shoppingCartItem.getQuantity());
            });
        });
    }

}
