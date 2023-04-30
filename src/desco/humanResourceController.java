/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desco;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import modelClass.CurrUserID;
import modelClass.Employee;
import modelClass.User;

/**
 * FXML Controller class
 *
 * @author Istiaqs-PC
 */
public class humanResourceController implements Initializable {

    @FXML
    private Pane pane2;
    @FXML
    private TableView<Employee> employeeInfoTable;
    @FXML
    private Pane pane3;
    @FXML
    private TableView<?> attendanceTable;
    @FXML
    private Pane pane4;
    @FXML
    private TableView<?> payrollTable;
    @FXML
    private TableColumn<?, ?> amountColumn3;
    @FXML
    private Pane pane5;
    @FXML
    private TableView<?> performanceTable;
    private ComboBox<String> deptComboBox4;
    @FXML
    private Pane pane7;
    @FXML
    private ComboBox<String> deptComboBox6;
    @FXML
    private TextField idTextField6;
    @FXML
    private TextField nameTextField6;
    @FXML
    private Pane pane8;
    @FXML
    private TableView<?> employeeOffboardTable;
    @FXML
    private TableColumn<?, ?> idColumn7;
    @FXML
    private TableColumn<?, ?> nameColumn7;
    @FXML
    private TableColumn<?, ?> departmentColumn7;
    @FXML
    private TableColumn<?, ?> positionColumn7;
    @FXML
    private Pane pane1;
    @FXML
    private TextField profileNameTextField;
    @FXML
    private TextField profileUserIDTextField;
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
    private Pane pane9;
    @FXML
    private TableColumn<Employee, String> IDInfoColumn;
    @FXML
    private TableColumn<Employee, String> NameIndoColumn;
    @FXML
    private TableColumn<Employee, String> DeptInfoColumn;
    @FXML
    private DatePicker dobPicker6;
    @FXML
    private TextField passwordTextField6;
    @FXML
    private TextField numberField6;
    @FXML
    private TextField emailTextField6;
    @FXML
    private TextArea policyTextArea;

    ObservableList<String> departments = FXCollections.observableArrayList(
            "Meter Reader", "Billing Administrator", "Customer Service Represantative",
            "Human Resources", "Manager", "Technician", "System Administrator"
    );
    @FXML
    private TableColumn<?, ?> idColumn3;
    @FXML
    private TableColumn<?, ?> deptColumn3;
    @FXML
    private TableColumn<Employee, String> ContactInfoColumn;
    @FXML
    private TableColumn<?, ?> idAttendanceColumn;
    @FXML
    private TableColumn<?, ?> presentAttdColumn;
    @FXML
    private TableColumn<?, ?> reasonAttdColumn;
    @FXML
    private TableColumn<?, ?> performanceIDColumn;
    @FXML
    private TableColumn<?, ?> performanceTaskColumn;
    @FXML
    private TableColumn<?, ?> perfCompleteColumn;
    @FXML
    private ComboBox<?> perfDeptComboBox;
    @FXML
    private TableColumn<Employee, String> SalaryInfoColumn;
    @FXML
    private TextField salaryTxtField;

    private void switchPane(int paneNumber) {
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);
        pane7.setVisible(false);
        pane8.setVisible(false);
        pane9.setVisible(false);

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
            case 7:
                pane7.setVisible(true);
                break;
            case 8:
                pane8.setVisible(true);
                break;
            case 9:
                pane9.setVisible(true);
                break;
        }
    }

    private Employee getCurrUser() throws IOException, ClassNotFoundException {
        // Read the current user ID from the session file
        String userID = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("session.bin"));
            CurrUserID savedUser = (CurrUserID) in.readObject();
            if (savedUser != null) {
                userID = savedUser.getCurrUserID();
            }
            System.out.println(userID);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Look for a matching customer in the customers file
        Employee currUser = null;
        List<Employee> employees = new ArrayList<>();
        try {
            try ( // Read the list of customers from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("employees.bin"))) {
                employees = (List<Employee>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        for (Employee employee : employees) {
            if (employee.getId().equals(userID)) {
                currUser = employee;
                break;
            }
        }

        return currUser;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        switchPane(1);
        deptComboBox6.setItems(departments);
        Employee curr;
        try {
            curr = getCurrUser();
            if (curr != null) {
                profileNameTextField.setText(curr.getName());
                profileUserIDTextField.setText(curr.getId());
                profileDOBdatepicker.setValue(curr.getDoB());
                profileEmailTextField.setText(curr.getEmail());
                profileConNumTextField.setText(curr.getContact());
            }
        } catch (IOException ex) {
            Logger.getLogger(customerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(customerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void viewProfileOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        switchPane(1);
        Employee curr = getCurrUser();
        profileNameTextField.setText(curr.getName());
    }

    @FXML
    private void employeeInfoOnClick(ActionEvent event) {
        switchPane(2);
        employeeInfoTable.refresh();
        
        IDInfoColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameIndoColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        DeptInfoColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        SalaryInfoColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        ContactInfoColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));

        ObservableList<Employee> emps = FXCollections.observableList(new ArrayList<>());

        try {
            try ( // Read the list of users from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("employees.bin"))) {
                emps.addAll((List<Employee>) inputStream.readObject());
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions as needed
        }

        employeeInfoTable.setItems((ObservableList<Employee>) emps);
    }

    @FXML
    private void employeeAttendanceOnClick(ActionEvent event) {
        switchPane(3);
    }

    @FXML
    private void payrollOnClick(ActionEvent event) {
        switchPane(4);
        
    }

    @FXML
    private void employeePerformanceOnClick(ActionEvent event) {
        switchPane(5);
    }

    @FXML
    private void employeeOnboardOnClick(ActionEvent event) {
        switchPane(7);
    }

    @FXML
    private void employeeOffOnClick(ActionEvent event) {
        switchPane(8);
    }

    @FXML
    private void viewPolicyOnClick(ActionEvent event) {
        switchPane(9);
        try {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("companypolicy.txt"))) {
                policyTextArea.setWrapText(true);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    policyTextArea.appendText(line + "\n");
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
    }

    @FXML
    private void logOutOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        getCurrUser().logout(event);
    }



    @FXML
    private void saveS6OnClick(ActionEvent event) {
        String id = idTextField6.getText();
        String pass = passwordTextField6.getText();
        String type = deptComboBox6.getValue();
        String name = nameTextField6.getText();
        String salary = salaryTxtField.getText();
        Employee e = new Employee(id, pass, type, name, salary);
        e.setEmail(emailTextField6.getText());
        e.setContact(numberField6.getText());
        e.setDoB(dobPicker6.getValue());
    }

    @FXML
    private void offboardS7OnClick(ActionEvent event) {
    }

    @FXML
    private void saveChangesOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        Employee curr = getCurrUser();
        if (curr != null) {
            curr.setName(profileNameTextField.getText());
            curr.setDoB(profileDOBdatepicker.getValue());
            curr.setEmail(profileEmailTextField.getText());
            curr.setContact(profileConNumTextField.getText());
        }
    }


    @FXML
    private void paySalaryOnClick(ActionEvent event) {
    }

    @FXML
    private void saveAttdOnClick(ActionEvent event) {
    }

    @FXML
    private void savePerformanceOnClick(ActionEvent event) {
    }


}
