package service.custom.impl;

import model.entity.Customer;
import repository.RepositoryFactory;
import repository.custom.CustomerRepository;
import service.custom.CustomerService;
import util.RepositoryType;

import java.util.ArrayList;
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
    public Customer searchById(String id) {
        return repository.searchById(id);
    }

    @Override
    public List<String> getAllCustomerIDs() {
        List<Customer> all = getAll();
        ArrayList<String>idList=new ArrayList<>();
        all.forEach(customer -> {
            idList.add(customer.getCustomerId());
        });
        return idList;
    }
}
