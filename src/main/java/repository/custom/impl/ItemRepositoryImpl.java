package repository.custom.impl;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Item;
import repository.custom.ItemRepository;

import java.sql.*;
import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {

    @Override
    public List<Item> getAll() {
        ObservableList<Item> itemDTOS= FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from item");
            while (resultSet.next()){
                Item itemDTO=new Item(
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
    public Boolean create(Item item) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into item values(?,?,?,?,?)" );
            preparedStatement.setObject(1,item.getItemCode());
            preparedStatement.setObject(2,item.getDescription());
            preparedStatement.setObject(3,item.getPackSize());
            preparedStatement.setObject(4,item.getUnitPrice());
            preparedStatement.setObject(5,item.getQtyOnHand());
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean update(Item item) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update item set Description=?,PackSize=?,UnitPrice=?,QtyOnHand=? where ItemCode=?");
            preparedStatement.setObject(1,item.getDescription());
            preparedStatement.setObject(2,item.getPackSize());
            preparedStatement.setObject(3,item.getUnitPrice());
            preparedStatement.setObject(4,item.getQtyOnHand());
            preparedStatement.setObject(5,item.getItemCode());
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean deleteById(String itemCode) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from item where ItemCode=?");
            preparedStatement.setObject(1,itemCode);
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item searchById(String itemCode) {
        return null;
    }
}
