package ru.popovichia.eshop.services;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.popovichia.eshop.entities.Customer;
import ru.popovichia.eshop.entities.Order;
import ru.popovichia.eshop.entities.OrderItem;
import ru.popovichia.eshop.entities.Product;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */

@Service
public interface DataService {
    
    public List<Order> getAllOrders();

    public List<OrderItem> getAllOrdersItems();
    
    public Order getOrderById(Long id);

    public Product getProductById(Long id);
    
    public List<Order> getOrdersByPattern(String inputStringPattern);

    public void saveOrderById(Long id, Customer customer);

    public Order getEditingOrder();

    public void setEditingOrder(Long id);

    public void updateOrderById(Long id, Order newOrder);
    
    public void deleteOrderById(long id);
    
    public List<Product> getAllProducts();
    
    public void saveProduct(Product product);

    public void setEditingProduct(
            Long id            
    );

    public Product getEditingProduct();

    public void updateProductById(
            Long id,
            String productNewTitle,
            BigDecimal productNewPrice
    );
    
    public void deleteProductById(long id);

    public void addOrderItemToOrder(
            long orderId,
            long productId,
            int productCount
    );
    
    public void deleteOrderItemById(Long id);
    
}
