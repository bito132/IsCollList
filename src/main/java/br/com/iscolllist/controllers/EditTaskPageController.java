package br.com.iscolllist.controllers;

import br.com.iscolllist.App;
import br.com.iscolllist.entities.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.hibernate.Transaction;

import java.net.URL;
import java.util.ResourceBundle;

public class EditTaskPageController implements Initializable {

    @FXML
    private TextArea descriptionTask;

    @FXML
    private TextField nameTask;

    @FXML
    void backHomePage(ActionEvent event) {
        App.changeScene("listTaskPage.fxml");
    }

    @FXML
    void editTask(ActionEvent event) {
        Task task = App.getTask();

        task.setName(nameTask.getText());
        task.setDescription(descriptionTask.getText());

        Transaction transaction = App.session.beginTransaction();
        App.session.persist(task);

        transaction.commit();
        App.changeScene("listTaskPage.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Task task = App.getTask();

        nameTask.setText(task.getName());
        descriptionTask.setText(task.getDescription());
    }
}
