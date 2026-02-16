package repository.custom.impl;


import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entity.Customer;
import repository.custom.CustomerRepository;
import util.CrudUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public Boolean create(Customer customer) {
        try {
            return CrudUtil.execute("INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?)",
                    customer.getCustomerId(),
                    customer.getCustomerTitle(),
                    customer.getCustomerName(),
                    customer.getDob(),
                    customer.getSalary(),
                    customer.getAddress(),
                    customer.getCity(),
                    customer.getProvince(),
                    customer.getPostalCode()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean update(Customer customer) {
        try {
            return CrudUtil.execute("UPDATE customer SET CustTitle=?, CustName=?, DOB=?, salary=?, CustAddress=?, City=?, Province=?, PostalCode=? WHERE CustID=? ",
                    customer.getCustomerTitle(),
                    customer.getCustomerName(),
                    customer.getDob(),
                    customer.getSalary(),
                    customer.getAddress(),
                    customer.getCity(),
                    customer.getProvince(),
                    customer.getPostalCode(),
                    customer.getCustomerId()
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean deleteById(String id) {
        try {
            return CrudUtil.execute("DELETE FROM customer WHERE CustID=?", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer searchById(String s) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        ObservableList<Customer> customerDTOS= FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer");

            ArrayList<Customer> customerArrayList = new ArrayList<>();

            while (resultSet.next()){
                customerArrayList.add(
                        new Customer(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getDate(4).toLocalDate(),
                                resultSet.getDouble(5),
                                resultSet.getString(6),
                                resultSet.getString(7),
                                resultSet.getString(8),
                                resultSet.getString(9)
                        )
                );
            }

            return customerArrayList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
