package net.nvsoftware.OrderService.service;


import net.nvsoftware.OrderService.model.OrderRequest;
import org.springframework.stereotype.Service;


public interface OrderService {
    long placeOrder(OrderRequest orderRequest);
}
