package sspd.bookshop.launch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Bookshop extends Application {

    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/loginform.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("အကောင့်ဝင်ရန်။");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {



        launch();

    }
}