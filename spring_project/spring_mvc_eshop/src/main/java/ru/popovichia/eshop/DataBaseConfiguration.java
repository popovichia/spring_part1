package ru.popovichia.eshop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.popovichia.eshop.repositories.OrdersRepository;
import ru.popovichia.eshop.repositories.ProductsRepository;
import ru.popovichia.eshop.services.ServiceImpl;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */
@Configuration
public class DataBaseConfiguration {

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory getEntityManagerFactory() {
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    @Bean(name = "entityManager")
    public EntityManager getEntityManager() {
        return this.getEntityManagerFactory().createEntityManager();
    }

    @Bean(name = "ordersRepository")
    public OrdersRepository getOrdersRepository() {
        return new OrdersRepository();
    }

    @Bean(name = "productsRepository")
    public ProductsRepository getProductsRepository() {
        return new ProductsRepository();
    }

    @Bean(name = "serviceImpl")
    public ServiceImpl getServiceImpl() {
        return new ServiceImpl();
    }
    
}
