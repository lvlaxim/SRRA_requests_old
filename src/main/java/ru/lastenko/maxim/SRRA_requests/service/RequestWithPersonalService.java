package ru.lastenko.maxim.SRRA_requests.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.lastenko.maxim.SRRA_requests.entity.requests.Request;
import ru.lastenko.maxim.SRRA_requests.repository.personal_data.PersonalDataRepository;
import ru.lastenko.maxim.SRRA_requests.repository.requests.RequestRepository;
import ru.lastenko.maxim.SRRA_requests.util.RequestFilter;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RequestWithPersonalService {
    private final RequestRepository requestRepository;
    private final PersonalDataRepository personalDataRepository;

    public RequestWithPersonalService(RequestRepository requestRepository, PersonalDataRepository personalDataRepository) {
        this.requestRepository = requestRepository;
        this.personalDataRepository = personalDataRepository;
    }

    public Page<Request> getByFilterAndInitiator(RequestFilter filter, Pageable pageable) {
        List<Integer> personalDataIdsByInitiator = personalDataRepository.findOnlyIdsByRequestInitiator("%"+filter.getInitiator()+"%");
        //Specification spec = (request, query, criteriaBuilder) -> request.get("id").in(personalDataIdsByInitiator);
        List<Request> requests = ((List<Request>) requestRepository.findAll(filter.getSpecification())).stream().filter(request -> personalDataIdsByInitiator.contains(request.getId())).collect(Collectors.toList());
        return new PageImpl<Request>(requests);
    }
}
