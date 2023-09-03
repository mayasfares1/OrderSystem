package dao;

import model.Orders;
import model.Customer;
import model.OrderLine;
import model.Product;

import java.util.List;


public interface IOrderDAO
{
    // VI
    Orders saveOrder(Orders order, Customer customer);

    // VII
    OrderLine saveOrderLine(OrderLine orderLine, Product product, Orders order);

    List<Orders> allOrdersForCustomer(int customerId);

    // IX
    Double findTotalPriceOfOrder(int orderId);
}