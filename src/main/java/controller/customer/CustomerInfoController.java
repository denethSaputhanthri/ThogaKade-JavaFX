package controller.customer;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.CustomerDTO;

import java.sql.*;
import java.time.LocalDate;

public class CustomerInfoController implements CustomerService {
    @Override
    public ObservableList<CustomerDTO> getAll() {
    ObservableList<CustomerDTO>customerDTOS= FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from customer");
            while (resultSet.next()){
                CustomerDTO customerDTO=new CustomerDTO(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustTitle"),
                        resultSet.getString("CustName"),
                        resultSet.getDate("DOB").toLocalDate(),
                        resultSet.getDouble("salary"),
                        resultSet.getString("CustAddress"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                );
                customerDTOS.add(customerDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerDTOS;
    }

    @Override
    public void addCustomer(String id, String customerTitle, String customerName, LocalDate dateOfBirth, Double salary, String address, String city, String province, String postalCode) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into customer values (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setObject(1,id);
            preparedStatement.setObject(2,customerTitle);
            preparedStatement.setObject(3,customerName);
            preparedStatement.setObject(4,dateOfBirth);
            preparedStatement.setObject(5,salary);
            preparedStatement.setObject(6,address);
            preparedStatement.setObject(7,city);
            preparedStatement.setObject(8,province);
            preparedStatement.setObject(9,postalCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCustomer(String id, String customerTitle, String customerName, LocalDate dateOfBirth, Double salary, String address, String city, String province, String postalCode) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customer SET CustTitle=?, CustName=?, DOB=?, salary=?, CustAddress=?, City=?, Province=?, PostalCode=? WHERE CustID=? ");
            preparedStatement.setObject(1,customerTitle);
            preparedStatement.setObject(2,customerName);
            preparedStatement.setObject(3,dateOfBirth);
            preparedStatement.setObject(4,salary);
            preparedStatement.setObject(5,address);
            preparedStatement.setObject(6,city);
            preparedStatement.setObject(7,province);
            preparedStatement.setObject(8,postalCode);
            preparedStatement.setObject(9,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
