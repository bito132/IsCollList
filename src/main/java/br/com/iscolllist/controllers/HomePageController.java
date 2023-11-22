package br.com.iscolllist.controllers;

import br.com.iscolllist.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomePageController {

    @FXML
    void clickAddTask(ActionEvent event) {
        App.changeScene("createTaskPage.fxml");
    }

    @FXML
    void clickShowTask(ActionEvent event)  {
        App.changeScene("listTaskPage.fxml");
    }
}
