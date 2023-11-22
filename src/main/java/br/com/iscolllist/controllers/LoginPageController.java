package br.com.iscolllist.controllers;

import br.com.iscolllist.App;
import br.com.iscolllist.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.query.sql.internal.SQLQueryParser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML TextField userNameLogin;

    @FXML PasswordField userPasswordLogin;

    @FXML Label warningTextLogin;

    @FXML
    void initialize() {

    }

    @FXML
    public void goToSignUp(){
        App.changeScene("signUp.fxml");
    }


    public boolean validLogin(){
        return App.session.createQuery("from User where name = :name and password = :password", User.class)
                .setParameter("name", userNameLogin.getText())
                .setParameter("password", userPasswordLogin.getText())
                .uniqueResult() != null;
    }
    @FXML
    public void loginUser(ActionEvent event){


        if(validLogin())
            App.changeScene("homePage.fxml");
        else{
            warningTextLogin.setText("usuário ou senha inválidos");
        }
    }

}
