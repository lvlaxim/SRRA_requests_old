package ru.lastenko.maxim.SRRA_requests.entity.personal_data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "personal_data", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalData {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "initiator")
    private String requestInitiator;

    @Column(name = "shipment")
    private String shipment;
}
