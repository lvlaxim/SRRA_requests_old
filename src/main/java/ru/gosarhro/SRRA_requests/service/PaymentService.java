package ru.gosarhro.SRRA_requests.service;

import org.springframework.stereotype.Service;
import ru.gosarhro.SRRA_requests.repository.requests.PaymentRepository;
import ru.gosarhro.SRRA_requests.entity.requests.Payment;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public Payment getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Payment> getAll() {
        return repository.findAll();
    }
}
