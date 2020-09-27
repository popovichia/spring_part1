package ru.popovichia.eshop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.popovichia.eshop.entities.OrderItem;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */
@Repository
public interface OrdersItemsRepositoryPaS
        extends PagingAndSortingRepository<OrderItem, Long> {

    public Page<OrderItem> findAll(Pageable pageable);
    
}
