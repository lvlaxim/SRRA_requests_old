package ru.gosarhro.SRRA_requests.entity.personal_data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "computer_with_access", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComputerWithAccess {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "ip")
    private String ip;
}
