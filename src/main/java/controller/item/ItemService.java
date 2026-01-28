package controller.item;

import javafx.collections.ObservableList;
import model.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    public ObservableList<ItemDTO>getAll();
}
