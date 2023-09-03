package dao;

import model.Customer;

import java.util.List;

public interface ICreateDAO
{
    // I
    Customer saveCustomer(Customer customer);

    // II
    Customer findCustomer(int id);

    // III
    List<Customer> getAllCustomer();
}