package br.com.iscolllist.controllers;

import br.com.iscolllist.App;
import br.com.iscolllist.entities.Subject;
import br.com.iscolllist.entities.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.hibernate.Transaction;

import java.io.IOException;
import java.time.LocalDateTime;

public class CreateTaskPageController {

    @FXML
    private TextArea descriptionTask;

    @FXML
    private TextField nameTask;

    @FXML
    private Button createTaskBtn;

    @FXML
    void backHomePage(ActionEvent event) throws IOException {
        App.changeScene("homePage.fxml");
    }

    @FXML
    void createTask(ActionEvent event) {

        Transaction transaction = App.session.beginTransaction();

        Task task = new Task(nameTask.getText(), descriptionTask.getText(), LocalDateTime.of(2023, 8, 7, 12, 0),
                LocalDateTime.of(2023, 10, 7, 12, 0), new Subject("mat a tica"));

        App.session.persist(task);

        transaction.commit();
    }
}
