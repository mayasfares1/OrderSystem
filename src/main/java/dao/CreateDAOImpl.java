package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Customer;

public class CreateDAOImpl {

    private EntityManagerFactory emf;

    public CreateDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override

    public Customer saveCustomer(Customer customer) {

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            em.close();
            return customer;
        }
        public Customer findCustomer (int id){
            try (EntityManager em = emf.createEntityManager()) {
                return em.find(Customer.class, id);

            }

        }
    }
}
