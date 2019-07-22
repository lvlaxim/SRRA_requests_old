package ru.lastenko.maxim.SRRA_requests.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "themes", schema = "requests")
public class Theme {

    @Id
    @SequenceGenerator(name = "themes_theme_id_seq", sequenceName = "requests.themes_theme_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "themes_theme_id_seq")
    @Column(name = "theme_id")
    private Integer id;

    @Column(name = "theme")
    private String name;

    public Theme() {
    }

    public Theme(Integer id, String name) {
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
        Theme theme = (Theme) o;
        return id.equals(theme.id) &&
                name.equals(theme.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
