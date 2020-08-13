package ru.lastenko.maxim.SRRA_requests.repository.personal_data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;
import ru.lastenko.maxim.SRRA_requests.entity.personal_data.PersonalData;

import java.util.List;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Integer> {
    @Query("select id from #{#entityName} where lower(initiator) like lower(?1)")
    List<Integer> findOnlyIdsByRequestInitiator(@PathVariable String initiator);
}
