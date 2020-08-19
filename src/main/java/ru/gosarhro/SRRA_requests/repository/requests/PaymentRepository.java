package ru.gosarhro.SRRA_requests.repository.requests;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gosarhro.SRRA_requests.entity.requests.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
