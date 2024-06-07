package sspd.bookshop.launch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Bookshop extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Bookshop.class.getResource("/layout/sale.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sale Report");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}