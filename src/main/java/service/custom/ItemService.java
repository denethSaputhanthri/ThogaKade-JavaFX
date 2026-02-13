package service.custom;

import model.dto.Item;
import service.SuperService;

import java.util.List;

public interface ItemService extends SuperService {
    List<Item> getAll();
    Boolean addItem(Item item);
    Boolean updateItem(Item item);
    Boolean deleteItem(String itemCode);
    Boolean searchById(String itemCode);
}
