package br.com.iscolllist.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class TaskType implements Serializable {
    //Uma classe generalizada em relação as classes Category e Subject(disciplinas e categorias).

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    @Column
    private String name;

    public TaskType(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
