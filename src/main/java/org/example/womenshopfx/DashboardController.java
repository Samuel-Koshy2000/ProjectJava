package org.example.womenshopfx;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node; // Add this import
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import java.io.IOException;

public class DashboardController {

    @FXML
    private void handleGoToProductCatalog(javafx.event.ActionEvent event) { // Add the ActionEvent parameter
        try {
            // Load the Product Catalog FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("productCatalog.fxml"));
            Parent productCatalogView = loader.load();

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Use the Node import here
            Scene scene = new Scene(productCatalogView);
            stage.setScene(scene);
            stage.setTitle("Product Catalog");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
