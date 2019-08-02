package ru.lastenko.maxim.SRRA_requests.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @SequenceGenerator(name = "departments_department_id_seq", sequenceName = "requests.departments_department_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departments_department_id_seq")
    @Column(name = "department_id")
    private Integer id;

    @Column(name = "department_head")
    private String head;

    @Column(name = "department")
    private String name;

    public Department() {
    }

    public Department(Integer id, String head, String name) {
        this.id = id;
        this.head = head;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id.equals(that.id) &&
                head.equals(that.head) &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, head, name);
    }
}
