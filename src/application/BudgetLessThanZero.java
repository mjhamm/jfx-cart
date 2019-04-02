package application;

import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BudgetLessThanZero {
	
	public static void display() {
		
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("ERROR: Budget Less Than Zero");
		window.setMinWidth(240);
		window.setMinHeight(100);
		window.setResizable(false);
		Label budgetErrorZeroLabel = new Label("Reason for this error: Your budget is 0 or less than 0.");
		Button okButton = new Button("OK!");
		okButton.setOnAction(e -> {
			window.close();
		});
		
		GridPane errorBudgetZeroGridPane = new GridPane();
		errorBudgetZeroGridPane.setHgap(10);
		errorBudgetZeroGridPane.setVgap(5);
		errorBudgetZeroGridPane.setPadding(new Insets(5,5,5,5));
		
		errorBudgetZeroGridPane.add(budgetErrorZeroLabel, 0, 0);
		errorBudgetZeroGridPane.add(okButton, 0, 1);
		GridPane.setValignment(budgetErrorZeroLabel, VPos.CENTER);
		GridPane.setHalignment(budgetErrorZeroLabel, HPos.CENTER);
		GridPane.setHalignment(okButton, HPos.CENTER);
		
		Scene scene = new Scene(errorBudgetZeroGridPane);
		window.setScene(scene);
		window.show();
	}
	
}

