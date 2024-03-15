package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FrontPageController {

  // FXML annotations to bind the UI components
  @FXML
  private Button handlelisteButton;

  @FXML
  private Button kjøleskapButton;

  @FXML
  private Button oppskrifterButton;

  // Event handlers for the buttons
  @FXML
  private void handleHandlelisteButtonClick() {
    System.out.println("Handleliste button clicked!");
  }

  @FXML
  private void handleKjøleskapButtonClick() {
    System.out.println("Kjøleskap button clicked!");
  }

  @FXML
  private void handleOppskrifterButtonClick() {
    System.out.println("Oppskrifter button clicked!");
  }
}
