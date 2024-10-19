package org.example.womenshopfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private Button logoutButton;

    @FXML
    public void initialize() {
        logoutButton.setOnAction(event -> {
            // Close the current window on logout
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.close();
        });
    }
}
