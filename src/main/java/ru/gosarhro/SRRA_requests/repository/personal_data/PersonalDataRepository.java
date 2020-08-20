package ru.gosarhro.SRRA_requests.repository.personal_data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.gosarhro.SRRA_requests.entity.personal_data.PersonalData;

import java.util.List;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Integer> {
    @Query("select id from #{#entityName} where lower(initiator) like lower(:initiator)")
    List<Integer> findOnlyIdsByRequestInitiator(String initiator);

    @Query("select id from #{#entityName} where lower(shipment) like lower(:shipment)")
    List<Integer> findOnlyIdsByShipment(String shipment);

    @Query("select id from #{#entityName} where (lower(initiator) like lower(:initiator)) and (lower(shipment) like lower(:shipment))")
    List<Integer> findOnlyIdsByInitiatorAndShipment(String initiator, String shipment);
}
