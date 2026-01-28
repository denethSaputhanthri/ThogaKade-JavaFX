package controller.item;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.ItemDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
