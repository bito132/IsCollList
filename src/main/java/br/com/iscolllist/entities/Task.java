package br.com.iscolllist.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Table
@Entity
public class Task {
    //A tarefa que o usuário irá adicionar para armazenar suas tarefas escolares(com nome, descrição, etc.).

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private boolean concluded;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "IdUser")
    private User IdUser;
    @Column
    private LocalDateTime startDate;
    @Column
    private LocalDateTime deadLine;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
           name = "task-category",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    public Task() {
        this.categories = new ArrayList<>();
    }

    public Task(String name, String description, User idUser, LocalDateTime startDate, LocalDateTime deadLine, Subject subject) {
        setName(name);
        setDescription(description);
        setConcluded(false);
        setIdUser(idUser);
        setStartDate(startDate);
        setDeadLine(deadLine);
        setSubject(subject);
        this.categories = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isConcluded() {
        return concluded;
    }

    public void setConcluded(boolean concluded) {
        this.concluded = concluded;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public User getIdUser() {
        return IdUser;
    }

    public void setIdUser(User idUser) {
        IdUser = idUser;
    }
}
