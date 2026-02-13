package service.custom;

import model.entity.Customer;
import service.SuperService;

import java.util.List;


public interface CustomerService extends SuperService {
    List<Customer> getAll();
    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(String id);
    boolean searchById(String id);
}
