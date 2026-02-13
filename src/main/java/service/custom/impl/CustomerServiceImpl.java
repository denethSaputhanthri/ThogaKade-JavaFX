package service.custom.impl;

import model.entity.Customer;
import repository.RepositoryFactory;
import repository.custom.CustomerRepository;
import service.custom.CustomerService;
import util.RepositoryType;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    CustomerRepository repository=RepositoryFactory.getInstance().getRepositoryType(RepositoryType.CUSTOMER);

    @Override
    public List<Customer> getAll() {
        return repository.getAll();
    }

    @Override
    public boolean addCustomer(Customer customer) {
        return repository.create(customer);
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return repository.update(customer);
    }

    @Override
    public boolean deleteCustomer(String id) {
        return repository.deleteById(id);
    }

    @Override
    public boolean searchById(String id) {
        return false;
    }
}
