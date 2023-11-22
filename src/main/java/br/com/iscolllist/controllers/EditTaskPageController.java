package br.com.iscolllist.controllers;

import br.com.iscolllist.App;
import br.com.iscolllist.entities.Subject;
import br.com.iscolllist.entities.Task;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import org.hibernate.Transaction;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class EditTaskPageController implements Initializable {

    @FXML
    private TextArea descriptionTask;

    @FXML
    private TextField nameTask;

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
    private ComboBox<Subject> subjectsTask;

    @FXML
    void backHomePage(ActionEvent event) {
        App.changeScene("listTaskPage.fxml");
    }

    @FXML
    void editTask(ActionEvent event) {
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

        Task task = App.getTask();

        task.setName(nameTask.getText());
        task.setDescription(descriptionTask.getText());
        task.setStartDate(startDateTime);
        task.setDeadLine(endDateTime);
        task.setSubject(subjectsTask.getValue());

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

        startDatetimeYear.setText(String.valueOf(task.getStartDate().getYear()));
        startDatetimeMonth.setText(String.valueOf(task.getStartDate().getMonthValue()));
        startDatetimeDay.setText(String.valueOf(task.getStartDate().getDayOfMonth()));
        startDatetimeHour.setText(String.valueOf(task.getStartDate().getHour()));
        startDatetimeMinute.setText(String.valueOf(task.getStartDate().getMinute()));

        endDatetimeYear.setText(String.valueOf(task.getDeadLine().getYear()));
        endDatetimeMonth.setText(String.valueOf(task.getDeadLine().getMonthValue()));
        endDatetimeDay.setText(String.valueOf(task.getDeadLine().getDayOfMonth()));
        endDatetimeHour.setText(String.valueOf(task.getDeadLine().getHour()));
        endDatetimeMinute.setText(String.valueOf(task.getDeadLine().getMinute()));

        subjectsTask.setValue(App.session.createQuery(
                        "from Subject where id = :id", Subject.class)
                .setParameter("id", task.getSubject().getId()).list().get(0)
        );
        subjectsTask.setItems(
                FXCollections.observableArrayList(
                        App.session.createQuery("from Subject", Subject.class).list()
                )
        );
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
