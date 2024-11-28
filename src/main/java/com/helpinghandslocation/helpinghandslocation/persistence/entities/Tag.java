package com.helpinghandslocation.helpinghandslocation.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tag")
public class Tag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Location> locations;

    public Tag(long id, String name) {
        this.id = (int) id;
        this.name = name;
    }
}
