package ru.lastenko.maxim.SRRA_requests.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lastenko.maxim.SRRA_requests.entity.Source;
import ru.lastenko.maxim.SRRA_requests.repository.SourceRepository;

import java.util.List;

@Service
public class SourceService {

    @Autowired
    private SourceRepository repository;

    public Source getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Source> getAll() {
        return repository.findAll();
    }
}
