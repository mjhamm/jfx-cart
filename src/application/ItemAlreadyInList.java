package application;

import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ItemAlreadyInList {
	
	public static void display() {
		
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("ERROR: Item Already In Cart");
		window.setMinWidth(150);
		window.setMinHeight(100);
		window.setResizable(false);
		Label errorInCartLabel = new Label("This error is caused because this item is already in your cart.\nTo fix this:\n"
				+ "Please remove the item in the table and adjust the quantity.");
		Button okButton = new Button("OK!");
		okButton.setOnAction(e -> {
			window.close();
		});
		
		GridPane errorInCartGridPane = new GridPane();
		errorInCartGridPane.setHgap(10);
		errorInCartGridPane.setVgap(5);
		errorInCartGridPane.setPadding(new Insets(5,5,5,5));
		
		errorInCartGridPane.add(errorInCartLabel, 0, 0);
		errorInCartGridPane.add(okButton, 0, 1);
		GridPane.setHalignment(errorInCartLabel, HPos.CENTER);
		GridPane.setHalignment(okButton, HPos.CENTER);
		
		Scene scene = new Scene(errorInCartGridPane);
		window.setScene(scene);
		window.show();
	}
	
}


