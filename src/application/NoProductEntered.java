package application;

import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NoProductEntered {
	
	public static void display() {
		
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("ERROR: Product Name/Product Price");
		window.setMinWidth(150);
		window.setMinHeight(100);
		window.setResizable(false);
		Label productErrorLabel = new Label("This Error can be caused by multiple things:\n1. You have no Product Name Entered.\n2. You have characters other than Alphabetical in your Product Name.\n"
				+ "3. You have no Product Price Entered.\n4. You have characters other than Integers within your Product Price.");
		Button continueButton = new Button("OK!");
		continueButton.setOnAction(e -> {
			window.close();
		});
		
		GridPane errorProductNameGridPane = new GridPane();
		errorProductNameGridPane.setHgap(2);
		errorProductNameGridPane.setVgap(5);
		errorProductNameGridPane.setPadding(new Insets(5,5,5,5));
		
		errorProductNameGridPane.add(productErrorLabel, 0, 0);
		errorProductNameGridPane.add(continueButton, 0, 1);
		GridPane.setHalignment(productErrorLabel, HPos.CENTER);
		GridPane.setHalignment(continueButton, HPos.CENTER);
		
		Scene scene = new Scene(errorProductNameGridPane);
		window.setScene(scene);
		window.show();
	}
	
}

