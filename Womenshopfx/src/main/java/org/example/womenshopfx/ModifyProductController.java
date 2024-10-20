package org.example.womenshopfx;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModifyProductController {

    @FXML
    private TableView<Product> modifyProductTable;
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
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        purchasePriceColumn.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        sellPriceColumn.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
    }

    public void loadExistingProducts() {
        // Load data from all product tables
        loadProductData("SELECT * FROM clothing");
        loadProductData("SELECT * FROM accessories");
        loadProductData("SELECT * FROM footwear");
    }

    private void loadProductData(String query) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            modifyProductTable.getItems().clear();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double purchasePrice = resultSet.getDouble("purchase_price");
                double sellPrice = resultSet.getDouble("sell_price");
                double discount = resultSet.getDouble("discount");
                String size = resultSet.getString("size");

                Product product = new Product(id, name, purchasePrice, sellPrice, discount, size);
                modifyProductTable.getItems().add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to handle save after modification
    @FXML
    private void handleSaveChanges(ActionEvent event) {
        for (Product product : modifyProductTable.getItems()) {
            // Update the modified products in the database
            updateProductInDatabase(product);
        }
    }

    private void updateProductInDatabase(Product product) {
        String updateQuery = "UPDATE " + getTableName(product) + " SET name = ?, purchase_price = ?, sell_price = ?, discount = ?, size = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPurchasePrice());
            preparedStatement.setDouble(3, product.getSellPrice());
            preparedStatement.setDouble(4, product.getDiscount());
            preparedStatement.setString(5, product.getSize());
            preparedStatement.setInt(6, product.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getTableName(Product product) {
        // Determine the table name based on product attributes, for simplicity, you can use a property
        // Here, assume you have a way to determine which table to update based on product's data.
        if (product.getSize() != null) { // Assuming size is a property exclusive to clothing
            return "clothing";
        } else if (product.getName().toLowerCase().contains("footwear")) { // Example condition
            return "footwear";
        } else {
            return "accessories";
        }
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        // Close the current window (modify scene)
        Stage stage = (Stage) modifyProductTable.getScene().getWindow();
        stage.close();
    }
}
