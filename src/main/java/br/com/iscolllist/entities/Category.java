package br.com.iscolllist.entities;

import jakarta.persistence.Entity;

@Entity
public class Category extends TaskType{
    //A categoria que a classe Task(a Tarefa) pode possuir.

    public Category(String name){
        super(name);
    }

}
