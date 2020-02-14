package ru.lastenko.maxim.SRRA_requests.repository.personal_data;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lastenko.maxim.SRRA_requests.entity.personal_data.PersonalData;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Integer> {
}
