package ru.popovichia.eshop.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.popovichia.eshop.entities.OrderItem;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */
@Repository
public interface OrdersItemsRepository extends org.springframework.data.repository.Repository<OrderItem, Long> {
    
    public List<OrderItem> findAll();
    
}
