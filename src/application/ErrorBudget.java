package application;

import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorBudget {
	
	public static void display() {
		
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Budget Error");
		window.setMinWidth(140);
		window.setMinHeight(100);
		window.setResizable(false);
		Label budgetErrorLabel = new Label("This is error is caused because\n\tyour budget is empty.");
		Button willDoButton = new Button("OK!");
		willDoButton.setOnAction(e -> {
			window.close();
		});
		
		GridPane errorBudgetGridPane = new GridPane();
		errorBudgetGridPane.setHgap(10);
		errorBudgetGridPane.setVgap(5);
		errorBudgetGridPane.setPadding(new Insets(5,5,5,5));
		
		errorBudgetGridPane.add(budgetErrorLabel, 0, 0);
		errorBudgetGridPane.add(willDoButton, 0, 1);
		GridPane.setHalignment(budgetErrorLabel, HPos.CENTER);
		GridPane.setHalignment(willDoButton, HPos.CENTER);
		
		Scene scene = new Scene(errorBudgetGridPane);
		window.setScene(scene);
		window.show();
	}
	
}

