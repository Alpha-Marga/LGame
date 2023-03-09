    package com.example.lgame;

    import javafx.application.Application;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Scene;
    import javafx.scene.layout.GridPane;
    import javafx.stage.Stage;

    import java.io.IOException;

    public class HelloApplication extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Hello!");
            stage.setScene(scene);

            LGameGUI controller = fxmlLoader.getController();
            controller.setMainStage(stage); // appel de la méthode setMainStage()

            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }
    }