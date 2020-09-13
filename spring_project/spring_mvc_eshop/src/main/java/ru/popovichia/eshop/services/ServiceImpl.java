package ru.popovichia.eshop.services;

import java.util.List;
import javax.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.popovichia.eshop.entities.Order;
import ru.popovichia.eshop.entities.OrderItem;
import ru.popovichia.eshop.entities.Product;
import ru.popovichia.eshop.repositories.OrdersRepository;
import ru.popovichia.eshop.repositories.ProductsRepository;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */
public class ServiceImpl {
    
    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private OrdersRepository ordersRepository;
    
    public List<Order> getAllOrders() {
        return ordersRepository.getAllOrders();
    }

    public List<OrderItem> getAllOrdersItems() {
        return ordersRepository.getAllOrdersItems();
    }
    
    public void createOrder(
            String customerFirstName,
            String customerLastName,
            String customerPhoneNumber
    ) {
        ordersRepository.createOrder(customerFirstName, customerLastName, customerPhoneNumber);
    }
    
    public Order getOrderById(long id) {
        return ordersRepository.getOrderById(id);
    }

    public void deleteOrderById(long id) {
        ordersRepository.deleteOrderById(id);
    }
    
    public List<Product> getAllProducts() {
        return productsRepository.getAllProducts();
    }
    
    public void createProduct(
            String productTitle,
            float productPrice
    ) {
        productsRepository.createProduct(productTitle, productPrice);
    }

    public void addItemToOrder(
            long orderId,
            long productId,
            int productCount
    ) {
        Order order = ordersRepository.getOrderById(orderId);
        Product product = productsRepository.getProductById(productId);
        if (order != null && product != null && productCount > 0) {
            ordersRepository.addItemToOrder(product, order, productCount);
        }
    }

}
