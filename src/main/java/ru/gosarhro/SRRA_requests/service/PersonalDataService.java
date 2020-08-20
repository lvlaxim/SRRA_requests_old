package ru.gosarhro.SRRA_requests.service;

import org.springframework.stereotype.Service;
import ru.gosarhro.SRRA_requests.entity.personal_data.PersonalData;
import ru.gosarhro.SRRA_requests.repository.personal_data.PersonalDataRepository;

import java.util.List;

@Service
public class PersonalDataService {
    private final PersonalDataRepository repository;

    public PersonalDataService(PersonalDataRepository repository) {
        this.repository = repository;
    }

    public PersonalData getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<PersonalData> getAll() {
        return repository.findAll();
    }

    public PersonalData save(PersonalData personalData) {
        return repository.save(personalData);
    }
}
