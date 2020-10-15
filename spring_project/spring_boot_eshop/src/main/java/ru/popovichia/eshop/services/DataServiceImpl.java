package ru.popovichia.eshop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.popovichia.eshop.entities.Customer;
import ru.popovichia.eshop.entities.Order;
import ru.popovichia.eshop.entities.OrderItem;
import ru.popovichia.eshop.entities.Product;
import ru.popovichia.eshop.entities.ProductDto;
import ru.popovichia.eshop.repositories.CustomersRepository;
import ru.popovichia.eshop.repositories.CustomersRepositoryPaS;
import ru.popovichia.eshop.repositories.OrdersItemsRepository;
import ru.popovichia.eshop.repositories.OrdersItemsRepositoryPaS;
import ru.popovichia.eshop.repositories.OrdersRepository;
import ru.popovichia.eshop.repositories.ProductDtosRepository;
import ru.popovichia.eshop.repositories.ProductsRepository;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */

@Service
public class DataServiceImpl implements DataService {
    
    private static final Logger LOG = Logger.getLogger(DataServiceImpl.class.getName());

    @Autowired
    private OrdersRepository ordersRepository;
    
    @Autowired
    private OrdersItemsRepositoryPaS ordersItemsRepositoryPagination;

    @Autowired
    private ProductsRepository productsRepository;
    
    @Autowired
    private ProductDtosRepository productDtosRepository;

    @Autowired
    private OrdersItemsRepository ordersItemsRepository;

    @Autowired
    private CustomersRepository customersRepository;
    
    @Autowired
    private CustomersRepositoryPaS customersRepositoryPaS;

    private Order editingOrder;
    private Product editingProduct;
    
    public List<Order> getAllOrders() {
        return ordersRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return ordersRepository.findById(id);
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

    public void deleteOrderById(Long id) {
        ordersRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }
    
    public List<ProductDto> getAllProductDtos() {
        return productDtosRepository.findAll();
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
    
    public void saveProductById(Long id, Product product) {
        Product productInDb = null;
        if (id != null) {
            productInDb = productsRepository.findById(id);
        }
        if (productInDb != null) {
            productInDb.setTitle(product.getTitle());
            productInDb.setPrice(product.getPrice());
            productsRepository.save(productInDb);
        } else {
            productsRepository.save(product);
        }
    }

    public void deleteProductById(Long id) {
        productsRepository.deleteById(id);
    }

    public List<OrderItem> getAllOrdersItems() {

        return ordersItemsRepository.findAll();

    }

    public List<OrderItem> getAllOrdersItems(Integer pageIndex, Integer pageSize) {
        
        Pageable pageable = PageRequest.of((pageIndex - 1), pageSize);
        return ordersItemsRepositoryPagination.findAll(pageable).toList();

    }

    public void addOrderItemToOrder(
            Long orderId,
            Long productId,
            Integer productCount
    ) {
        Product product = productsRepository.findById(productId);
        Order order = ordersRepository.findById(orderId);
        if (order != null && product != null && productCount > 0) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setProductPrice(product.getPrice());
            orderItem.setProductCount(productCount);
            orderItem.setOrder(order);
            ordersItemsRepository.save(orderItem);
        }
    }
    
    public Integer getOrdersItemsPagesCount(Integer onePageScope) {
        Integer rowsCount = ordersItemsRepository.getRowsCount();
        Integer pagesCount = 1;
        if (rowsCount != null && rowsCount > 0) {
            pagesCount = rowsCount / onePageScope + (rowsCount % onePageScope > 0 ? 1 : 0);
        }
        return pagesCount;
    }

    public Integer getOrdersItemsCount() {
        return ordersItemsRepository.getRowsCount();
    }
    
    public void deleteOrderItemById(Long id) {
        ordersItemsRepository.deleteById(id);
    }

    public List<Customer> getAllCustomersSortedByFullNameAsc() {
        
        return customersRepositoryPaS.findAll(
                Sort.by("lastName").ascending()
                .and(Sort.by("firstName").ascending())
        );
    }
    
    public List<OrderItem> getOrdersItemsLikePattern(String inputStringPattern) {
        List<OrderItem> resultListOrdersItems = new ArrayList<>();
        resultListOrdersItems
                .addAll(ordersItemsRepository.findLikeOrder(inputStringPattern));
        resultListOrdersItems
                .addAll(ordersItemsRepository.findLikeProduct(inputStringPattern));
        return resultListOrdersItems;
    }

}
