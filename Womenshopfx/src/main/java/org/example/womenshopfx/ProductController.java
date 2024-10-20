package org.example.womenshopfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductController {

    @FXML
    private TableView<Product> productTable;
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
    private Button buyButton; // Buy button
    @FXML
    private Button modifyButton; // Modify button
    @FXML
    private Label capitalAmountLabel;

    private double capital = 1000.00; // Example starting capital

    // Handle product type change
    @FXML
    private void handleProductTypeChange(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        String productType = menuItem.getText();
        updateProductCatalog(productType);
    }

    // Update product catalog based on type
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
        }
    }

    // Load data methods for each category
    private void loadClothingData() { loadData("SELECT * FROM clothing"); }
    private void loadFootwearData() { loadData("SELECT * FROM footwear"); }
    private void loadAccessoriesData() { loadData("SELECT * FROM accessories"); }

    // General method to load data from the database
    private void loadData(String query) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            productTable.getItems().clear();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double purchasePrice = resultSet.getDouble("purchase_price");
                double sellPrice = resultSet.getDouble("sell_price");
                double discount = resultSet.getDouble("discount");
                String size = resultSet.getString("size");

                Product product = new Product(id, name, purchasePrice, sellPrice, discount, size);
                productTable.getItems().add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

        capitalAmountLabel.setText(String.format("%.2f", capital));
    }

    // Handle Add Product button and switch scene
    @FXML
    private void handleAddProduct(ActionEvent event) throws IOException {
        Parent addProductParent = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }

    // Handle Buy Product button
    @FXML
    private void handleBuyProduct(ActionEvent event) {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            double purchasePrice = selectedProduct.getPurchasePrice();
            if (capital >= purchasePrice) {
                capital -= purchasePrice;
                capitalAmountLabel.setText(String.format("%.2f", capital));
            } else {
                System.out.println("Not enough capital to buy the product.");
            }
        } else {
            System.out.println("No product selected.");
        }
    }

    // Open Modify Product scene
    @FXML
    private void openModifyScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyProduct.fxml"));
            Parent modifyProductScene = loader.load();

            ModifyProductController modifyController = loader.getController();
            modifyController.loadExistingProducts(); // Load data to modify

            Stage stage = new Stage();
            stage.setScene(new Scene(modifyProductScene));
            stage.setTitle("Modify Products");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
