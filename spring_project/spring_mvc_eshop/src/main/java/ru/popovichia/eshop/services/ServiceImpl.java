package ru.popovichia.eshop.services;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    private Order editingOrder;
    private Product editingProduct;
    
    public List<Order> getAllOrders() {
        return ordersRepository.getAllOrders();
    }

    public List<OrderItem> getAllOrdersItems() {
        return ordersRepository.getAllOrdersItems();
    }
    
    public void createOrder(
            String customerFirstName,
            String customerLastName,
            String customerPhoneNumber,
            String customerEmail
    ) {
        ordersRepository.createOrder(
                customerFirstName,
                customerLastName,
                customerPhoneNumber,
                customerEmail
        );
    }

    public Order getEditingOrder() {
        return editingOrder;
    }

    public void setEditingOrder(Long id) {
        if (id != null) {
            this.editingOrder = this.getOrderById(id);
        } else {
            this.editingOrder = null;
        }
    }

    
    public Order getOrderById(long id) {
        return ordersRepository.getOrderById(id);
    }

    public void updateOrderById(
            Long id,
            String customerNewFirstName,
            String customerNewLastName,
            String customerNewPhoneNumber,
            String customerNewEmail
    ) {
        ordersRepository.updateOrderById(
                id,
                customerNewFirstName,
                customerNewLastName,
                customerNewPhoneNumber,
                customerNewEmail
        );
    }
    
    public void deleteOrderById(long id) {
        Order order = ordersRepository.getOrderById(id);
        ordersRepository.deleteOrder(order);
    }
    
    public List<Product> getAllProducts() {
        return productsRepository.getAllProducts();
    }
    
    public void createProduct(
            String productTitle,
            BigDecimal productPrice
    ) {
        productsRepository.createProduct(productTitle, productPrice);
    }

    public void setEditingProduct(
            Long id            
    ) {
        if (id != null) {
            this.editingProduct = productsRepository.getProductById(id);
        } else {
            this.editingProduct = null;
        }
    }

    public Product getEditingProduct() {
        return editingProduct;
    }    

    public void updateProductById(
            Long id,
            String productNewTitle,
            BigDecimal productNewPrice
    ) {
        Product product = productsRepository.getProductById(id);
        product.setTitle(productNewTitle);
        product.setPrice(productNewPrice);
        productsRepository.updateProduct(product);
    }
    
    public void deleteProductById(long id) {
        Product product = productsRepository.getProductById(id);
        productsRepository.deleteProduct(product);
    }

    public void addOrderItemToOrder(
            long orderId,
            long productId,
            int productCount
    ) {
        Order order = ordersRepository.getOrderById(orderId);
        Product product = productsRepository.getProductById(productId);
        if (order != null && product != null && productCount > 0) {
            ordersRepository.addOrderItemToOrder(product, order, productCount);
        }
    }
    
    public void deleteOrderItemById(Long id) {
        OrderItem orderItem = ordersRepository.getOrderItemById(id);
        ordersRepository.deleteOrderItem(orderItem);
    }
}
