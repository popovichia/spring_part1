package ru.popovichia.eshop.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.popovichia.eshop.entities.OrderItem;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */
@Repository
public interface OrdersItemsRepository
        extends org.springframework.data.repository.Repository<OrderItem, Long> {

    public List<OrderItem> findAll();
    public OrderItem findById(Long id);
    public void save(OrderItem orderItem);
    
    @Query(value = "SELECT oi FROM OrderItem oi "
            + "INNER JOIN oi.product "
            + "WHERE "
            + "oi.product.title LIKE %:inputStringPattern% "
            + "OR oi.product.price LIKE %:inputStringPattern%"
    )
    public List<OrderItem> findLikeProduct(@Param("inputStringPattern") String inputStringPattern);
    
    @Query(value = "SELECT oi FROM OrderItem oi "
            + "INNER JOIN oi.order "
            + "WHERE "
            + "oi.order.customer.firstName LIKE %:inputStringPattern% "
            + "OR oi.order.customer.lastName LIKE %:inputStringPattern% "
            + "OR oi.order.customer.phoneNumber LIKE %:inputStringPattern% "
            + "OR oi.order.customer.email LIKE %:inputStringPattern%"
    )
    public List<OrderItem> findLikeOrder(@Param("inputStringPattern") String inputStringPattern);
    
    @Query(value = "SELECT COUNT(*) FROM OrderItem")
    public Integer getRowsCount();

    public void deleteById(Long id);
    
}
