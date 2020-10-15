package ru.popovichia.eshop.services;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.popovichia.eshop.entities.Customer;
import ru.popovichia.eshop.entities.Order;
import ru.popovichia.eshop.entities.OrderItem;
import ru.popovichia.eshop.entities.Product;
import ru.popovichia.eshop.entities.ProductDto;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */

@Service
public interface DataService {
    
    public List<Order> getAllOrders();
    public Order getOrderById(Long id);
    public void saveOrderById(Long id, Customer customer);
    public void deleteOrderById(Long id);

    public List<Product> getAllProducts();
    public List<ProductDto> getAllProductDtos();
    public Product getProductById(Long id);
    public void saveProductById(Long id, Product product);
    public void deleteProductById(Long id);

    public List<OrderItem> getAllOrdersItems();
    public List<OrderItem> getAllOrdersItems(Integer pageIndex, Integer pageSize);
    public void addOrderItemToOrder(Long orderId, Long productId, Integer productCount);
    public Integer getOrdersItemsPagesCount(Integer pageSize);
    public Integer getOrdersItemsCount();
    public void deleteOrderItemById(Long id);

    public List<Customer> getAllCustomersSortedByFullNameAsc();
    
    public List<OrderItem> getOrdersItemsLikePattern(String inputStringPattern);
    
}
