package com.adanfm.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="state")
public class State implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String acron;

    @JsonIgnore
    @OneToMany(mappedBy="state")
    private List<City> cities = new ArrayList<>();

    public State() {}

    public State(Integer id, String name, String acron) {
        this.id = id;
        this.name = name;
        this.acron = acron;
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

    public String getAcron() {
        return acron;
    }

    public void setAcron(String acron) {
        this.acron = acron;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(id, state.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
