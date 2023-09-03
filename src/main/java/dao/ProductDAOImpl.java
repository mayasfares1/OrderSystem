package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Product;

public class ProductDAOImpl implements IProductDAO{

    private EntityManagerFactory emf;

    public ProductDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Product saveProduct(Product product) {
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            if (product.getId() == null){
                em.persist(product);
            }
            em.getTransaction().commit();
            return product;
        }
    }

    @Override
    public Product findProduct(int id)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            return em.find(Product.class, id);
        }
    }
}
