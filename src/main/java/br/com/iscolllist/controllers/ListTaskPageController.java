package br.com.iscolllist.controllers;

import br.com.iscolllist.App;
import br.com.iscolllist.entities.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ListTaskPageController implements Initializable {

    @FXML
    private TableColumn<Task, Boolean> concludedTask;

    @FXML
    private TableColumn<Task, LocalDateTime> deadLineTask;

    @FXML
    private TableColumn<Task, String> descriptionTask;

    @FXML
    private TableColumn<Task, String> nameTask;

    @FXML
    private TableColumn<Task, LocalDateTime> startDateTask;

    @FXML
    private TableView<Task> tasksTable;

    @FXML
    void backHomePage(ActionEvent event) {
        App.changeScene("homePage.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameTask.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionTask.setCellValueFactory(new PropertyValueFactory<>("description"));
        concludedTask.setCellValueFactory(new PropertyValueFactory<>("concluded"));
        startDateTask.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        deadLineTask.setCellValueFactory(new PropertyValueFactory<>("deadLine"));

        tasksTable.setItems(listTasks());

        tasksTable.setRowFactory(tv -> {
            TableRow<Task> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    Task clickedTask = row.getItem();

                    App.sendTask(clickedTask);
                    App.changeScene("editTaskPage.fxml");
                }
            });

            return row;
        });
    }

    private ObservableList<Task> listTasks() {
        return FXCollections.observableArrayList(App.session.createQuery("from Task", Task.class).list());
    }
}
