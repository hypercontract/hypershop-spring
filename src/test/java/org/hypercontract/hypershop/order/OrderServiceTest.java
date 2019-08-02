package org.hypercontract.hypershop.order;

import org.hypercontract.hypershop.resource.Id;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    public void getByIdReturnsAnOrder() {
        var expectedId = new Id<Order>("something");
        var expectedOrder = Order.builder().id(expectedId).build();
        when(orderRepository.findById(expectedId)).thenReturn(Optional.of(expectedOrder));

        var order = orderService.getById(expectedId);

        assertSame(order, expectedOrder);
    }

    @Test(expected = EntityNotFoundException.class)
    public void getByIdThrowsIfIdIsInvalid() {
        when(orderRepository.findById(any())).thenReturn(Optional.empty());

        orderService.getById(new Id<>());
    }

}
