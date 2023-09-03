import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Customer;

public class TotalCostDAO {

    private String name;
    private String email;

    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();



    public void createCustomer(int id) {
        try (EntityManager entityManager = emf.createEntityManager()) {


            entityManager.getTransaction().begin();
            Customer c = new Customer();
            c.setName("Alex");
            c.setEmail("alex@gmail.com");
            entityManager.merge(c);
            entityManager.getTransaction().commit();
        }
    }

        public Customer findCustomer (int id)
        {
            try(EntityManager entityManager = emf.createEntityManager()){
              Customer customer = entityManager.find(Customer.class,id);

                return customer;

            }
        }

    public TotalCostDAO(EntityManagerFactory emf) {
        this.emf = emf;

    }

    //        public long getAllCustomers(int id)
//
//        {
//            try (EntityManager em = emf.createEntityManager())
//            {
//                String paramName = "name";
//                TypedQuery<model.Customer> query = em.createQuery("SELECT n FROM model.Orders n JOIN n.customer p WHERE p.id = :name", model.Customer.class);
//                query.setParameter(paramName, customerID);
//                return query.getResultList();
//            }
//        }










    }