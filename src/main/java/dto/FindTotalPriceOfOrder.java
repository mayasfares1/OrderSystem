package dto;

import lombok.Getter;
import model.OrderLine;
import model.Orders;

@Getter
public class FindTotalPriceOfOrder
{
    private Integer orderID;
    private Double price;

    public FindTotalPriceOfOrder(Orders order)
    {
        this.orderID = order.getId();

        this.price = 0.0;

        for (OrderLine o : order.getOrderLine())
        {
            price += o.getProduct().getPrice() * o.getQuantity();
        }
    }
}