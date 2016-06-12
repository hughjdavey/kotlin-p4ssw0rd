package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class java {
    public static void main(String... args) {
        Button btn = new Button();
        EventHandler<KeyEvent> handler = evt -> {
            if (evt.getCode() == KeyCode.ENTER)
                ((Button) evt.getSource()).fire();
        };
        btn.setOnKeyPressed(handler);

        Stage primaryStage = new Stage();
        btn.setOnAction(
                event -> {
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(primaryStage);
                    VBox dialogVbox = new VBox(20);
                    dialogVbox.getChildren().add(new Text("This is a Dialog"));
                    Scene dialogScene = new Scene(dialogVbox, 300, 200);
                    dialog.setScene(dialogScene);
                    dialog.show();
                });

        HBox hbox = new HBox();
        hbox.getChildrenUnmodifiable().filtered( node -> node instanceof TextField).get(0);

        TextField text = new TextField();
        text.textProperty().addListener((observable, oldValue, newValue) -> System.out.println(newValue));

    }
}
