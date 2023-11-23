package br.com.iscolllist;

import br.com.iscolllist.entities.Subject;
import br.com.iscolllist.entities.Task;
import br.com.iscolllist.entities.User;
import br.com.iscolllist.util.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.time.LocalDateTime;

public class App extends Application {

    private static Stage stage;

    public static User userLogade;

    public static Session session = HibernateUtil.getSessionFactory().openSession();
    public static Transaction transaction; //= session.beginTransaction();
    public static void changeScene(String sceneName) {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader(App.class.getResource(sceneName));
            Scene scene = new Scene(fXMLLoader.load(), 600, 420);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println("Arquivo não existe");
        }
    }

    public static void sendTask(Task task) {
        stage.setUserData(task);
    }

    public static Task getTask() {
        return (Task) stage.getUserData();
    }

    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
       // FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("homePage.fxml"));
       // Scene sceneHomePage = new Scene(fxmlLoader.load(), 600, 420);
      //  Scene sceneCreateTaskPage = new Scene(fxmlLoader.load(), 600, 420);
      //  Scene sceneEditTaskPage = new Scene(fxmlLoader.load(), 600, 420);
      //  Scene sceneListtaskPage = new Scene(fxmlLoader.load(), 600, 420);
        stage.setTitle("testekkj");
        changeScene("login.fxml");
        stage.show();
    }

    @Override
    public void init() throws Exception{
        if (!session.createQuery("from Subject", Subject.class).list().isEmpty()) return;

        Subject subject1 = new Subject("Matemática");
        Subject subject2 = new Subject("Português");
        Subject subject3 = new Subject("Química");
        Subject subject4 = new Subject("Física");
        Subject subject5 = new Subject("Geografia");
        Subject subject6 = new Subject("História");

        Transaction transaction = session.beginTransaction();

        session.persist(subject1);
        session.persist(subject2);
        session.persist(subject3);
        session.persist(subject4);
        session.persist(subject5);
        session.persist(subject6);

        transaction.commit();
    }

    public static void main(String[] args) {
        launch();
    }
}