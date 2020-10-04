package ru.popovichia.eshop.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.popovichia.eshop.entities.Product;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */
@Repository
public interface ProductsRepository extends org.springframework.data.repository.Repository<Product, Long> {
    
    public List<Product> findAll();
    public Product findById(Long id);
    public void save(Product product);
    public void deleteById(Long id);
    
}
