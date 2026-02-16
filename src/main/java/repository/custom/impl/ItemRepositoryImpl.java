package repository.custom.impl;

import db.DBConnection;
import model.dto.Item;
import repository.custom.ItemRepository;
import util.CrudUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {

    @Override
    public List<Item> getAll() {
        try {
            ResultSet resultSet = CrudUtil.execute("select * from item");

            ArrayList<Item> itemArrayList = new ArrayList<>();

            while (resultSet.next()) {
                itemArrayList.add(new Item(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand")
                        ));
            }
            return itemArrayList;
    } catch(
    SQLException e)

    {
        throw new RuntimeException(e);
    }
}


    @Override
    public Boolean create(Item item) {
        try {
            return CrudUtil.execute("insert into item values(?,?,?,?,?)",
                    item.getItemCode(),
                    item.getDescription(),
                    item.getPackSize(),
                    item.getUnitPrice(),
                    item.getQtyOnHand()
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean update(Item item) {
        try {
            return CrudUtil.execute("update item set Description=?,PackSize=?,UnitPrice=?,QtyOnHand=? where ItemCode=?",
                    item.getDescription(),
                    item.getPackSize(),
                    item.getUnitPrice(),
                    item.getQtyOnHand(),
                    item.getItemCode()
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean deleteById(String itemCode) {
        try {
            return CrudUtil.execute("delete from item where ItemCode=?", itemCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item searchById(String itemCode) {
        return null;
    }
}
