package repository.custom;

import model.dto.Order;
import repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,String> {
}
