package ru.popovichia.eshop.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import ru.popovichia.eshop.entities.Product;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */
public class ProductsRepository {
    
    @Autowired
    private EntityManager entityManager;
    
    public List<Product> getAllProducts() {
        return entityManager.createQuery("from Product").getResultList();
    }
    
    public void createProduct(
            String productTitle,
            float productPrice
    ) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Product product = new Product(productTitle, productPrice);
        entityTransaction.begin();
        entityManager.persist(product);
        entityTransaction.commit();
    }
    
    public Product getProductById(long id) {
        return entityManager
                .createQuery("from Product where id = :id", Product.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void updateProduct(Product product) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(product);
        entityTransaction.commit();
    }
    
    public void deleteProduct(Product product) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(product);
        entityTransaction.commit();
    }
    
}
