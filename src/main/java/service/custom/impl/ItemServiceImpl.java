package service.custom.impl;

import model.dto.Item;
import repository.RepositoryFactory;
import repository.custom.ItemRepository;
import service.custom.ItemService;
import util.RepositoryType;

import java.util.List;

public class ItemServiceImpl implements ItemService {
    ItemRepository repository=RepositoryFactory.getInstance().getRepositoryType(RepositoryType.ITEM);

    @Override
    public List<Item> getAll() {
        return repository.getAll();
    }

    @Override
    public Boolean addItem(Item item) {
        return repository.create(item);
    }

    @Override
    public Boolean updateItem(Item item) {
        return repository.update(item);
    }

    @Override
    public Boolean deleteItem(String itemCode) {
        return repository.deleteById(itemCode);
    }

    @Override
    public Boolean searchById(String itemCode) {
        return searchById(itemCode);
    }
}
