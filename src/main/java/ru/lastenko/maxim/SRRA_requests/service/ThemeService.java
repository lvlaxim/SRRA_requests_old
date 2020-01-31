package ru.lastenko.maxim.SRRA_requests.service;

import org.springframework.stereotype.Service;
import ru.lastenko.maxim.SRRA_requests.entity.Theme;
import ru.lastenko.maxim.SRRA_requests.repository.ThemeRepository;

import java.util.List;

@Service
public class ThemeService {

    private final ThemeRepository repository;

    public ThemeService(ThemeRepository repository) {
        this.repository = repository;
    }

    public Theme getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Theme> getAll() {
        return repository.findAll();
    }
}
