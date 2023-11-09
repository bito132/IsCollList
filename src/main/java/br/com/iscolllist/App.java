package br.com.iscolllist;

import br.com.iscolllist.entities.Subject;
import br.com.iscolllist.entities.Task;
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

    public static Session session = HibernateUtil.getSessionFactory().openSession();
    public static Transaction transaction; //= session.beginTransaction();
    public static void changeScene(String sceneName) throws IOException {
        FXMLLoader fXMLLoader;
        fXMLLoader = new FXMLLoader(App.class.getResource(sceneName));
        Scene scene = new Scene(fXMLLoader.load(), 600, 420);
        stage.setScene(scene);
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
        changeScene("homePage.fxml");
        stage.show();
    }

    @Override
    public void init() throws Exception{
        Task tarefakkj = new Task(
                "mateticakkj",
                "é pra fazer",
                LocalDateTime.of(2023, 8, 7, 12, 0),
                LocalDateTime.of(2023, 10, 7, 12, 0),
                new Subject("mat a tica")
        );

       // session.get(tarefakkj);
    }

    public static void main(String[] args) {
        launch();
    }
}