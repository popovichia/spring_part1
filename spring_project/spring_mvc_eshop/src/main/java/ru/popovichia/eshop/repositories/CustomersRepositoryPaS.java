package ru.popovichia.eshop.repositories;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.popovichia.eshop.entities.Customer;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */
@Repository
public interface CustomersRepositoryPaS extends PagingAndSortingRepository<Customer, Long> {

    public List<Customer> findAll(Sort sort);
    
}
