package ru.lastenko.maxim.SRRA_requests.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sources", schema = "requests")
public class Source {

    @Id
    @SequenceGenerator(name = "sources_source_id_seq", sequenceName = "requests.sources_source_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sources_source_id_seq")
    @Column(name = "source_id")
    private Integer id;

    @Column(name = "sources")
    private String name;

    public Source() {
    }

    public Source(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Source source = (Source) o;
        return id.equals(source.id) &&
                name.equals(source.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
