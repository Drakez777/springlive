package net.nvsoftware.PaymentService.service;

import net.nvsoftware.PaymentService.entity.PaymentEntity;
import net.nvsoftware.PaymentService.model.PaymentRequest;
import net.nvsoftware.PaymentService.model.PaymentResponse;
import net.nvsoftware.PaymentService.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        PaymentEntity paymentEntity = PaymentEntity.builder()
                .orderId(paymentRequest.getOrderId())
                .paymentMode(paymentRequest.getPaymentMode())
                .totalAmount(paymentRequest.getTotalAmount())
                .paymentDate(Instant.now())
                .paymentStatus("SUCCESS")
                .build();

        paymentRepository.save(paymentEntity);
        return paymentEntity.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailByOrderId(long orderId) {

        PaymentEntity paymentEntity = paymentRepository.findByOrderId(orderId);

        PaymentResponse paymentResponse = PaymentResponse.builder()
                .id(paymentEntity.getId())
                .orderId(paymentEntity.getOrderId())
                .paymentStatus(paymentEntity.getPaymentStatus())
                .paymentMode(paymentEntity.getPaymentMode())
                .totalAmount(paymentEntity.getTotalAmount())
                .paymentDate(paymentEntity.getPaymentDate())
                .build();

        return paymentResponse;
    }
}
