package org.example.womenshopfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import java.io.IOException;

public class AddProductController {

    @FXML
    private TextField clothingNameField, clothingPurchasePriceField, clothingSellPriceField, clothingDiscountField, clothingSizeField;
    @FXML
    private TextField accessoriesNameField, accessoriesPurchasePriceField, accessoriesSellPriceField, accessoriesDiscountField;
    @FXML
    private TextField footwearNameField, footwearPurchasePriceField, footwearSellPriceField, footwearDiscountField;

    @FXML
    private Button cancelButton;

    // TabPane to manage multiple product categories
    @FXML
    private TabPane productTabPane;

    // Save clothing product
    @FXML
    private void saveClothing(ActionEvent event) {
        String name = clothingNameField.getText();
        double purchasePrice = Double.parseDouble(clothingPurchasePriceField.getText());
        double sellPrice = Double.parseDouble(clothingSellPriceField.getText());
        double discount = Double.parseDouble(clothingDiscountField.getText());
        String size = clothingSizeField.getText();

        // Code to save clothing product to the database
        System.out.println("Clothing product saved: " + name);
    }

    // Save accessories product
    @FXML
    private void saveAccessories(ActionEvent event) {
        String name = accessoriesNameField.getText();
        double purchasePrice = Double.parseDouble(accessoriesPurchasePriceField.getText());
        double sellPrice = Double.parseDouble(accessoriesSellPriceField.getText());
        double discount = Double.parseDouble(accessoriesDiscountField.getText());

        // Code to save accessories product to the database
        System.out.println("Accessories product saved: " + name);
    }

    // Save footwear product
    @FXML
    private void saveFootwear(ActionEvent event) {
        String name = footwearNameField.getText();
        double purchasePrice = Double.parseDouble(footwearPurchasePriceField.getText());
        double sellPrice = Double.parseDouble(footwearSellPriceField.getText());
        double discount = Double.parseDouble(footwearDiscountField.getText());

        // Code to save footwear product to the database
        System.out.println("Footwear product saved: " + name);
    }

    // Cancel and close the add product scene
    @FXML
    private void cancelAddProduct(ActionEvent event) throws IOException {
        // Load the ProductCatalog.fxml
        Parent productCatalogParent = FXMLLoader.load(getClass().getResource("ProductCatalog.fxml"));
        Scene productCatalogScene = new Scene(productCatalogParent);

        // Get the current stage (window) from the event source
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene to Product Catalog
        window.setScene(productCatalogScene);
        window.show();
    }
}
