package br.com.iscolllist.controllers;

import br.com.iscolllist.App;
import br.com.iscolllist.entities.Subject;
import br.com.iscolllist.entities.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DetailedViewTaksController implements Initializable {
    @FXML
    private Text descriptionTask;

    @FXML
    private Text endDate;

    @FXML
    private Text startDate;

    @FXML
    private Text subjectTask;

    @FXML
    private Text taskDone;

    @FXML
    private Text taskName;


    @FXML
    void backHomePage(ActionEvent event) {
        App.changeScene("listTaskPage.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Task task = ListTaskPageController.viewTask;

        taskName.setText(task.getName());
        descriptionTask.setText("Descrição: " + task.getDescription());
        taskDone.setText("Foi feito? " + (task.isConcluded() ? "Sim" : "Não"));
        subjectTask.setText("Disciplina: " + App.session.createQuery(
                        "from Subject where id = :id", Subject.class)
                .setParameter("id", task.getSubject().getId()).list().get(0).getName());
        startDate.setText("Data de início: " + DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(task.getStartDate()));
        endDate.setText("Data de término: " + DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(task.getDeadLine()));
    }
}
