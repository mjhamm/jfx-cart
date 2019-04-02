package application;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SampleController implements Initializable {
	
	//LISTS FOR ITEMS, ITEMSPURCHASED, ITEMSNOTPURCHASED
	public ArrayList<Item> items = new ArrayList<Item>();
	
	public ArrayList<Item> itemsPurchased = new ArrayList<Item>();
	
	public ArrayList<Item> itemsNotPurchased = new ArrayList<Item>();
	
	//CREATE BUDGET ONLY ALLOW FOR INTEGERS, "." AND "$"
	public void createBudget(ActionEvent makeBudget) {
		if (budgetTotalTextField.getText() == null || budgetTotalTextField.getText().trim().isEmpty()) {
			application.ErrorBudget.display();		
		}
		else if (budgetTotalTextField.getText().matches("\\$\\d*(\\.\\d*)?") || budgetTotalTextField.getText().matches("\\d*(\\.\\d*)?")) {
			checkBudget();
			goShoppingButton.setDisable(true);
		}
		else {
			application.ErrorBudget.display();
		}
	}
	
	//CHECKS BUDGET INPUT FOR $
	public void checkBudget() {
		if (budgetTotalTextField.getText().contains("$")) {
			if (Double.parseDouble(budgetTotalTextField.getText().replace("$", "")) > 0) {
				userTotalBudgetLabel.setText(budgetTotalTextField.getText());
				setObjectsVisable();
				}
			else {
				application.BudgetLessThanZero.display();
			}
		}
		else {
			if (Double.parseDouble(budgetTotalTextField.getText()) > 0) {
				userTotalBudgetLabel.setText("$" + budgetTotalTextField.getText());
				setObjectsVisable();
			}
			else {
				application.BudgetLessThanZero.display();
			}
		}
	}

	//ADD ITEM
	public void addItemToCart(ActionEvent addItem) {
		if (productTextBox.getText() == null || productTextBox.getText().trim().isEmpty()) {
			application.NoProductEntered.display();
		}
		else if (!productTextBox.getText().matches("[a-zA-Z]*")) {
			application.NoProductEntered.display();
		}
		else if (enterItemPrice.getText() == null || enterItemPrice.getText().trim().isEmpty()) {
			application.NoProductEntered.display();
		}
		else if (!enterItemPrice.getText().matches("\\d*(\\.\\d*)?")) {
			application.NoProductEntered.display();
		}
		else if (items.size() == 0) {
			addItemToTable();
			goShoppingButton.setDisable(false);
		}
		else {
			checkForDuplicates();
		}
	}

	//REMOVE ITEM
	public void removeItemFromCart(ActionEvent deleteItem) {
		Item selectedItem = mainTableView.getSelectionModel().getSelectedItem();
			mainTableView.getItems().remove(selectedItem);
			items.remove(selectedItem);
			if (items.size() == 0) {
				goShoppingButton.setDisable(true);
			}
		}

	//GO SHOPPING
	public void goShopping(ActionEvent goShoppingFinal) {
		getSortedItemByPriority();
		productTextBox.setDisable(true);
		priorityComboBox.setDisable(true);
		quantityComboBox.setDisable(true);
		enterItemPrice.setDisable(true);
		addItemButton.setDisable(true);
		deleteItemsButton.setDisable(true);
		mainTableView.setDisable(true);
		itemsPurchasedTable.setDisable(false);
		itemsNotPurchasedTable.setDisable(false);
		goShoppingButton.setDisable(true);
		for (int x = 0; x < items.size(); x++) {
			Double budgetDoubleShopping = (budgetToInteger() - Double.parseDouble(items.get(x).getPrice()));
			String budgetSetter = ("$" + String.format("%.2f", budgetDoubleShopping));
				if (items.get(x).getPriority().equals(1)) {
					if (budgetDoubleShopping < 0) {
						items.get(x).getNotPurchased();
						itemsNotPurchased.add(items.get(x));
					}
					else {
						items.get(x).getPurchased();
						userTotalBudgetLabel.setText(budgetSetter);
						itemsPurchased.add(items.get(x));
					}
				}
				else if (items.get(x).getPriority().equals(2)) {
					if (budgetDoubleShopping < 0) {
						items.get(x).getNotPurchased();
						itemsNotPurchased.add(items.get(x));
					}
					else {
						items.get(x).getPurchased();
						userTotalBudgetLabel.setText(budgetSetter);
						itemsPurchased.add(items.get(x));
					}
				}
				else if (items.get(x).getPriority().equals(3)){
					if (budgetDoubleShopping < 0) {
						items.get(x).getNotPurchased();
						itemsNotPurchased.add(items.get(x));
					}
					else {
						items.get(x).getPurchased();
						userTotalBudgetLabel.setText(budgetSetter);
						itemsPurchased.add(items.get(x));
					}
				}
				else {
					items.get(x).getNotPurchased();
					itemsNotPurchased.add(items.get(x));
				}
			}
		itemsNotPurchasedTable.getItems().addAll(itemsNotPurchased);
		itemsPurchasedTable.getItems().addAll(itemsPurchased);
		}
	
	//ADD ITEM TO MAIN TABLE
	public void addItemToTable() {
		Item item = new Item(getProductName(), priorityToInteger(), quantityToInteger(), getProductPrice(), getPurchasedItem(), getNotPurchasedItem());
		items.add(item);
		mainTableView.getItems().add(item);
		productTextBox.setText("");
		priorityComboBox.setValue("3");
		quantityComboBox.setValue("1");
		enterItemPrice.setText("");
		}
	
	//CHECKING FOR DUPLICATES IN MAIN TABLE
	public void checkForDuplicates() {
		boolean foundIt = false;
		int i;
		int j = 0;
		
		firstLabel:
			for (i = 0; i < items.size(); i++) {
				for (j = 0; j < items.size(); j++) {
					if (items.get(i).getProduct().equalsIgnoreCase(productTextBox.getText())) {
						foundIt = true;
						break firstLabel;
					}
				}
			}
			if (foundIt) {
				application.ItemAlreadyInList.display();
			}
			else {
				addItemToTable();
			}
	}
	
	//MAKES OBJECTS VISABLE
	public void setObjectsVisable() {
		productTextBox.setDisable(false);
		priorityComboBox.setDisable(false);
		quantityComboBox.setDisable(false);
		enterItemPrice.setDisable(false);
		addItemButton.setDisable(false);
		deleteItemsButton.setDisable(false);
		goShoppingButton.setDisable(false);
		mainTableView.setDisable(false);
		budgetTotalTextField.setDisable(true);
		totalBudgetButton.setDisable(true);
		}
	
	//GETS RID OF INITIAL $
	public StringBuilder budgetToString() {
		StringBuilder budgetString = new StringBuilder(userTotalBudgetLabel.getText().toString());
		budgetString.deleteCharAt(0);
		return budgetString;
	}
	
	//CONVERT BUDGET FROM STRING TO AN INTEGER
	public Double budgetToInteger() {
		Double budgetInteger = Double.parseDouble(budgetToString().toString());
		return budgetInteger;
	}
	
	//PRIORITY COMBOBOX TO INTEGER
	public Integer priorityToInteger() {
		return Integer.parseInt(priorityComboBox.getValue());
		
	}
	
	//QUANTITY COMBOBOX TO INTEGER
	public Integer quantityToInteger() {
		return Integer.parseInt(quantityComboBox.getValue());
	}
	
	
	//PRODUCT NAME FROM PRODUCT TEXT BOX
	public String getProductName() {
		return productTextBox.getText().substring(0,1).toUpperCase() + productTextBox.getText().substring(1);
	}
	
	public StringBuilder priceRemoveSign() {
		StringBuilder priceString = new StringBuilder(enterItemPrice.getText().toString());
		priceString.deleteCharAt(0);
		return priceString;
	}
	
	//PRODUCT PRICE FROM PRICE TEXT BOX
	public Double priceToInteger() {
		return Double.parseDouble(enterItemPrice.getText());
	}
	
	//MULTIPLY ITEM PRICE AND ITEM QUANTITY AND CONVERT TO STRING
	public String getProductPrice() {
		return String.format("%.2f", (quantityToInteger() * priceToInteger()));
	}
	
	//GET PRODUCT NAME TO PUT INTO PURCHASED ITEM ARRAY
	private String getPurchasedItem() {
		return getProductName();
	}
	
	//GET PRODUCT NAME TO PUT INTO NOT PURCHASED ITEM ARRAY
	private String getNotPurchasedItem() {
		return getProductName();
	}

	//SORTS ITEM ARRAY BY PRIORITY
	public ArrayList<Item> getSortedItemByPriority() {
		Collections.sort(items);
		return items;
	}
	
	//FXML----------------------------------------------------------------------------------------------------
	@FXML
	public TableView<Item> mainTableView;
	@FXML
	public TableView<Item> itemsPurchasedTable;
	@FXML
	public TableView<Item> itemsNotPurchasedTable;
	@FXML 
	public TableColumn<Item, String> productNameTableCol;
	@FXML
	public TableColumn<Item, Integer> priorityTableCol;
	@FXML 
	public TableColumn<Item, Integer> quantityTableCol;
	@FXML
	public TableColumn<Item, Integer> priceTableCol;
	@FXML
	public TableColumn<Item, String> itemsNotPurchasedCol;
	@FXML
	public TableColumn<Item, String> itemsPurchasedCol;
	@FXML
	public BorderPane mainBorderPane;
	@FXML
	public GridPane mainGridPane;
	@FXML
	public HBox bottomHBox;
	@FXML
	public Label shoppingCartMainLabel, priceMainLabel, quantityMainLabel, itemPriceLabel, priorityMainLabel, productMainLabel, userTotalBudgetLabel;
	@FXML
	public Button totalBudgetButton, addItemButton, deleteItemsButton, goShoppingButton;
	@FXML
	public TextField productTextBox, budgetTotalTextField, enterItemPrice;
	@FXML
	public ComboBox<String> priorityComboBox, quantityComboBox;
	//----------------------------------------------------------------------------------------------------------
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		totalBudgetButton.setStyle("-fx-text-fill: green");
		addItemButton.setStyle("-fx-text-fill: green");
		deleteItemsButton.setStyle("-fx-text-fill: red");
		productTextBox.setDisable(true);
		priorityComboBox.setDisable(true);
		quantityComboBox.setDisable(true);
		enterItemPrice.setDisable(true);
		addItemButton.setDisable(true);
		deleteItemsButton.setDisable(true);
		goShoppingButton.setDisable(true);
		mainTableView.setDisable(true);
		itemsNotPurchasedTable.setDisable(true);
		itemsPurchasedTable.setDisable(true);
		
		productNameTableCol.setCellValueFactory(new PropertyValueFactory<Item, String>("product"));
		priorityTableCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("priority"));
		quantityTableCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
		priceTableCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("price"));
		itemsPurchasedCol.setCellValueFactory(new PropertyValueFactory<Item, String>("purchased"));
		itemsNotPurchasedCol.setCellValueFactory(new PropertyValueFactory<Item, String>("notPurchased"));
	}
	
}
