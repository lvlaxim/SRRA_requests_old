package ru.lastenko.maxim.SRRA_requests.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lastenko.maxim.SRRA_requests.entity.Request;
import ru.lastenko.maxim.SRRA_requests.repository.RequestRepository;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository repository;

    public Request getById(int id){
        return repository.findById(id).orElse(null);
    }

    public List<Request> getAll() {
        return repository.findAll();
    }

    public List<Request> getAllOrderedByIdDesc(){
        return repository.findAllByOrderByIdDesc();
    }
}
