import model.Orders;
import model.Customer;
import model.OrderLine;
import model.Product;


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