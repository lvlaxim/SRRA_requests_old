package ru.lastenko.maxim.SRRA_requests.entity.rubric;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rubrics")
public class Rubric {

    public static final Rubric EMPTY_RUBRIC = new Rubric(29, "", "");

    @Id
    @SequenceGenerator(name = "rubrics_rubric_id_seq", sequenceName = "requests.rubrics_rubric_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rubrics_rubric_id_seq")
    @Column(name = "rubric_id")
    private Integer id;

    @Column(name = "rubric_code")
    private String rubric;

    @Column(name = "rubric")
    private String name;

    public Rubric() {
    }

    public Rubric(Integer id, String rubric, String name) {
        this.id = id;
        this.rubric = rubric;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRubric() {
        return rubric;
    }

    public void setRubric(String rubric) {
        this.rubric = rubric;
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
        Rubric rubric1 = (Rubric) o;
        return id.equals(rubric1.id) &&
                rubric.equals(rubric1.rubric) &&
                name.equals(rubric1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rubric, name);
    }
}
