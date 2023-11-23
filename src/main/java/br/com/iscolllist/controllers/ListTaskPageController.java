package br.com.iscolllist.controllers;

import br.com.iscolllist.App;
import br.com.iscolllist.entities.Task;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
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

    public static Task viewTask;

    @FXML
    void backHomePage(ActionEvent event) {
        App.changeScene("homePage.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameTask.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionTask.setCellValueFactory(new PropertyValueFactory<>("description"));

        concludedTask.setCellValueFactory(new PropertyValueFactory<>("concluded"));

        concludedTask.setCellFactory(p -> {
            CheckBox checkBox = new CheckBox();
            TableCell<Task, Boolean> tableCell = new TableCell<>() {

                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null)
                        setGraphic(null);
                    else {
                        setGraphic(checkBox);
                        checkBox.setSelected(item);
                    }
                }
            };

            checkBox.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                Transaction transaction = App.session.beginTransaction();

                Task task = tableCell.getTableRow().getItem();

                task.setConcluded(!task.isConcluded());

                App.session.persist(task);

                transaction.commit();
            });

            tableCell.setAlignment(Pos.CENTER);
            tableCell.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

            return tableCell;
        });

        tasksTable.setEditable(true);

        startDateTask.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        startDateTask.setCellFactory(getDateCell(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

        deadLineTask.setCellValueFactory(new PropertyValueFactory<>("deadLine"));
        deadLineTask.setCellFactory(getDateCell(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

        tasksTable.setItems(listTasks());

        tasksTable.setRowFactory(tv -> {
            TableRow<Task> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    Task clickedTask = row.getItem();

                    App.sendTask(clickedTask);
                    App.changeScene("editTaskPage.fxml");
                } else if (!row.isEmpty() && event.getButton() == MouseButton.SECONDARY) {
                    viewTask = row.getItem();
                    App.changeScene("detailedViewTaks.fxml");
                }
            });

            return row;
        });
    }

    public static <ROW,T extends Temporal> Callback<TableColumn<ROW, T>, TableCell<ROW, T>> getDateCell (DateTimeFormatter format) {
        return column -> {
            return new TableCell<ROW, T> () {
                @Override
                protected void updateItem (T item, boolean empty) {
                    super.updateItem (item, empty);
                    if (item == null || empty) {
                        setText (null);
                    }
                    else {
                        setText (format.format (item));
                    }
                }
            };
        };
    }

    private ObservableList<Task> listTasks() {
        return FXCollections.observableArrayList(App.session.createQuery("from Task", Task.class).list());
    }
}
