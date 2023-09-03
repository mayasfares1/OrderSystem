package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Customer;
import model.OrderLine;
import model.Orders;
import model.Product;

import java.util.List;

public class OrderDAOImpl implements IOrderDAO
{

    private EntityManagerFactory emf;

    public OrderDAOImpl(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    @Override
    public Orders saveOrder(Orders order, Customer customer)
    {

        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            order.addCustomer(customer);
            em.persist(order);
            em.getTransaction().commit();
            return order;
        }
    }

    @Override
    public OrderLine saveOrderLine(OrderLine orderLine, Product product, Orders orders)
    {

        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            orderLine.setProduct(product);
            orders.addOrderLine(orderLine);
            em.persist(orderLine);
            em.getTransaction().commit();
            return orderLine;
        }
    }

    @Override
    public List<Orders> allOrdersForCustomer(int id)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            return em.createQuery("SELECT o FROM Orders o WHERE o.customer.id = :Id", Orders.class)
                    .setParameter("Id", id).getResultList();
        }
    }

    @Override
    public Double findTotalPriceOfOrder(int id)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            return em.createQuery("SELECT SUM(ol.quantity * p.price) FROM OrderLine ol JOIN ol.product p WHERE ol.orders.id = :Id", Double.class)
                    .setParameter("Id", id).getSingleResult();
        }
    }
}