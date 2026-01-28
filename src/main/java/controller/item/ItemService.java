package controller.item;

import javafx.collections.ObservableList;
import model.dto.ItemDTO;

public interface ItemService {
    public ObservableList<ItemDTO>getAll();
    public void addItem(String itemCode, String description, String packSize, Double unitPrice, Integer qty);
}
