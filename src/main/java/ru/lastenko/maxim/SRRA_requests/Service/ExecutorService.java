package ru.lastenko.maxim.SRRA_requests.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lastenko.maxim.SRRA_requests.entity.Executor;
import ru.lastenko.maxim.SRRA_requests.repository.ExecutorRepository;

import java.util.List;

@Service
public class ExecutorService {

    private ExecutorRepository repository;

    @Autowired
    public void setRepository(ExecutorRepository repository) {
        this.repository = repository;
    }

    public Executor save(Executor executor) {
        return repository.save(executor);
    }

    public Executor getById(int Id) {
        return repository.getOne(Id);
    }

    public List<Executor> getAll() {
        return repository.findAll();
    }

    public void deleteById(int Id) {
        repository.deleteById(Id);
    }

}
