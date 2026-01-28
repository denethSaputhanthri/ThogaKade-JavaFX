package controller.item;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.ItemDTO;

import java.sql.*;

public class ItemInfoController implements ItemService{
    @Override
    public ObservableList<ItemDTO> getAll() {
        ObservableList<ItemDTO>itemDTOS= FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from item");
            while (resultSet.next()){
                ItemDTO itemDTO=new ItemDTO(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand")
                );
                itemDTOS.add(itemDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemDTOS;
    }

    @Override
    public void addItem(String itemCode, String description, String packSize, Double unitPrice, Integer qty) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into item values(?,?,?,?,?)" );
            preparedStatement.setObject(1,itemCode);
            preparedStatement.setObject(2,description);
            preparedStatement.setObject(3,packSize);
            preparedStatement.setObject(4,unitPrice);
            preparedStatement.setObject(5,qty);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateItem(String itemCode, String description, String packSize, Double unitPrice, Integer qty) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update item set Description=?,PackSize=?,UnitPrice=?,QtyOnHand=? where ItemCode=?");
            preparedStatement.setObject(1,description);
            preparedStatement.setObject(2,packSize);
            preparedStatement.setObject(3,unitPrice);
            preparedStatement.setObject(4,qty);
            preparedStatement.setObject(5,itemCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
