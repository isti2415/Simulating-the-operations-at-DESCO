/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desco;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Istiaqs-PC
 */
public class customerController implements Initializable {

    @FXML
    private Pane pane2;
    @FXML
    private ComboBox<?> billMonthComboBox;
    @FXML
    private ComboBox<?> billYearComboBox;
    @FXML
    private CheckBox autoPaymentCheckbox;
    @FXML
    private CheckBox paperlessbillCheckBox;
    @FXML
    private TextArea billTextField;
    @FXML
    private Pane pane1;
    @FXML
    private TextField profileNameTextField;
    @FXML
    private TextField profileUsernameTextField;
    @FXML
    private DatePicker profileDOBdatepicker;
    @FXML
    private TextField currPassTextField;
    @FXML
    private TextField profileEmailTextField;
    @FXML
    private TextField profileConNumTextField;
    @FXML
    private TextField newPassTextField;
    @FXML
    private Pane pane3;
    @FXML
    private ComboBox<?> energyUseMonthCombobox;
    @FXML
    private ComboBox<?> energyUseYearCombobox;
    @FXML
    private TextArea viewTextArea;
    @FXML
    private Pane pane4;
    @FXML
    private ComboBox<?> serviceTypeComboBox;
    @FXML
    private TextField detailsTextField;
    @FXML
    private Pane pane5;
    @FXML
    private TableView<?> notificationsTableViewOnClick;
    @FXML
    private TableColumn<?, ?> dateCol;
    @FXML
    private TableColumn<?, ?> subjectCol;
    @FXML
    private TableColumn<?, ?> detailsCol;
    @FXML
    private Pane pane6;
    @FXML
    private DatePicker complaintDatePicker;
    @FXML
    private TextArea complaintTextArea;

    private void switchPane(int paneNumber) {
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);
        pane6.setVisible(false);

        switch (paneNumber) {
            case 1:
                pane1.setVisible(true);
                break;
            case 2:
                pane2.setVisible(true);
                break;
            case 3:
                pane3.setVisible(true);
                break;
            case 4:
                pane4.setVisible(true);
                break;
            case 5:
                pane5.setVisible(true);
                break;
            case 6:
                pane6.setVisible(true);
                break;
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        switchPane(1);
    }

    @FXML
    private void viewProfileOnClick(ActionEvent event) {
        switchPane(1);
    }

    @FXML
    private void viewMyBillsOnClick(ActionEvent event) {
        switchPane(2);
    }

    @FXML
    private void viewEnergyUsageOnClick(ActionEvent event) {
        switchPane(3);
    }

    @FXML
    private void viewServiceRequestOnClick(ActionEvent event) {
        switchPane(4);
    }

    @FXML
    private void viewNotificationsOnClick(ActionEvent event) {
        switchPane(5);
    }

    @FXML
    private void fileComplaintOnClick(ActionEvent event) {
        switchPane(6);
    }

    @FXML
    private void logOutOnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/desco/login.fxml"));
            Parent root = loader.load();
            desco.LoginController loginController = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }

    @FXML
    private void viewBillOnClick(ActionEvent event) {
    }

    @FXML
    private void makePaymentOnClick(ActionEvent event) {
    }

    @FXML
    private void saveChangesOnClick(ActionEvent event) {
    }

    @FXML
    private void viewMontlyUsageOnClick(ActionEvent event) {
    }

    @FXML
    private void ViewYearlyUsageOnClick(ActionEvent event) {
    }

    @FXML
    private void submitOnClick(ActionEvent event) {
    }

    @FXML
    private void submitComplaintButton(ActionEvent event) {
    }

}
