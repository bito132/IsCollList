package br.com.iscolllist.controllers;

import br.com.iscolllist.App;
import br.com.iscolllist.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField userNameSignUp;

    @FXML
    private PasswordField userPasswordSignUp;

    @FXML
    private PasswordField userConfirmPasswordSignUp;

    @FXML Label warningTextSignUp;

    @FXML
    void initialize() {

    }

    @FXML
    public void backLoginPage(ActionEvent event){
        App.changeScene("login.fxml");
    }

    @FXML
    public void signUpUser(ActionEvent event){
        if(!(userNameSignUp.getText().equals("")) && !(userPasswordSignUp.getText().equals(""))){
            if(userPasswordSignUp.getText().equals(userConfirmPasswordSignUp.getText())){

                Transaction transaction = App.session.beginTransaction();

                User user = new User(userNameSignUp.getText(), userPasswordSignUp.getText());

                try {
                    App.session.persist(user);

                    transaction.commit();

                    App.changeScene("login.fxml");
                }

                catch(Exception e){
                    warningTextSignUp.setText("Nome de usuário já existentekkj");
                    transaction.rollback();
                }
            }
            else{
                userConfirmPasswordSignUp.setText("");
                warningTextSignUp.setText("Senha inválidakkj");
            }
        }
        else{
            warningTextSignUp.setText("Nome ou Senha inválidoskkj");
        }
    }

}