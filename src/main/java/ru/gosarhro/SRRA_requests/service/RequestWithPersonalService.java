package ru.gosarhro.SRRA_requests.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gosarhro.SRRA_requests.repository.personal_data.PersonalDataRepository;
import ru.gosarhro.SRRA_requests.repository.requests.RequestRepository;
import ru.gosarhro.SRRA_requests.util.RequestFilter;
import ru.gosarhro.SRRA_requests.entity.requests.Request;

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
        Specification spec;
        if (!filter.getInitiator().equals("") && filter.getShipment().equals("")) {
            List<Integer> personalDataIds = personalDataRepository.findOnlyIdsByRequestInitiator("%" + filter.getInitiator() + "%");
            spec = (request, query, criteriaBuilder) -> request.get("id").in(personalDataIds);
        }
        else if (filter.getInitiator().equals("") && !filter.getShipment().equals("")) {
            List<Integer> personalDataIds = personalDataRepository.findOnlyIdsByShipment("%" + filter.getShipment() + "%");
            spec = (request, query, criteriaBuilder) -> request.get("id").in(personalDataIds);
        }
        else  {
            List<Integer> personalDataIds = personalDataRepository.findOnlyIdsByInitiatorAndShipment("%" + filter.getInitiator() + "%", "%" + filter.getShipment() + "%");
            spec = (request, query, criteriaBuilder) -> request.get("id").in(personalDataIds);
        }
        if (filter.getSpecification() != null) spec = filter.getSpecification().and(spec);
        return requestRepository.findAll(spec, pageable);
    }
}
