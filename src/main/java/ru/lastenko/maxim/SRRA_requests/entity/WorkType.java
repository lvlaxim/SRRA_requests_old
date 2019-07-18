package ru.lastenko.maxim.SRRA_requests.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "prices", schema = "requests")
public class WorkType {

    @Id
    @SequenceGenerator(name = "prices_price_id_seq", sequenceName = "requests.prices_price_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prices_price_id_seq")
    @Column(name = "price_id")
    private Integer id;

    @Column(name = "work_type")
    private String name;

    @Column(name = "unit")
    private String unit;

    @Column(name = "price")
    private char price;

    public WorkType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public char getPrice() {
        return price;
    }

    public void setPrice(char price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkType workType = (WorkType) o;
        return price == workType.price &&
                id.equals(workType.id) &&
                name.equals(workType.name) &&
                unit.equals(workType.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, unit, price);
    }
}
