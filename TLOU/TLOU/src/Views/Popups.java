package Views;

import javafx.stage.*; 
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class Popups {
	
	public static void popUps(String title ,String wrongMove) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        Label message = new Label(wrongMove);
        Button exit = new Button("Ok");
        exit.setOnAction(e -> window.close());
        VBox layout = new VBox(30);
        layout.getChildren().addAll(message, exit);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
		
	}

}
