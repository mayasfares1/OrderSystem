package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.OrderLine;
import model.Orders;
import model.Product;

import java.util.List;

public class OrderDAOImpl {

    public class OrderDAOImpl implements IOrderDAO {

        private EntityManagerFactory emf;

        public OrderDAOImpl(EntityManagerFactory emf) {

            this.emf = emf;
        }

        @Override
        public Orders saveOrder(Orders order) {
            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();
                em.persist(order);
                em.getTransaction().commit();
                return order;
            }
        }

        @Override
        public OrderLine saveOrderLine(OrderLine orderLine, Product product, Orders orders) {

            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();
                orderLine.setProduct(product);
                orders.addOrderLine(orderLine);
                em.persist(orderLine);
                em.getTransaction().commit();
                return orderLine;
            }
        }

        public List<Orders> allOrdersForCustomer(int customerId) {
            try (EntityManager em = emf.createEntityManager()) {
                return em.createQuery("SELECT o FROM Orders o WHERE o.customer.id = :id", Orders.class)
                        .setParameter("id", customerId)
                        .getResultList();
            }
        }

        public Double findTotalPriceOfOrder(int orderId) {
            try (EntityManager em = emf.createEntityManager()) {
                return em.createQuery("SELECT SUM(ol.quantity * p.price) FROM OrderLine ol JOIN ol.product p WHERE ol.orders.id = :id", Double.class)
                        .setParameter("id", orderId)
                        .getSingleResult();
            }
        }
    }
}
