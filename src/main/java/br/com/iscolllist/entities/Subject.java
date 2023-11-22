package br.com.iscolllist.entities;

import jakarta.persistence.Entity;

@Entity
public class Subject extends TaskType{
    //A disciplina que a classe Task(a Tarefa) pode possuir.

    public Subject(String name) {
        super(name);
    }

    public Subject() {
        super();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
