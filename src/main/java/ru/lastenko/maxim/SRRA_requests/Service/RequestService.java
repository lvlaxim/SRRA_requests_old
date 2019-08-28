package ru.lastenko.maxim.SRRA_requests.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.lastenko.maxim.SRRA_requests.entity.Request;
import ru.lastenko.maxim.SRRA_requests.repository.RequestRepository;

import java.util.LinkedList;
import java.util.List;

import static ru.lastenko.maxim.SRRA_requests.repository.RequestSpecifications.*;


@Service
public class RequestService {

    @Autowired
    private RequestRepository repository;

    public Request getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Request> getAll() {
        return repository.findAll();
    }

    public Page<Request> getAllOrderedByIdDesc(Pageable pageable) {
        return repository.findAllByOrderByIdDesc(pageable);

    }

    public Page<Request> getByFilter(Integer id, String answer, Pageable pageable) {
        Specification specification = buildSpecification(id, answer);
        return repository.findAll(specification, pageable);
    }

    public Page<Request> findAllPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }

    private static Specification buildSpecification(Integer id, String answer) {
        List<Specification> specifications = new LinkedList<>();
        if (id != null) {
            specifications.add(hasId(id));
        }
        if (answer != null && !answer.isEmpty()) {
            specifications.add(answerContains(answer));
        }
        int size = specifications.size();
        Specification specification;
        if (size > 0) {
//            specification = Specification.where(specifications.get(0));
            specification = specifications.get(0);

            if (size > 1) {
                for (int i = 1; i < size; i++) {
                    specification = specification.and(specifications.get(i));
                }
            }
            return specification;
        }

        return null;
    }
}
