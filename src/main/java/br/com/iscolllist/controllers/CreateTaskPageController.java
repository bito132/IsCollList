package br.com.iscolllist.controllers;

import br.com.iscolllist.App;
import br.com.iscolllist.entities.Subject;
import br.com.iscolllist.entities.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import org.hibernate.Transaction;

import java.time.LocalDateTime;

public class CreateTaskPageController {
    @FXML
    private TextField nameTask;

    @FXML
    private TextArea descriptionTask;

    @FXML
    private TextField startDatetimeDay;

    @FXML
    private TextField startDatetimeHour;

    @FXML
    private TextField startDatetimeMinute;

    @FXML
    private TextField startDatetimeMonth;

    @FXML
    private TextField startDatetimeYear;

    @FXML
    private TextField endDatetimeDay;

    @FXML
    private TextField endDatetimeHour;

    @FXML
    private TextField endDatetimeMinute;

    @FXML
    private TextField endDatetimeMonth;

    @FXML
    private TextField endDatetimeYear;

    @FXML
    private Button createTaskBtn;

    @FXML
    void backHomePage(ActionEvent event) {
        App.changeScene("homePage.fxml");
    }

    @FXML
    void createTask(ActionEvent event) {
        if (
            isFieldEmpty(nameTask) || isFieldEmpty(descriptionTask) ||
            !isValidDateTime(startDatetimeDay) ||
            !isValidDateTime(startDatetimeHour) ||
            !isValidDateTime(startDatetimeMinute) ||
            !isValidDateTime(startDatetimeMonth) ||
            !isValidDateTime(startDatetimeYear) ||
            !isValidDateTime(endDatetimeDay) ||
            !isValidDateTime(endDatetimeHour) ||
            !isValidDateTime(endDatetimeMinute) || 
            !isValidDateTime(endDatetimeMonth) ||
            !isValidDateTime(endDatetimeYear)
        ) return;

        LocalDateTime startDateTime = null;

        try {
            startDateTime = getStartDateTime();
        } catch (Exception e) {
            return;
        }

        LocalDateTime endDateTime = null;

        try {
            endDateTime = getEndDateTime();
        } catch (Exception e) {
            return;
        }

        Transaction transaction = App.session.beginTransaction();

        Task task = new Task(
            nameTask.getText(),
            descriptionTask.getText(),
            startDateTime,
            endDateTime,
            new Subject("mat a tica")
        );

        App.session.persist(task);

        transaction.commit();
        App.changeScene("homePage.fxml");
    }

    private boolean isFieldEmpty(TextInputControl textField) {
        return textField.getText().trim().isEmpty();
    }

    private boolean isValidDateTime(TextInputControl textField) {
        if (isFieldEmpty(textField)) return false;

        try {
            Integer.parseInt(textField.getText().trim());
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private LocalDateTime getStartDateTime() {
        return LocalDateTime.of(
            Integer.parseInt(startDatetimeYear.getText()),
            Integer.parseInt(startDatetimeMonth.getText()),
            Integer.parseInt(startDatetimeDay.getText()),
            Integer.parseInt(startDatetimeHour.getText()),
            Integer.parseInt(startDatetimeMinute.getText())
        );
    }

    private LocalDateTime getEndDateTime() {
        return LocalDateTime.of(
            Integer.parseInt(endDatetimeYear.getText()),
            Integer.parseInt(endDatetimeMonth.getText()),
            Integer.parseInt(endDatetimeDay.getText()),
            Integer.parseInt(endDatetimeHour.getText()),
            Integer.parseInt(endDatetimeMinute.getText())
        );
    }
}
