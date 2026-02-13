package repository.custom.impl;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Order;
import repository.custom.OrderRepository;

import java.sql.*;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public Boolean create(Order order) {
        return null;
    }

    @Override
    public Boolean update(Order order) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update Orders set OrderDate=? ,CustID=? where OrderID=?");
            preparedStatement.setObject(1,order.getOrderDate());
            preparedStatement.setObject(2,order.getCustomerId());
            preparedStatement.setObject(3,order.getOrderId());
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean deleteById(String orderId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from Orders where OrderID=?");
            preparedStatement.setObject(1,orderId);
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order searchById(String s) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        ObservableList<Order> orderDTOS= FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Orders");
            while (resultSet.next()){
                Order orderDTO=new Order(
                        resultSet.getString("OrderID"),
                        resultSet.getDate("OrderDate").toLocalDate(),
                        resultSet.getString("CustID")
                );
                orderDTOS.add(orderDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderDTOS;
    }
}
