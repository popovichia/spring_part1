package ru.popovichia.eshop.services;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.popovichia.eshop.entities.Customer;
import ru.popovichia.eshop.entities.Order;
import ru.popovichia.eshop.entities.OrderItem;
import ru.popovichia.eshop.entities.Product;
import ru.popovichia.eshop.repositories.CustomersRepository;
import ru.popovichia.eshop.repositories.OrdersItemsRepository;
import ru.popovichia.eshop.repositories.OrdersRepository;
import ru.popovichia.eshop.repositories.ProductsRepository;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */

@Service
public class DataServiceImpl implements DataService {
    
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ProductsRepository productsRepository;
    
    @Autowired
    private OrdersItemsRepository ordersItemsRepository;

    @Autowired
    private CustomersRepository customersRepository;
    
    private Order editingOrder;
    private Product editingProduct;
    
    public List<Order> getAllOrders() {
        return ordersRepository.findAll();
    }

    public List<OrderItem> getAllOrdersItems() {
        return ordersItemsRepository.findAll();
    }

    public List<Order> getOrdersByPattern(String inputStringPattern) {
        return null;
    }

    public Order getOrderById(Long id) {
        return ordersRepository.findById(id);
    }
    
    public Product getProductById(Long id) {
        
        Product product = null;
        if (id != null) {
            product = productsRepository.findById(id);
        }
        if (product == null) {
            product = new Product();
        }
        return product;
    
    }

    public void saveOrderById(Long id, Customer customer) {
        
        Order order = ordersRepository.findById(id);
        if (order == null) {
            order = new Order();
        }
        order.setCustomer(customer);
        customersRepository.save(customer);
        ordersRepository.save(order);

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
    
    public void updateOrderById(Long id, Order newOrder) {
    }
    
    public void deleteOrderById(long id) {
    }
    
    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }
    
    public void saveProduct(Product product) {
        
        productsRepository.save(product);
        
    }

    public void setEditingProduct(
            Long id            
    ) {
        if (id != null) {
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
    }
    
    public void deleteProductById(long id) {
    }

    public void addOrderItemToOrder(
            long orderId,
            long productId,
            int productCount
    ) {
    }
    
    public void deleteOrderItemById(Long id) {
    }
}
