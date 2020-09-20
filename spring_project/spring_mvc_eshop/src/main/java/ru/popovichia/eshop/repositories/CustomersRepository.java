package ru.popovichia.eshop.repositories;

import org.springframework.stereotype.Repository;
import ru.popovichia.eshop.entities.Customer;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */
@Repository
public interface CustomersRepository extends org.springframework.data.repository.Repository<Customer, Long> {

    public void save(Customer customer);
    
}
