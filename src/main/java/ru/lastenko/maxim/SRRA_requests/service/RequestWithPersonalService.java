package ru.lastenko.maxim.SRRA_requests.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.lastenko.maxim.SRRA_requests.entity.requests.Request;
import ru.lastenko.maxim.SRRA_requests.repository.personal_data.PersonalDataRepository;
import ru.lastenko.maxim.SRRA_requests.repository.requests.RequestRepository;
import ru.lastenko.maxim.SRRA_requests.util.RequestFilter;

import java.util.List;


@Service
public class RequestWithPersonalService {
    private final RequestRepository requestRepository;
    private final PersonalDataRepository personalDataRepository;

    public RequestWithPersonalService(RequestRepository requestRepository, PersonalDataRepository personalDataRepository) {
        this.requestRepository = requestRepository;
        this.personalDataRepository = personalDataRepository;
    }

    public Page<Request> getByFilterAndPersonal(RequestFilter filter, Pageable pageable) {
        List<Integer> personalDataIdsByInitiator = personalDataRepository.findOnlyIdsByRequestInitiator("%"+filter.getInitiator()+"%");
        Specification spec = (request, query, criteriaBuilder) -> request.get("id").in(personalDataIdsByInitiator);
        if (filter.getSpecification() != null) spec = filter.getSpecification().and(spec);
        return requestRepository.findAll(spec, pageable);
    }
}
