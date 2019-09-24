package ru.lastenko.maxim.SRRA_requests.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lastenko.maxim.SRRA_requests.entity.Payment;
import ru.lastenko.maxim.SRRA_requests.repository.PaymentRepository;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public Payment getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Payment> getAll() {
        return repository.findAll();
    }
}
