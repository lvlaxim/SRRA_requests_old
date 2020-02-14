package ru.lastenko.maxim.SRRA_requests.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lastenko.maxim.SRRA_requests.entity.payment.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
