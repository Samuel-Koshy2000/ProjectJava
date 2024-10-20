package org.example.womenshopfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductController {

    @FXML
    private TableView<Product> productTable; // Assuming you have a Product class
    @FXML
    private TableColumn<Product, Integer> idColumn; // ID Column
    @FXML
    private TableColumn<Product, String> nameColumn; // Name Column
    @FXML
    private TableColumn<Product, Double> purchasePriceColumn; // Purchase Price Column
    @FXML
    private TableColumn<Product, Double> sellPriceColumn; // Sell Price Column
    @FXML
    private TableColumn<Product, Double> discountColumn; // Discount Column
    @FXML
    private TableColumn<Product, String> sizeColumn; // Size Column

    // Method to handle the product type change
    @FXML
    private void handleProductTypeChange(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        String productType = menuItem.getText();

        // Call a method to update the product catalog based on the selected type
        updateProductCatalog(productType);
    }

    // Method to update the product catalog based on product type
    private void updateProductCatalog(String productType) {
        switch (productType.toLowerCase()) {
            case "clothing":
                loadClothingData();
                break;
            case "footwear":
                loadFootwearData();
                break;
            case "accessories":
                loadAccessoriesData();
                break;
            default:
                // Optionally handle an unsupported product type
                break;
        }
    }

    // Method to load clothing data from the database
    private void loadClothingData() {
        String query = "SELECT * FROM clothing"; // Query for clothing table
        loadData(query);
    }

    // Method to load footwear data from the database
    private void loadFootwearData() {
        String query = "SELECT * FROM footwear"; // Query for footwear table
        loadData(query);
    }

    // Method to load accessories data from the database
    private void loadAccessoriesData() {
        String query = "SELECT * FROM accessories"; // Query for accessories table
        loadData(query);
    }

    // General method to load data from the database
    private void loadData(String query) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            productTable.getItems().clear(); // Clear existing items

            while (resultSet.next()) {
                int id = resultSet.getInt("id"); // Replace with actual column name
                String name = resultSet.getString("name"); // Replace with actual column name
                double purchasePrice = resultSet.getDouble("purchase_price"); // Replace with actual column
                double sellPrice = resultSet.getDouble("sell_price"); // Replace with actual column
                double discount = resultSet.getDouble("discount"); // Replace with actual column
                String size = resultSet.getString("size"); // Replace with actual column

                // Create a new Product object (assuming you have a Product class with the appropriate constructor)
                Product product = new Product(id, name, purchasePrice, sellPrice, discount, size);

                // Add the product to the table
                productTable.getItems().add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace for any SQL exceptions
        }
    }

    // Initialize method to set up the table columns
    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        purchasePriceColumn.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        sellPriceColumn.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
    }
}
