package org.example.womenshopfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ProductController {

    @FXML
    private Label capitalLabel;

    @FXML
    private Label capitalAmountLabel;

    @FXML
    private MenuButton productTypeMenu;

    @FXML
    private TableView<Product> productTable; // Assuming you have a Product class

    @FXML
    private TableColumn<Product, Integer> idColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, Double> purchasePriceColumn;

    @FXML
    private TableColumn<Product, Double> sellPriceColumn;

    @FXML
    private TableColumn<Product, Double> discountColumn;

    @FXML
    private TableColumn<Product, String> sizeColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button modifyButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button sellButton;

    @FXML
    public void initialize() {
        // Initialize your table and other UI components here
        // For example, set up the table columns
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        purchasePriceColumn.setCellValueFactory(cellData -> cellData.getValue().purchasePriceProperty().asObject());
        sellPriceColumn.setCellValueFactory(cellData -> cellData.getValue().sellPriceProperty().asObject());
        discountColumn.setCellValueFactory(cellData -> cellData.getValue().discountProperty().asObject());
        sizeColumn.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());

        // Example: Update capital amount
        updateCapital(1000.00); // Set initial capital
    }

    @FXML
    private void handleAddProduct() {
        // Logic to add a new product
        System.out.println("Add product logic goes here");
    }

    @FXML
    private void handleModifyProduct() {
        // Logic to modify an existing product
        System.out.println("Modify product logic goes here");
    }

    @FXML
    private void handleDeleteProduct() {
        // Logic to delete a product
        System.out.println("Delete product logic goes here");
    }

    @FXML
    private void handleSellProduct() {
        // Logic to sell a product
        System.out.println("Sell product logic goes here");
    }

    private void updateCapital(double amount) {
        capitalAmountLabel.setText(String.format("%.2f", amount));
    }
}
