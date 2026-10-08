<<<<<<< HEAD
package com.example.hellofx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloJavaFXApp extends Application {

    @Override
    public void start(Stage stage) {
        Label message = new Label("Welcome ISHMAEL MAPALO LUBOYA!");
        Button startButton = new Button("Start");

        startButton.setOnAction(e -> {
            new CustomerManagerApp().start(new Stage());
            stage.close();
        });

        VBox layout = new VBox(20, message, startButton);
        layout.setAlignment(Pos.CENTER);

        stage.setTitle("My First JavaFX Application-202509735");
        stage.setScene(new Scene(layout, 500, 300));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
=======
package com.example.hellofx;
import javafx.application.Application; import javafx.geometry.Pos; import javafx.scene.Scene; import javafx.scene.control.Button; import javafx.scene.control.Label; import javafx.scene.layout.VBox; import javafx.stage.Stage;
public class HelloJavaFXApp extends Application {
    @Override
    public void start(Stage stage) {
        Label message = new Label("Welcome ISHMAEL MAPALO LUBOYA!");         Button button = new Button("Start");Button resetButton = new Button("Reset");

        resetButton.setOnAction(event ->
                message.setText("Welcome, ISHMAEL MAPALO LUBOYA!")
        );
        button.setOnAction(event ->
            message.setText("Great! You clicked the button.")
        );
        VBox layout = new VBox(20);         layout.setAlignment(Pos.CENTER);         layout.getChildren().addAll(message, button);
        Scene scene = new Scene(layout, 500, 300);         stage.setTitle("My First JavaFX Application-202509735");         stage.setScene(scene);         stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
>>>>>>> origin/master
