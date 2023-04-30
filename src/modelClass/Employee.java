 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Istiaqs-PC
 */
public class Employee extends User {

    private static final String FILENAME = "employees.bin";

    private String type;
    private String name;
    private String email;
    private String contact;
    private LocalDate DoB;
    private String salary;

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
        updateEmployee();        
    }

    // Add any additional relevant information as needed
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        updateEmployee();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
        updateEmployee();
    }

    public LocalDate getDoB() {
        return DoB;
    }

    public void setDoB(LocalDate DoB) {
        this.DoB = DoB;
        updateEmployee();
    }

    public void setType(String type) {
        this.type = type;
        updateEmployee();
    }

    public void setName(String name) {
        this.name = name;
        updateEmployee();
    }

    public Employee(String id, String password, String type, String name, String salary) {
        super(id, password);
        this.type = type;
        this.name = name;
        this.salary = salary;
        saveEmployee();
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    private void saveEmployee() {
        List<Employee> employeeList = Employee.loadEmployee();
        // Check if the employee ID of the current employee already exists in the list
        boolean exists = false;
        for (Employee employee : employeeList) {
            if (employee.getId().equals(this.getId())) {
                exists = true;
                break;
            }
        }
        // If the employee ID already exists, show an error message and do not save the employee
        if (exists) {
            System.out.println("Employee already exists");
        } else {
            // Otherwise, add the employee to the list and save the list to the file
            employeeList.add(this);
            try (FileOutputStream fileOut = new FileOutputStream("employees.bin", false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(employeeList);
                System.out.println("Employee saved to employees.bin file");
            } catch (IOException e) {
                System.out.println("Error saving reading to file");
            }
        }
    }

    private static List<Employee> loadEmployee() {
        List<Employee> employees = new ArrayList<>();
        try {
            try ( // Read the list of employees from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("employees.bin"))) {
                employees = (List<Employee>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        return employees;
    }

    private void updateEmployee() {
        List<Employee> employeeList = Employee.loadEmployee();
        boolean updated = false;

        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId().equals(this.getId())) {
                employeeList.set(i, this);
                updated = true;
                break;
            }
        }

        if (!updated) {
            System.out.println("Employee not found");
        } else {
            try (FileOutputStream fileOut = new FileOutputStream("employees.bin", false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(employeeList);
                System.out.println("Employee updated and saved to employees.bin file");
            } catch (IOException e) {
                System.out.println("Error updating employee");
            }
        }
    }
}
