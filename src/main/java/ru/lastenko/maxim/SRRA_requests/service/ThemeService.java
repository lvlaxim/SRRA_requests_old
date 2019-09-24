package ru.lastenko.maxim.SRRA_requests.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lastenko.maxim.SRRA_requests.entity.Theme;
import ru.lastenko.maxim.SRRA_requests.repository.ThemeRepository;

import java.util.List;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository repository;

    public Theme getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Theme> getAll() {
        return repository.findAll();
    }
}
