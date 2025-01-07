package net.nvsoftware.OrderService.service;


import net.nvsoftware.OrderService.model.OrderRequest;
import net.nvsoftware.OrderService.model.OrderResponse;
import org.springframework.stereotype.Service;


public interface OrderService {
    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetailById(long orderId);
}
