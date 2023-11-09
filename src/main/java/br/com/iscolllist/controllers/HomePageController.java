package br.com.iscolllist.controllers;

import br.com.iscolllist.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomePageController {

    @FXML
    private Button AddTaskBottom;

    @FXML
    void clickAddTask(ActionEvent event) throws IOException {
        App.changeScene("createTaskPage.fxml");
        System.out.println("kkj deu certokkj1");

    }

    @FXML
    void clickEditTask(ActionEvent event) throws IOException {
        App.changeScene("editTaskPage.fxml");
        System.out.println("kkj deu certokkj2");

    }

    @FXML
    void clickShowTask(ActionEvent event) throws IOException {
        App.changeScene("listTaskPage.fxml");
        System.out.println("kkj deu certokkj3");

    }

}
