package ru.lastenko.maxim.SRRA_requests.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lastenko.maxim.SRRA_requests.entity.Executor;
import ru.lastenko.maxim.SRRA_requests.repository.ExecutorRepository;

import java.util.List;

@Service
public class ExecutorService {

    @Autowired
    private ExecutorRepository repository;

    public Executor save(Executor executor) {
        return repository.save(executor);
    }

    public Executor getById(int Id) {
        return repository.findById(Id).orElse(null);
    }

    public List<Executor> getAll() {
        return repository.findAllByOrderByNameAsc();
    }

    public void deleteById(int Id) {
        repository.deleteById(Id);
    }

}
