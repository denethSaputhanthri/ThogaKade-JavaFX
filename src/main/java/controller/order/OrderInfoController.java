package controller.order;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.OrderDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
