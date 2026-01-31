package controller.order;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import model.dto.OrderDTO;

import java.sql.*;
import java.time.LocalDate;

public class OrderInfoController implements OrderService{
    @Override
    public ObservableList<OrderDTO> getAllOrders() {
        ObservableList<OrderDTO>orderDTOS= FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Orders");
            while (resultSet.next()){
                OrderDTO orderDTO=new OrderDTO(
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

    @Override
    public void updateOrder(String orderId, LocalDate dpOrderDate, String customerId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update Orders set OrderDate=? ,CustID=? where OrderID=?");
            preparedStatement.setObject(1,dpOrderDate);
            preparedStatement.setObject(2,customerId);
            preparedStatement.setObject(3,orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrder(String orderId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from item where OrderID=?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
